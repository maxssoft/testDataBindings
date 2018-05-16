package com.example.msidorov.testdatabindings.data.dao

import android.arch.persistence.room.*
import com.example.msidorov.testdatabindings.data.entity.UserEntity
import io.reactivex.Flowable

/**
 * @author m.sidorov
 */
@Dao interface UserDao {

    @Query("select * from users")
    fun loadAll(): List<UserEntity>

    @Query("select * from users where id = :id")
    fun loadById(id: Int): UserEntity?

    @Query("select * from users where login = :login")
    fun loadByLogin(login: String): UserEntity

    @Query("select * from users where user_name like :userName")
    fun loadByUserName(userName: String): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: UserEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(user: UserEntity)

    @Delete
    fun delete(user: UserEntity)

/*
    @Query("DELETE FROM Users")
    fun delete()

    @Transaction
    private fun deleteAll(user: UserEntity){
        delete(user)
        delete()
    }
*/

}