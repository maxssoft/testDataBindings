package ru.maxsssoft.recordrepository

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

import ru.maxssoft.utils.base.*

/**
 * @author m.sidorov
 *
 * Расширения, оборачивающие RecordRepository в RX вызовы
 */
fun <R> RecordRepository<R>.rxUpdate(user: R): Completable {
    return Completable.fromAction { this.update(user) }
}

fun <R> RecordRepository<R>.rxDelete(user: R): Completable {
    return Completable.fromAction { this.delete(user) }
}

fun <R> RecordRepository<R>.rxDelete(processor: DeleteProcessor): Completable {
    return Completable.fromAction { this.delete(processor) }
}

fun <R> RecordRepository<R>.rxQuery(loader: RecordLoader<R>): Maybe<R> {
    return Single
            .fromCallable {
                Optional(this.query(loader))
            }
            .filter { t -> t.isPresent }
            .map { t -> t.get()!! }
}

fun <R> RecordRepository<R>.rxQuery(loader: QueryLoader<R>): Single<List<R>> {
    return Single.fromCallable {
        this.query(loader)
    }
}

