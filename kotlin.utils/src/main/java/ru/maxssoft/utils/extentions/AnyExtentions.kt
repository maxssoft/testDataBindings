package ru.maxssoft.utils.extentions

/**
 * @author m.sidorov
 */

fun <T> T?.isNull(block: () -> T): T {
    return this?: block()
}

fun <T> T?.isNull(emptyValue: T): T {
    return this?: emptyValue
}

