val occ = Array(3) { Array<Int>(3) { 0 } }
val free = Array(3) { Array<Int>(3) { 0 } }

var w: Int

var ok: Boolean
var turn: Boolean
var over: Boolean
var next: Boolean

//player 1 TMB, 2 LCR

//TODO: interpret this in plain text:
//over ↔ (∃w(w ∈ W ∧ |w| = 3 ∧ ∑a∈w a < 15) ∨ ∀w (w ∈ W ∧ |w| = 3 ∧ ∑a∈w a = 15))

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

        var peg = mutableListOfistOf(1,2,3,4,5,6,7,8,9)

        do{

            try Runtime.getRuntime().exec("cmd /c cls")
            catch (e: Exception) println("Exception details: ${e.message}")

            printBoard(s)

            //gameplay
            next = !next

            val peg = readlnOrNull()?.toIntOrNull()?.takeIf { it in 1..9 } ?: //invalid input ask again
            val pos = readlnOrNull()?.toIntOrNull()?.takeIf { it in // has to be in the free array

                //    peg ∈ Peg ∧ pos ∈ Free → ok = ¬ok
                //    ∧ Occ = Occ ∪ {pos}
            nextPlayerMove(peg, pos)

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

fun nextPlayerMove(peg: Int, pos: Int){
//    - A function that takes in two points. A certain peg (peg) gains a certain 
//  amount of points (P) when it is position in a position (pos) with coordinates (S). 

}

fun gameOver(over: Boolean): String{
    if ()
    return "Player A wins"
    else
    return "Player B wins"
}