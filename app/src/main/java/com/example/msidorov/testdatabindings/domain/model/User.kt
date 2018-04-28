package com.example.msidorov.testdatabindings.domain.model

import com.example.msidorov.testdatabindings.utils.Hash

/**
 * @author m.sidorov
 */
interface User {

    val id: Long

    var userName: String

    var login: String

    var description: String

    val passwordHash: String

    val contacts: List<Contact>?

    fun setPassword(password: String)

    fun calculatePasswordHash(password: String) : String {
        return Hash.md5(password);
    }
}