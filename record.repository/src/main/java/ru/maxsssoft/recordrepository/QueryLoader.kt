package ru.maxsssoft.recordrepository

/**
 * @author m.sidorov
 *
 * Абстрактный загрузчик данных, выполняющий загрузку данных из хранилища
 */

interface QueryLoader<R> {
    fun load(): List<R>
}