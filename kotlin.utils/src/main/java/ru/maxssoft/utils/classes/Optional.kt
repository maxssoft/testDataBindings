package ru.maxssoft.utils.classes

/**
 * @author m.sidorov
 */
class Optional<out T>(val value: T) {
    fun get(): T = value

    val isPresent: Boolean
        get() = value != null
}