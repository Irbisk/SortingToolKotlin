package sorting

import java.io.File
import java.util.Scanner

var outFile: File = File("11")

fun main(args: Array<String>) {
    var sortingType = "natural"
    var dataType = "word"
    var outFileName = ""
    var inFileName = ""

    for (s in args.indices) {
        if (args[s].contains("-")) {
            if (args[s] == "-sortingType") {
                try {
                    sortingType = args[s + 1]
                } catch (e: IndexOutOfBoundsException) {
                    println("No sorting type defined!")
                }
            } else if (args[s] == "-dataType") {
                try {
                    dataType = args[s + 1]
                } catch (e: IndexOutOfBoundsException) {
                    println("No data type defined!")
                }
            } else if (args[s] == "-inputFile")  {
                inFileName = args[s + 1]
            } else if (args[s] == "-outputFile") {
                outFileName = args[s + 1]
                outFile = File(outFileName)
                outFile.createNewFile()
            } else {
                println("${args[s]} is not a valid parameter. It will be skipped.")
            }
        }
    }



    CommandHandler.handle(sortingType, dataType, inFileName)

}



