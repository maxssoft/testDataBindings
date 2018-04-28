package com.example.msidorov.testdatabindings.domain.repository.base

/**
 * Базовый интерфейс для репоизториев, основанных на табличных записях (строках)
 * R - класс модели, связанный с записью в таблице
 *
 * @author m.sidorov
 */
interface RecordRepository<R> {
    // создает экземпляр модели для новой записи
    fun newRecord(): R

    // загружает запись по первичному ключу
    fun <PK : Int> load(id: PK): R?

    // сохраняет запись в хранилище
    fun update(record: R)

    // удаляет запись
    fun delete(record: R)

    // загружает записи из хранилища по условию выборки
    fun query(loader: QueryLoader<R>): List<R> {
        return loader.load()
    }

    // удаляет записи по условию
    fun deleteBy(processor: DeleteProcessor){
        processor.run()
    }
}

