package com.example.msidorov.testdatabindings.domain.repository.base

import com.example.msidorov.testdatabindings.utils.isNull
import io.reactivex.Completable
import io.reactivex.Single

/**
 * Расширения, оборачивающие RecordRepository в RX
 *
 * @author m.sidorov
 */
fun <R, PK> RecordRepository<R>.rxLoad(id: PK): Single<R> {
    return Single.fromCallable { this.load(id).isNull(this.newRecord()) }
}

fun <R> RecordRepository<R>.rxUpdate(user: R): Completable {
    return Completable.fromAction { this.update(user) }
}

fun <R> RecordRepository<R>.rxDelete(user: R): Completable {
    return Completable.fromAction { this.delete(user) }
}

fun <R, Q> RecordRepository<R>.rxQuery(condition: RecordQueryCondition<Q>): Single<List<R>> {
    return Single.fromCallable { this.query(condition) }
}

fun <R, Q> RecordRepository<R>.rxDeleteBy(condition: RecordQueryCondition<Q>): Completable {
    return Completable.fromAction { this.deleteBy(condition) }
}
