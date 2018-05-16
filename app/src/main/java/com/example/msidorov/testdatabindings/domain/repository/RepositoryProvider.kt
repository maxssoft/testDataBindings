package com.example.msidorov.testdatabindings.domain.repository

import com.example.msidorov.testdatabindings.data.database.AppDatabase
import com.example.msidorov.testdatabindings.data.repository.UserRepositoryDb

/**
 * @author m.sidorov
 */
class RepositoryProvider(val database: AppDatabase ) {

    val userRepository: UserRepository by lazy {
        UserRepositoryDb(database)
    }

}