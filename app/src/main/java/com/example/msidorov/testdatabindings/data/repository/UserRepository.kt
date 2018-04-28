package com.example.msidorov.testdatabindings.data.repository

import com.example.msidorov.testdatabindings.data.database.AppDatabase
import com.example.msidorov.testdatabindings.data.entity.UserEntity
import com.example.msidorov.testdatabindings.domain.model.User
import com.example.msidorov.testdatabindings.domain.repository.base.QueryLoader
import com.example.msidorov.testdatabindings.domain.repository.base.RecordRepository

/**
 * @author m.sidorov
 */
class UserRepository(val db: AppDatabase) : RecordRepository<User> {

    override fun newRecord(): User {
        return UserEntity()
    }

//    override fun<Int> load(id: Int): User? {
//        return db.userDao().loadById(id)
//    }

//    override fun load(id: Int): User? {
//        return db.userDao().loadById(id);
//    }

    override fun update(record: User) {
        db.userDao().update(record as UserEntity)
    }

    override fun delete(record: User) {
        db.userDao().delete(record as UserEntity)
    }

    // Загрузка всех пользователей
    val allRecordsLoader = object : QueryLoader<User> {
        override fun load(): List<User> {
            return db.userDao().loadAll()
        }
    }

    inner class LoaderByLogin(val login: String) : QueryLoader<User> {
        override fun load(): List<User> {
            db.query()
            return listOf(db.userDao().loadByLogin(login))
        }
    }

    fun ds() = query(allRecordsLoader)

    fun dd() = query(LoaderByLogin("dffd"))

    fun dsa() {
        (name, age) = getUser()
    }

}

// Выборка по логину
class ByLoginLoader(val db: AppDatabase, val login: String) : QueryLoader<User> {
    override fun load(): List<User> {
        return listOf(db.userDao().loadByLogin(login))
    }
}

// Выборка по имени пользователя (like)
class userNameLikeCondition(userName: String) : RecordQueryCondition<String> {
    override val condition: String = "user_name like \"%$userName\"%"
}

