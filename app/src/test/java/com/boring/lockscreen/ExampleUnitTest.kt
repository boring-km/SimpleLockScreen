package com.boring.lockscreen

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun 소수점_제거_확인() {
        var testValue = String.format("%.0f", 1.234)
        assertEquals("1", testValue)
    }
}