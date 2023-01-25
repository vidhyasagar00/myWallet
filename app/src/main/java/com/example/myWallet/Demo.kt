package com.example.myWallet

object Demo {
    @JvmStatic
    fun main(a: Array<String>) {
        print(joinOptions(listOf("a", "b", "c")))
    }

    fun joinOptions(options: Collection<String>) =
        options.joinToString(prefix = "(", postfix = ")", separator = " | ")
}