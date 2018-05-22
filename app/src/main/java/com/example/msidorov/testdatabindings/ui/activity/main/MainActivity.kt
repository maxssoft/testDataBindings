package com.example.msidorov.testdatabindings.ui.activity.main

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.msidorov.testdatabindings.App
import com.example.msidorov.testdatabindings.R

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService
import ru.maxssoft.utils.extentions.isNull
import ru.maxsssoft.recordrepository.rxQuery
import ru.maxsssoft.recordrepository.rxUpdate
import com.example.msidorov.testdatabindings.databinding.ActivityMainBinding
import com.example.msidorov.testdatabindings.events.ErrorEvent
import com.example.msidorov.testdatabindings.ui.navigation.Navigator
import com.example.msidorov.testdatabindings.utils.UserException
import io.reactivex.disposables.CompositeDisposable

/**
 * @author m.sidorov
 */
    class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var vm: VM_MainActivity

    private var disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        vm = VM_MainActivity(this)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main);
        binding.setVM(vm)
    }

    override fun onStart() {
        super.onStart()
        vm.onStart()

        testDatabase()
        // SQLiteStudioService.instance().setPort(9999);
        SQLiteStudioService.instance().start(this)
    }

    override fun onStop() {
        SQLiteStudioService.instance().stop()

        vm.onStop()
        super.onStop()
    }

    private fun testDatabase() {
        val userRepository = App.instance.repositories.userRepository
        val users = userRepository.query(userRepository.loaders.allUsers())

        val lastId = users.lastOrNull()?.id.isNull(0) + 1
        val newUser = userRepository.newRecord()
        newUser.apply {
            login = "user_${lastId}"
            userName = login
            description = "description $login"
            setPassword(login)
        }
        userRepository.rxUpdate(newUser)
                .subscribeOn(Schedulers.io())
                .subscribe({ Log.e("testDatabase", "insert record complete") }, { ErrorEvent.post(UserException("error of insert record", it)) })

        userRepository.rxQuery(userRepository.loaders.byUserName("user%"))
                .flatMapCompletable {
                    if (!it.isEmpty()){
                        it.get(0).userName = it.get(0).userName
                        userRepository.rxUpdate(it.get(0))
                    } else {
                        Completable.complete()
                    }
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ Log.e("testDatabase", "update record complete") }, { ErrorEvent.post(UserException("error of update record", it)) })

        Navigator.openUserCard(1)
    }

}
