package sorting

import java.io.File
import java.lang.NumberFormatException
import java.util.*



class CommandHandler {

    companion object {
        fun handle(sortingType: String, dataType: String, fileNameIn: String) {
            when(dataType) {
                "long" -> sortLongs(sortingType, fileNameIn)
                "line" -> sortLines(sortingType, fileNameIn)
                "word" -> sortWords(sortingType, fileNameIn)
            }
        }
    }
}

fun printMy(line: String) {
    if (!outFile.exists())  {
        println(line)
    } else {
        outFile.writeText(line)
    }
}

fun sortLongs(sortingType: String, fileName: String) {
    var sc = Scanner(System.`in`)
    val file = File(fileName)
    if (file.exists()) {
        sc = Scanner(file)
    }

    val numberList = mutableListOf<Long>()
    while (sc.hasNext()) {
        val number = sc.next()
        try {
            numberList.add(number.toLong())
        } catch (e: NumberFormatException) {
            println("$number is not a long. It will be skipped.")
        }
    }
    sc.close()

    printMy("Total numbers: ${numberList.size}.")
    if (sortingType == "natural") {
        printMy("Sorted data: ${numberList.sorted().joinToString(" ")}")
    } else {
        val numberMap = numberList.sorted().associateBy( { it }, { 0 } ).toMutableMap()
        numberMap.entries.forEach {
            val key = it.key
            numberMap[key] = numberList.count { it == key }
        }
        val map = numberMap.toList().sortedBy { (key, value) -> value }.toMap()
        map.forEach {  printMy("${it.key}: ${it.value} time(s), ${it.value * 100 / numberList.size}%")  }
    }
}

fun sortWords(sortingType: String, fileName: String) {
    var sc = Scanner(System.`in`)
    val file = File(fileName)
    if (file.exists()) {
        sc = Scanner(file)
    }
    val wordsList = mutableListOf<String>()
    while (sc.hasNext()) {
        wordsList.add(sc.next())
    }
    sc.close()
    printMy("Total words: ${wordsList.size}.")
    if (sortingType == "natural") {
        printMy("Sorted data: ${wordsList.sorted().joinToString(" ")}")
    } else {
        val wordsMap = wordsList.sorted().associateBy( { it }, { 0 } ).toMutableMap()
        wordsMap.entries.forEach {
            val key = it.key
            wordsMap[key] = wordsList.count { it == key }
        }
        val map = wordsMap.toList().sortedBy { (key, value) -> value }.toMap()
        map.forEach {  printMy("${it.key}: ${it.value} time(s), ${it.value * 100 / wordsList.size}%")  }
    }
}

fun sortLines(sortingType: String, fileName: String) {
    var sc = Scanner(System.`in`)
    val file = File(fileName)
    if (file.exists()) {
        sc = Scanner(file)
    }
    val linesList = mutableListOf<String>()
    while (sc.hasNextLine()) {
        linesList.add(sc.nextLine())
    }
    sc.close()

    printMy("Total lines: ${linesList.size}.")
    if (sortingType == "natural") {
        printMy("Sorted data: ${linesList.sorted().joinToString("\n")}")
    } else {
        val wordsMap = linesList.sorted().associateBy( { it }, { 0 } ).toMutableMap()
        wordsMap.entries.forEach {
            val key = it.key
            wordsMap[key] = linesList.count { it == key }
        }
        val map = wordsMap.toList().sortedBy { (key, value) -> value }.toMap()
        map.forEach {  printMy("${it.key}: ${it.value} time(s), ${it.value * 100 / linesList.size}%")  }
    }
}