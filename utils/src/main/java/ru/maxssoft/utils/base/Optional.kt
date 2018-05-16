package ru.maxssoft.utils.base

/**
 * @author m.sidorov
 */
class Optional<out T>(private val value: T) {
    fun get(): T = value

    val isPresent: Boolean
        get() = value != null
}