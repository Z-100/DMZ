package ch.zindustries.dmzapp

import java.io.File

private const val TEST_FILE = "dmz-app/src/main/resources/test.txt"

fun main() {

    val tokenizer = Tokenizer()

    val fileContent = File(TEST_FILE).readLines().joinToString("\n")

    val tokenizedFile = tokenizer.tokenizeContent(fileContent)

    println(tokenizedFile)
}

