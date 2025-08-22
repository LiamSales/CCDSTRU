val free = Array(3) { Array<Boolean>(3) { True } }//should we make this a list instead?
val occ = Array(3) { Array<Boolean>(3) { False } }//should we make this a list instead just like peg? so we can add and remove

var w: Int

var ok: Boolean
var turn: Boolean
var over: Boolean
var next: Boolean

var one = mutableListOf<Int>()
var two = mutableListOf<Int>()
var three = mutableListOf<Int>()
var four = mutableListOf<Int>()
var five = mutableListOf<Int>()
var six = mutableListOf<Int>()

//player 1 TMB, 2 LCR

//win if at least 1 less than 15 sum or every sum in w is exactly 15

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

fun nextPlayerMove(peg: Int, pos: Pair<Int,Int>){
//    - A function that takes in two points. pegs get P points when its in a position with S coordinates

    if (free[pos] == True ){
        ok = !ok
        free[pos] == False
        occ[pos] == True
    }


    /* ok ∧ pos ∈ T  → One = One ∪ {peg}
    ∧ next = ¬next
    - variable (ok) is true and the position */

    //if (ok && j value of pos is 0 )
    // One gets the peg int added to its list
    // next = !next

    //switch case on the numbers

}

fun gameOver(over: Boolean): String{
    if ()
    return "Player A wins"
    else
    return "Player B wins"
}