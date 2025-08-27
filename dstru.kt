import kotlin.system.exitProcess

// ======== Sets and Types from Spec ========

// A = {1,2,3}; S = A x A (positions as (row,col))
typealias Pos = Pair<Int, Int>

// Predefined position sets (by coordinates)
val T: Set<Pos> = setOf(1 to 1, 1 to 2, 1 to 3)           // top row
val M: Set<Pos> = setOf(2 to 1, 2 to 2, 2 to 3)           // middle row
val B: Set<Pos> = setOf(3 to 1, 3 to 2, 3 to 3)           // bottom row

val L: Set<Pos> = setOf(1 to 1, 2 to 1, 3 to 1)           // left col
val C: Set<Pos> = setOf(1 to 2, 2 to 2, 3 to 2)           // center col
val R: Set<Pos> = setOf(1 to 3, 2 to 3, 3 to 3)           // right col

// S = full 3x3 grid
val S: Set<Pos> = (1..3).flatMap { i -> (1..3).map { j -> i to j } }.toSet()

// F (not used directly by transitions, defined for completeness):
// All 3-element subsets of S except the two diagonals.
val F: Set<Set<Pos>> = run {
    val allTriples = S.toList()
        .indices
        .flatMap { i ->
            (i + 1 until S.size).flatMap { j ->
                (j + 1 until S.size).map { k ->
                    setOf(S.elementAt(i), S.elementAt(j), S.elementAt(k))
                }
            }
        }
        .toSet()

    val d1 = setOf(1 to 1, 2 to 2, 3 to 3)
    val d2 = setOf(1 to 3, 2 to 2, 3 to 1)
    allTriples - setOf(d1, d2)
}

// ======== System Variables (Global) ========

val occ = mutableSetOf<Pos>()                // Occ ⊆ S
val free = mutableSetOf<Pos>()               // Free = S − Occ

// One..Six ⊆ P (pegs used in each row/col set)
val one = mutableSetOf<Int>()                // T row pegs
val two = mutableSetOf<Int>()                // M row pegs
val three = mutableSetOf<Int>()              // B row pegs
val four = mutableSetOf<Int>()               // L col pegs
val five = mutableSetOf<Int>()               // C col pegs
val six = mutableSetOf<Int>()                // R col pegs

// Peg ⊆ P (available pegs), P = {1..9}
val Peg = mutableSetOf<Int>()                // Peg = P − (One ∪ ... ∪ Six)

// W = {One} ∪ ... ∪ {Six}  (i.e., set of subsets)
val W: List<Set<Int>> get() = listOf(one, two, three, four, five, six)

// Booleans
var ok = false
var next = false
var turn = true   // true: Player 1's turn, false: Player 2's turn
var over = false

// Board for display (0 = empty)
val board = Array(3) { IntArray(3) { 0 } }

// Result
var result = ""

// ======== Utilities ========

fun resetGame() {
    occ.clear()
    free.clear()
    free.addAll(S)

    one.clear(); two.clear(); three.clear()
    four.clear(); five.clear(); six.clear()

    Peg.clear(); Peg.addAll(1..9)

    ok = false
    next = false
    turn = true
    over = false
    result = ""

    for (i in 0..2) for (j in 0..2) board[i][j] = 0
}

fun printBoard() {
    for (i in 0..2) {
        for (j in 0..2) {
            if (board[i][j] != 0) print(" ${board[i][j]} ") else print("   ")
            if (j < 2) print("|")
        }
        println()
        if (i < 2) println("---+---+---")
    }
}

fun readPeg(): Int {
    while (true) {
        println("Choose peg (1-9) from available: $Peg")
        val v = readlnOrNull()?.toIntOrNull()
        if (v != null && v in Peg) return v
        println("Invalid peg. Try again.")
    }
}

fun readPos(): Pos {
    while (true) {
        println("Enter row (1-3):")
        val r = readlnOrNull()?.toIntOrNull()
        println("Enter column (1-3):")
        val c = readlnOrNull()?.toIntOrNull()
        if (r != null && c != null && r in 1..3 && c in 1..3) {
            val p = r to c
            if (p in free) return p
            println("That position is occupied. Try again.")
        } else {
            println("Invalid coordinates. Try again.")
        }
    }
}

