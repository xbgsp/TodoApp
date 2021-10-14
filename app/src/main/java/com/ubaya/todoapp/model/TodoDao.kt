package com.ubaya.todoapp.model

import androidx.room.*

@Dao
interface TodoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg todo:Todo)

    @Query("SELECT * FROM todo")
    suspend fun selectAllTodo(): List<Todo>

    @Query("SELECT * FROM todo WHERE uuid= :id")
    suspend fun selectTodo(id:Int): Todo

    @Delete
    suspend fun deleteTodo(todo:Todo)
}