val free = mutableListOf<Pair<Int,Int>>()
val occ = mutableListOf<Pair<Int,Int>>()
var w = mutableListOf<Int>()

var ok: Boolean = false
var turn: Boolean = true // true: Player A, false: Player B
var over: Boolean = false
var next: Boolean = false

var one = mutableListOf<Int>()
var two = mutableListOf<Int>()
var three = mutableListOf<Int>()
var four = mutableListOf<Int>()
var five = mutableListOf<Int>()
var six = mutableListOf<Int>()

// TODO: Implement win condition logic

fun printBoard(s: Array<Array<Int>>) {
    for (i in s.indices) {
        for (j in s[i].indices) {
            if (s[i][j] != 0)
                print(" ${s[i][j]} ")
            else
                print("   ")
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

        // add each coordinate pair to free 1-based indexing
        free.clear()
        occ.clear()
        for (i in 1..3) {
            for (j in 1..3) {
                free.add(Pair(i, j))
            }
        }

        ok = false
        next = false
        turn = true
        over = false

        one.clear()
        two.clear()
        three.clear()
        four.clear()
        five.clear()
        six.clear()

        var pegList = mutableListOf(1,2,3,4,5,6,7,8,9)

        do{

            try { print("\u001b[H\u001b[2J") }
            catch (e: Exception) { println("Exception details: ${e.message}") }

            printBoard(s)

            // TODO: Input handling for peg and pos
            var peg: Int? = null
            var pos: Pair<Int, Int>? = null
            while (peg == null) {
                println("Enter peg (1-9):")
                peg = readlnOrNull()?.toIntOrNull()?.takeIf { it in pegList }
                if (peg == null) println("Invalid peg. Try again.")
            }
            while (pos == null) {
                println("Enter row (1-3):")
                val row = readlnOrNull()?.toIntOrNull()?.takeIf { it in 1..3 }
                println("Enter column (1-3):")
                val col = readlnOrNull()?.toIntOrNull()?.takeIf { it in 1..3 }
                if (row != null && col != null && free.contains(Pair(row, col))) {
                    pos = Pair(row, col)
                } else {
                    println("Invalid position. Try again.")
                }
            }

            nextPlayerMove(peg, pos)

            // TODO: Implement win condition check here
            // if (checkWin()) {
            //     over = true
            //     gameOver(over)
            // }

            // Alternate player only after valid move
            turn = !turn

        } while(!over)

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

fun nextPlayerMove(peg: Int, pos: Pair<Int,Int>){

    if (free.contains(pos)){
        ok = true
        free.remove(pos)
        occ.add(pos)
    } else {
        ok = false
        println("Position already occupied.")
        return
    }

    when (pos.first){
        1 -> one.add(peg)
        2 -> two.add(peg)
        3 -> three.add(peg)
    }

    when (pos.second){
        1 -> four.add(peg)
        2 -> five.add(peg)
        3 -> six.add(peg)
    }

    // TODO: Update board state (s) with peg value

    next = !next
}

fun gameOver(over: Boolean): String{
    // TODO: Implement proper win condition check
    if (over /* && win condition */)
        return "Player A wins"
    else
        return "Player B wins"
}

// TODO: Implement checkWin() function to determine if the game