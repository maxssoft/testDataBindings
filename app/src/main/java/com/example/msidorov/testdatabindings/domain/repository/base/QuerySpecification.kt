package com.example.msidorov.testdatabindings.domain.repository.base

/**
 * @author m.sidorov
 */

// Абстрактный загрузчик данных, выполняющий загрузку данных из хранилища
interface QueryLoader<R> {
    fun load(): List<R>
}

// Абстрактный интерфейс, удаляющий данные из хранилища
interface DeleteProcessor {
    fun run()
}