// ======== Core Spec Logic ========

// NextPlayerMove(peg ∈ P, pos ∈ S)
fun nextPlayerMove(peg: Int, pos: Pos) {
    // Preconditions: peg ∈ Peg and pos ∈ Free
    if (peg !in Peg || pos !in free) return

    // peg ∈ Peg ∧ pos ∈ Free → ok = ¬ok ∧ Occ = Occ ∪ {pos}
    ok = !ok
    occ += pos
    free -= pos

    // Place peg on visual board
    board[pos.first - 1][pos.second - 1] = peg

    // Update Peg (Peg = P − (One ∪ ... ∪ Six)), but we remove now and will reflect in W
    Peg -= peg

    // Row updates: ok ∧ pos ∈ T/M/B → add peg; next = ¬next
    if (ok) {
        when (pos) {
            in T -> one += peg
            in M -> two += peg
            in B -> three += peg
        }
        next = !next
    }

    // Column updates: ok ∧ next ∧ pos ∈ L/C/R → add peg; next = ¬next; ok = ¬ok
    if (ok && next) {
        when (pos) {
            in L -> four += peg
            in C -> five += peg
            in R -> six += peg
        }
        next = !next
        ok = !ok
    }

    // After a move, recompute over using the spec formula:
    // over ↔ (∃w∈W (|w|=3 ∧ sum<15)) ∨ (∀w∈W (|w|=3 → sum=15))
    // Avoid vacuous truth: require at least one |w|=3 for the ∀-clause.
    val triples = W.filter { it.size == 3 }
    val existsLess15 = triples.any { it.sum() < 15 }
    val allEq15 = triples.isNotEmpty() && triples.all { it.sum() == 15 }
    over = existsLess15 || allEq15

    // Now resolve GameOver or toggle turn if not over
    gameOver(existsLess15, allEq15)
}

// GameOver(over)
// over ∧ turn ∧ ∃w (|w|=3 ∧ sum<15)   → B wins
// over ∧ ¬turn ∧ ∃w (|w|=3 ∧ sum<15)  → A wins
// over ∧ turn ∧ ∀w (|w|=3 ∧ sum=15)   → A wins
// over ∧ ¬turn ∧ ∀w (|w|=3 ∧ sum=15)  → B wins
// ¬over                                → turn = ¬turn
fun gameOver(existsLess15: Boolean, allEq15: Boolean) {
    if (over) {
        result = when {
            existsLess15 && turn      -> "Player B wins"
            existsLess15 && !turn     -> "Player A wins"
            allEq15 && turn           -> "Player A wins"
            allEq15 && !turn          -> "Player B wins"
            else                      -> "Draw"
        }
    } else {
        // ¬over → toggle turn
        turn = !turn
    }
}

// ======== Main Loop ========

fun main() {
    resetGame()

    while (true) {
        println("\n==============================")
        println(if (turn) "Player A (P1) Turn" else "Player B (P2) Turn")
        println("==============================\n")
        printBoard()
        println()

        val peg = readPeg()
        val pos = readPos()

        nextPlayerMove(peg, pos)

        // If game ended, show result and possibly restart
        if (over) {
            println("\nFinal Board:")
            printBoard()
            println("\n$result")
            println("\nPlay again? (Y/N)")
            when (readlnOrNull()?.trim()?.lowercase()) {
                "y", "yes" -> resetGame()
                "n", "no"  -> {
                    println("Thanks for playing!")
                    exitProcess(0)
                }
                else -> {
                    println("Exiting.")
                    exitProcess(0)
                }
            }
        }
    }
}


// lesson learned: don't try to deviate from specs
// understand what the code is asking for rather than blindly coding, W was referring to a set of sets, that was put there for a reason
// use type alias, pre-defined "set" structure
// board reset can be moved to its own function
// spread things out for more modularization, not everything should be in main
// while (true) + return base case is a goated combo for user input validation
// could have used the when statement for the game over part, was careless in understanding the specs
// always avoid vacuous truths when necessary by going if item.all, we also have to make sure item.notempty, otherwise empty sets will always qualify
// overall id grade myself a 5/10 here
