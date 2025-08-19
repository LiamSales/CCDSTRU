val occ = Array(3) { Array<Int>(3) { 0 } }
val free = Array(3) { Array<Int>(3) { 0 } }

var peg: Int
var w: Int

var ok: Boolean
var turn: Boolean
var over: Boolean
var next: Boolean

/*
    Free = S − Occ
    Peg = P − (One ∪ Two ∪ Three ∪ Four ∪ Five ∪ Six)
    W = {One} ∪ {Two} ∪ {Three} ∪ {Four} ∪ {Five} ∪ {Six}
    over ↔ (∃w(w ∈ W ∧ |w| = 3 ∧ ∑a∈w a < 15) ∨ ∀w (w ∈ W ∧ |w| = 3 ∧ ∑a∈w a = 15))


Occ ⊆ S = Occupied positions (all the spots that have a peg already).

Free ⊆ S = Free positions (S - Occ).

One, Two, Three = sets of pegs placed in rows (T, M, B).

Four, Five, Six = sets of pegs placed in columns (L, C, R).

Peg ⊆ P = pegs not yet used (P - (all placed pegs)).

W = collection of all these sets ({One, Two, Three, Four, Five, Six}).

ok ∈ {true,false} = tells if the last move was valid.

turn ∈ {true,false} = tells whose turn it is (true = Player A, false = Player B).

next ∈ {true,false} = auxiliary variable for row/column assignment.

over ∈ {true,false} = whether the game has ended.
 */


fun printBoard(s: Array<Array<Char>>) {
    for (i in s.indices) {
        for (j in s[i].indices) {
            print(" ${s[i][j]} ")
            if (j < s[i].lastIndex) print("|")
        }
        println()
        if (i < s.lastIndex) {
            println("---+---+---")
        }
    }
}


fun main(){

    var again = false
    var invalid = true

    do{

        val s = Array(3) { Array<Int>(3) { 0 } }

        ok = false
        next = false
        turn = true
        over = false

        var one = mutableListOf<Int>()
        var two = mutableListOf<Int>()
        var three = mutableListOf<Int>()
        var four = mutableListOf<Int>()
        var five = mutableListOf<Int>()
        var six = mutableListOf<Int>()

        var peg = listOf(1,2,3,4,5,6,7,8,9)

        do{

            try Runtime.getRuntime().exec("cmd /c cls")
            catch (e: Exception) println("Exception details: ${e.message}")

            printBoard(s)

            //gameplay

            over = true

        }while(!over)

        println("Do you want to play again? (Y/N)")
        while(invalid)
            when (readlnOrNull()?.lowercase()) {
            "n" -> invalid = false
            "y" -> {
                    invalid = false
                    again = true
                    }
            else -> println("\tInvalid input. Please enter Y or N.")
            }

    } while (again)

}

fun nextPlayerMove(free: Array){

    val peg = readlnOrNull()?.toIntOrNull()?.takeIf { it in 1..9 } ?: //invalid input ask again
    val position = readlnOrNull()?.toIntOrNull()?.takeIf { it in // has to be in the free array

}

/*
to do:

Make sure Occ (occupied) and Free (free) are updated correctly during gameplay

At start: Occ = ∅, Free = S (all positions)

After a move: add position to Occ, remove from Free

Implement function NextPlayerMove(peg ∈ P, pos ∈ S)

Check: peg ∈ Peg and pos ∈ Free → ok = !ok and update Occ

If ok ∧ pos ∈ T → add peg to One, toggle next

If ok ∧ pos ∈ M → add peg to Two, toggle next

If ok ∧ pos ∈ B → add peg to Three, toggle next

If ok ∧ next ∧ pos ∈ L → add peg to Four, toggle next, toggle ok

If ok ∧ next ∧ pos ∈ C → add peg to Five, toggle next, toggle ok

If ok ∧ next ∧ pos ∈ R → add peg to Six, toggle next, toggle ok

 Remove peg from Peg once it is placed


 Build W = {One, Two, Three, Four, Five, Six}

 Check if game is over:

If ∃w (subset of W with size 3) where ∑a∈w < 15 → one player wins

If ∀w (subset of W with size 3) where ∑a∈w = 15 → the other player wins

 Implement result logic:

over ∧ turn ∧ ∃w(sum<15) → B wins

over ∧ ¬turn ∧ ∃w(sum<15) → A wins

over ∧ turn ∧ ∀w(sum=15) → A wins

over ∧ ¬turn ∧ ∀w(sum=15) → B wins


 While !over, alternate turns (turn = !turn)

 Prompt correct player (A or B) to pick a peg and a pos

 Validate: peg must be unused (peg ∈ Peg), pos must be free (pos ∈ Free)


 Display board after each move (Occ positions filled with peg numbers)

 Handle invalid input gracefully (position already occupied, peg already used)

 Print result at the end (A wins or B wins)

 Use enums or constants for player turns (instead of plain booleans)

 Create helper functions:

printBoard()

isGameOver()

placePeg()

 Add scoring or replay tracking
*/