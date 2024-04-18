package ch.zindustries.dmzapp

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

val tokenizedWords: MutableMap<String, Int> = HashMap()

private const val TOKENS_FILE = "dmz-app/src/main/resources/tokens.map"

class Tokenizer {

    init { tokenizedWords.putAll(FileOperator.getTokens()) }

    fun tokenizeContent(content: String): Map<Int, Int> {

        val sentences = content.split(".\n")
            .map { it.replace(Regex("[,:;.!?\"'()-]"), "") }
            .map { it.split(" ") }

        val sentenceSizes = listOf(0) + sentences.map { it.size }

        var startIndex = 0

        return sentences.mapIndexed { i, sentence ->
                startIndex += sentenceSizes[i]
                tokenizeWords(sentence, startIndex)
            }
            .flatMap { it.entries }
            .associate { it.key to it.value }
    }

    private fun tokenizeWords(words: List<String>, sentenceIndex: Int): Map<Int, Int> {

        return words.mapIndexed { i, word ->
            Pair(i + sentenceIndex, tokenizeWord(word))
        }.toMap()
    }

    private fun tokenizeWord(word: String): Int {

        var token = tokenizedWords[word]

        if (token == null) {
            token = tokenizedWords.keys.size
            tokenizedWords[word] = token
            FileOperator.writeToken(word, token)
        }

        return token
    }
}

class FileOperator {
    companion object {

        fun getTokens(): List<Pair<String, Int>> {
            return File(TOKENS_FILE).readLines()
                .map { it.split(";") }.flatten()
                .filter { it.isNotEmpty() }
                .map { it.split("=") }
                .map { (k, v) -> Pair(k, v.toInt()) }
        }

        fun writeToken(word: String, token: Int) {
            BufferedWriter(FileWriter(TOKENS_FILE, true)).use {
                it.write("$word=$token;")
            }
        }
    }
}
