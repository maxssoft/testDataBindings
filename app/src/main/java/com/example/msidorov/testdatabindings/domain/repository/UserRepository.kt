package com.example.msidorov.testdatabindings.domain.repository

import com.example.msidorov.testdatabindings.domain.model.User
import ru.maxsssoft.recordrepository.QueryLoader
import ru.maxsssoft.recordrepository.RecordLoader
import ru.maxsssoft.recordrepository.RecordRepository

/**
 * @author m.sidorov
 *
 * Репозиторий для пользователей
 */
interface UserRepository : RecordRepository<User> {

    val loaders: LoadersProvider

    // Интерфейс провайдера, возвращающего загрузчики для пользователей
    interface LoadersProvider {

        // Создает загрузчик по первичному ключу
        fun byId(id: Int): RecordLoader<User>

        // Создает загрузчик всех пользователей
        fun allUsers(): QueryLoader<User>

        // Создает загрузчик по логину
        fun byLogin(login: String): RecordLoader<User>

        // Создает загрузчик по имени пользователя (поддержка Like)
        fun byUserName(userName: String): QueryLoader<User>

    }

}
