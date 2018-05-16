package ru.maxsssoft.recordrepository

/**
 * Базовый интерфейс для репоизториев, основанных на табличных записях (строках)
 * R - класс модели, связанный с записью в таблице
 *
 * @author m.sidorov
 */
interface RecordRepository<R> {

    // создает экземпляр модели для новой записи
    fun newRecord(): R

    // загружает записи из хранилища по условию выборки, заданным во внешнем загрузчике
    fun query(loader: QueryLoader<R>): List<R> {
        return loader.load()
    }

    // загружает одну запись из хранилища по условию выборки, заданным во внешнем загрузчике
    fun query(loader: RecordLoader<R>): R? {
        return loader.load()
    }

    // сохраняет запись в хранилище
    fun update(record: R)

    // удаляет запись
    fun delete(record: R)

    // удаляет записи по логике, задаваемой во внешнем процессоре
    fun delete(processor: DeleteProcessor){
        processor.run()
    }

}

