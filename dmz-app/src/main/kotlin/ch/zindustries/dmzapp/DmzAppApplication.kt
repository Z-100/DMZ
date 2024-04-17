package ch.zindustries.dmzapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DmzAppApplication

fun main(args: Array<String>) {
    runApplication<DmzAppApplication>(*args)
}
