package com.example.msidorov.testdatabindings

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService
import ru.maxssoft.utils.extentions.isNull
import ru.maxsssoft.recordrepository.rxQuery
import ru.maxsssoft.recordrepository.rxUpdate

/**
 * @author m.sidorov
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        testDatabase()

        // SQLiteStudioService.instance().setPort(9999);
        SQLiteStudioService.instance().start(this)
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
                .subscribe({ Log.e("testDatabase", "insert record complete") }, { showError("error of insert record", it) })

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
                .subscribe({ Log.e("testDatabase", "update record complete") }, { showError("error of update record", it) })
    }

    private fun showError(message: String, error: Throwable? = null){
        Log.e("testDatabase", "error: ${message.isNull("")}", error)
        Toast.makeText(this, "$message : ${error?.message.isNull("")}", Toast.LENGTH_LONG)
    }

    override fun onStop() {
        SQLiteStudioService.instance().stop()

        super.onStop()
    }



    companion object {

        internal fun f() {
            val s: String? = null
        }
    }

}
