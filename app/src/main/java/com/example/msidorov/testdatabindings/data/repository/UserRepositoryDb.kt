package com.example.msidorov.testdatabindings.data.repository

import com.example.msidorov.testdatabindings.data.database.AppDatabase
import com.example.msidorov.testdatabindings.data.entity.UserEntity
import com.example.msidorov.testdatabindings.domain.model.User
import com.example.msidorov.testdatabindings.domain.repository.UserLoadersFabric
import com.example.msidorov.testdatabindings.domain.repository.UserRepository

import ru.maxsssoft.recordrepository.QueryLoader
import ru.maxsssoft.recordrepository.RecordLoader

/**
 * @author m.sidorov
 */
class UserRepositoryDb(val db: AppDatabase) : UserRepository {

    override fun newRecord(): User {
        return UserEntity()
    }

    override fun update(record: User) {
        if (record.id == 0L){
            db.userDao().insert(record as UserEntity)
        } else {
            db.userDao().update(record as UserEntity)
        }
    }

    override fun delete(record: User) {
        db.userDao().delete(record as UserEntity)
    }

    override val loaders: UserLoadersFabric = object : UserLoadersFabric {

        override fun byId(id: Int) = object : RecordLoader<User> {
            override fun load(): User? {
                return db.userDao().loadById(id)
            }
        }

        override fun allUsers() = object : QueryLoader<User> {
            override fun load(): List<User> {
                return db.userDao().loadAll()
            }
        }

        override fun byUserName(userName: String) = object : QueryLoader<User> {
            override fun load(): List<User> {
                return db.userDao().loadByUserName(userName)
            }
        }

        override fun byLogin(login: String) = object : RecordLoader<User>{
            override fun load(): User? {
                return db.userDao().loadByLogin(login)
            }
        }

    }

}
