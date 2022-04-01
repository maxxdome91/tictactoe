package tictactoe

import kotlin.math.abs

fun main() {
    //print("Enter cells: ")
    var userString = "_________"
    var xTurn = true
    var result = """
        ${"-".repeat(9)}
        | ${userString?.get(0)} ${userString?.get(1)} ${userString?.get(2)} |
        | ${userString?.get(3)} ${userString?.get(4)} ${userString?.get(5)} |
        | ${userString?.get(6)} ${userString?.get(7)} ${userString?.get(8)} |
        ${"-".repeat(9)}
    """.trimIndent()
    println(result)
    while (true) {
        print("Enter the coordinates: ")
        val coordinatesString = readln().split(" ").toMutableList()
        val coordinates : MutableList<Char> = mutableListOf()
        val intCoordinates : MutableList<Int> = mutableListOf()
        for (i in 0 .. coordinatesString.lastIndex) {
            coordinates.add(coordinatesString[i].first())
        }
        if (!coordinates[0].isDigit() || !coordinates[coordinates.lastIndex].isDigit()) {
            println("You should enter numbers!")
            continue
        }
        intCoordinates.add(coordinates[0].digitToInt())
        intCoordinates.add(coordinates[coordinates.lastIndex].digitToInt())
        if (intCoordinates[0] !in 1..3 || intCoordinates[intCoordinates.lastIndex] !in 1..3) {
            println("Coordinates should be from 1 to 3!")
            continue
        }
        intCoordinates[0] --
        intCoordinates[intCoordinates.lastIndex] --
        val index = intCoordinates[0]*3 + intCoordinates[intCoordinates.lastIndex]
        if (userString[index] != '_') {
            println("This cell is occupied! Choose another one!")
            continue
        }
        val charToInsert : Char
        charToInsert = if (xTurn) 'X'
        else 'O'
        xTurn = !xTurn
        if (index == 0) {
            userString = charToInsert + userString.substring(1)
        } else{
            userString = userString.substring(0, index) + charToInsert + userString.substring(index+1)
        }

        result = """
        ${"-".repeat(9)}
        | ${userString?.get(0)} ${userString?.get(1)} ${userString?.get(2)} |
        | ${userString?.get(3)} ${userString?.get(4)} ${userString?.get(5)} |
        | ${userString?.get(6)} ${userString?.get(7)} ${userString?.get(8)} |
        ${"-".repeat(9)}
    """.trimIndent()
        println(result)
        if (isImpossible(userString)) {
            println("Impossible")
            break
        } else if (hasXWon(userString)) {
            println("X wins")
            break
        } else if (hasOWon(userString)) {
            println("O wins")
            break
        } else if (isGameFinished(userString)) {
            println("Game not finished")
        } else {
            println("Draw")
            break
        }
    }
}

fun isGameFinished(s: String): Boolean {
    return s.contains('_')
}

fun isImpossible(s: String): Boolean {
    val xCount = s.count { x -> x == 'X' }
    val oCount = s.count { x -> x == 'O' }

    return if (abs(xCount - oCount) > 1) {
        true
    } else hasXWon(s) && hasOWon(s)
}

fun hasXWon(s: String): Boolean {
    if (s.substring(0..2) == "XXX" || s.substring(3..5) == "XXX"
        || s.substring(6..8) == "XXX"
    ) {
        return true
    } else {
        for (i in 0..2) {
            if (s[i] == 'X' && s[i+3] == 'X' && s[i+6] == 'X') {
                return true
            }
        }
    }
    if (s[4] == 'X') {
        if ((s[0] == 'X' && s[8] == 'X') || (s[2] == 'X' && s[6] == 'X')) {
            return true
        }
    }
    return false
}

fun hasOWon(s: String): Boolean {
    if (s.substring(0..2) == "OOO" || s.substring(3..5) == "OOO"
        || s.substring(6..8) == "OOO"
    ) {
        return true
    } else {
        for (i in 0..2) {
            if (s[i] == 'O' && s[i+3] == 'O' && s[i+6] == 'O') {
                return true
            }
        }
    }
    if (s[4] == 'O') {
        if ((s[0] == 'O' && s[8] == 'O') || (s[2] == 'O' && s[6] == 'O')) {
            return true
        }
    }
    return false
}

