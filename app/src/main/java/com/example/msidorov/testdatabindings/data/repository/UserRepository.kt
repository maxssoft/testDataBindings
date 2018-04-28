package com.example.msidorov.testdatabindings.data.repository

import com.example.msidorov.testdatabindings.data.database.AppDatabase
import com.example.msidorov.testdatabindings.data.entity.UserEntity
import com.example.msidorov.testdatabindings.domain.model.User
import com.example.msidorov.testdatabindings.domain.repository.base.RecordQueryCondition
import com.example.msidorov.testdatabindings.domain.repository.base.RecordRepository

/**
 * @author m.sidorov
 */
class UserRepository(val db: AppDatabase) : RecordRepository<User> {

    override fun newRecord(): User {
        return UserEntity()
    }

    override <Int> fun load(id: Int): User? {
        return db.userDao().loadById(id);
    }

    override fun update(record: User) {
        db.userDao().update(record as UserEntity)
    }

    override fun delete(record: User) {
        db.userDao().delete(record as UserEntity)
    }

    override fun <String> query(queryCondition: RecordQueryCondition<String>): List<User> {
        db.query("select * from user where ${queryCondition.condition}")
    }

    override fun <Q> deleteBy(deleteCondition: RecordQueryCondition<Q>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}

// Выборка по логину
class LoginCondition(login: String) : RecordQueryCondition<String> {
    override val condition: String = "login = \"$login\""
}

// Выборка по имени пользователя (like)
class userNameLikeCondition(userName: String) : RecordQueryCondition<String> {
    override val condition: String = "user_name like \"%$userName\"%"
}

