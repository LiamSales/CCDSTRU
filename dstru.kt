val free = mutableListOfistOf<Pair<Int,Int>>()
val occ = mutableListOfistOf<Pair<Int,Int>>()
var w: mutableListOf<Int>()

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

//win if at least 1 less than 15 sum or every sum in w is exactly 15

fun printBoard(s: Array<Array<Char>>) {
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

        var peg = mutableListOf(1,2,3,4,5,6,7,8,9)

        do{

            try Runtime.getRuntime().exec("cmd /c cls")
            catch (e: Exception) println("Exception details: ${e.message}")

            printBoard(s)

            //gameplay

            val peg = readlnOrNull()?.toIntOrNull()?.takeIf { it in 1..9 } ?: //invalid input ask again
            val pos = readlnOrNull()?.toIntOrNull()?.takeIf { it in // has to be in the free array ask again if not

            nextPlayerMove(peg, pos)

            if(){
                over = true
                gameOver(over)
            }

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

    if (free[pos] == True ){
        ok = !ok
        free[pos] == False
        occ[pos] == True
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

    next = !next
    ok = !ok

}

fun gameOver(over): String{
    if (over &&)
        return "Player A wins"
    else
        return "Player B wins"
}