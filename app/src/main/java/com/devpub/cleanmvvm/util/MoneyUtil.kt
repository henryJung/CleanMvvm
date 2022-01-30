package com.devpub.cleanmvvm.util

import java.lang.Exception
import java.text.NumberFormat
import java.util.*

object MoneyUtil {

    private const val UNIT = "원"
    private const val MAX_LENGTH = 12

    fun String.isMaxLength(): Boolean {
        return this.length >= MAX_LENGTH
    }

    fun Long.comma(): String {
        return NumberFormat.getInstance(Locale.KOREA).format(this)
    }

    fun Long.commaWon(): String {
        return this.comma() + UNIT
    }

    fun Int.comma(): String {
        return NumberFormat.getInstance(Locale.KOREA).format(this)
    }

    fun Int.commaWon(): String {
        return this.comma() + UNIT
    }

    fun Int.commaManWon(): String {
        return "${this.comma()}만$UNIT"
    }

    fun String.withoutCommaWon(): Long {
        return try {
            this.replace(",", "").replace(UNIT, "").toLong()
        } catch (e: Exception) {
            0
        }
    }
}