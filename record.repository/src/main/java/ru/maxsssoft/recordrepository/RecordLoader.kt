package ru.maxsssoft.recordrepository

/**
 * @author m.sidorov
 *
 * Абстрактный загрузчик данных, выполняющий загрузку одной записи из хранилища
 */

interface RecordLoader<R> {
    fun load(): R?
}