package com.example.wallpaperapp

import org.junit.Test

import org.junit.Assert.*

class ExampleUnitTest {
    @Test
    fun testIsStrongPassword() {
//        assertEquals(4, 2 + 2)
        println(isStrongPassword("password"))

        assertEquals(false,isStrongPassword("password")) // false
        assertEquals(true,isStrongPassword("P@\$\$word123")) //P@$$word123 true

    }

    fun isStrongPassword(password: String): Boolean {
        val capitalRegex = Regex("[A-Z]")
//        val lowercaseRegex = Regex("[a-z]")
        val digitRegex = Regex("\\d")
        val specialCharRegex = Regex("[^A-Za-z0-9]")

        val hasCapital = capitalRegex.containsMatchIn(password)
//        val hasLowercase = lowercaseRegex.containsMatchIn(password)
        val hasDigit = digitRegex.containsMatchIn(password)
        val hasSpecialChar = specialCharRegex.containsMatchIn(password)

        return hasCapital && hasDigit && hasSpecialChar
    }
}