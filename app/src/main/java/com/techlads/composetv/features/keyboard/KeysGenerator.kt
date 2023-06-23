package com.techlads.composetv.features.keyboard

object KeysGenerator {
    val alphabet = lazy { ('A'..'Z').toList() }
    val specialCharV1 = lazy { listOf("-", "'") }
    val alphabetLower = lazy { ('a'..'z').toList() }
    val numbers = lazy { ('0'..'9').toList() }
}