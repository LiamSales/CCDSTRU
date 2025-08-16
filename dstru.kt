val occ = Array(3) { Array<Int>(3) { 0 } }
val free = Array(3) { Array<Int>(3) { 0 } }

var next: Int
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

 */


fun main(){

    var again = false
    var invalid = true

    do{

        val s = Array(3) { Array<Int>(3) { 0 } }

        ok = false
        next = false
        turn = true

        var one : Array<Int> = arrayOf<Int>()
        var two : Array<Int> = arrayOf<Int>()
        var three : Array<Int> = arrayOf<Int>()
        var four : Array<Int> = arrayOf<Int>()
        var five : Array<Int> = arrayOf<Int>()
        var six : Array<Int> = arrayOf<Int>()

        var peg = {1, 2, 3, 4, 5, 6, 7, 8, 9}


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

    } (while again)

}