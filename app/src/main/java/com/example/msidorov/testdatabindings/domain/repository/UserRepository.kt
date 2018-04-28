package com.example.msidorov.testdatabindings.domain.repository

import com.example.msidorov.testdatabindings.domain.model.User
import com.example.msidorov.testdatabindings.domain.repository.base.EmptyCondition
import com.example.msidorov.testdatabindings.domain.repository.base.RecordQueryCondition
import com.example.msidorov.testdatabindings.domain.repository.base.RecordRepository
import com.example.msidorov.testdatabindings.domain.repository.base.rxQuery
import io.reactivex.Maybe
import io.reactivex.Single

/**
 * Репозиторий для пользователей
 *
 * @author m.sidorov
 */
interface UserRepository : RecordRepository<User> {

    override fun <Int> load(id: Int): User

}

// Выборка по логину
class LoginCondition(login: String) : RecordQueryCondition<String> {
    override val condition: String = "login = \"$login\""
}

// Выборка по имени пользователя (like)
class userNameLikeCondition(userName: String) : RecordQueryCondition<String> {
    override val condition: String = "user_name like \"%$userName\"%"
}

fun loadAll(): Single<List<User>> {
    val userRepo: UserRepository
    return userRepo
            .rxQuery(EmptyCondition)
}

fun loadByLogin(login: String): Maybe<User>{
    val userRepo: UserRepository
    return userRepo
            .rxQuery(LoginCondition(login))
            .flatMapMaybe { list ->
                val user = list.firstOrNull()
                if (user != null) Maybe.just(user) else Maybe.empty()
            }
}

