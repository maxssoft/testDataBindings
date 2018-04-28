package com.example.msidorov.testdatabindings.domain.repository.base

import io.reactivex.Completable
import io.reactivex.Single

/**
 * Базовый интерфейс для репоизториев, основанных на табличных записях (строках)
 * R - класс модели, связанный с записью в таблице
 * Q - класс, описывающий условия выборок записей
 *
 * @author m.sidorov
 */
interface RecordRepository<R> {
    // создает экземпляр модели для новой записи
    fun newRecord(): R

    // загружает запись по первичному ключу
    fun <PK> load(id: PK): R?

    // сохраняет запись в хранилище
    fun update(record: R)

    // удаляет запись
    fun delete(record: R)

    // загружает записи из хранилища по условию выборки
    fun <Q> query(queryCondition: RecordQueryCondition<Q>): List<R>

    // удаляет записи по условию
    fun <Q> deleteBy(deleteCondition: RecordQueryCondition<Q>)
}

// Общий интерфейс, определяющий условие выборок для RecordRepository
interface RecordQueryCondition<Q> {
    val condition: Q
}

// Пустое условие поиска
object EmptyCondition: RecordQueryCondition<Boolean> {
    override val condition: Boolean = true
}


