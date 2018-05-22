package com.example.msidorov.testdatabindings.events

import com.example.msidorov.testdatabindings.ui.navigation.OpenFragmentData
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Класс, порождающий события события и подписку на них
 *
 * @author m.sidorov
 */
object ErrorEvent {

    private val subject: PublishSubject<Throwable> = PublishSubject.create()

    val source: Observable<Throwable>
        get() = subject

    fun post(error: Throwable){
        subject.onNext(error)
    }

}