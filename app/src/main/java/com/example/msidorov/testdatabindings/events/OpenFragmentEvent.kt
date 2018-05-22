package com.example.msidorov.testdatabindings.events

import com.example.msidorov.testdatabindings.ui.navigation.OpenFragmentData
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * Класс, порождающий события открытия фрагментов и подписку на них
 *
 * @author m.sidorov
 */
object OpenFragmentEvent {

    private val subject: PublishSubject<OpenFragmentData> = PublishSubject.create()

    val source: Observable<OpenFragmentData>
        get() = subject

    fun post(openFragmentData: OpenFragmentData){
        subject.onNext(openFragmentData)
    }

}