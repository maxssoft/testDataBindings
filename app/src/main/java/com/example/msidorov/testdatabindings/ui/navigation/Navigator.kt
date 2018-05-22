package com.example.msidorov.testdatabindings.ui.navigation

import android.os.Bundle
import com.example.msidorov.testdatabindings.events.OpenFragmentEvent
import com.example.msidorov.testdatabindings.ui.fragments.FragmentType
import com.example.msidorov.testdatabindings.ui.fragments.factory.ss.FragmentFactory
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import org.reactivestreams.Publisher
import java.util.*

/**
 * @author m.sidorov
 */
object Navigator {

    const val PARAM_RECORD_ID = "record_id"

    fun openUserCard(userId: Long){
        OpenFragmentEvent.post(OpenFragmentData(
                fragmentType = FragmentType.USER_CARD,
                data = Bundle().apply { putLong(PARAM_RECORD_ID, userId) }
        ))
    }

}