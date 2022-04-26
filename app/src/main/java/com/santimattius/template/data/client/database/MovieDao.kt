package com.santimattius.template.data.client.database

import androidx.room.*
import com.santimattius.template.data.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    fun getAll(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE id=:id")
    suspend fun findById(id: String): MovieEntity

    @Query("SELECT COUNT(id) FROM movie")
    fun count(): Int

    @Insert
    suspend fun insertAll(vararg movies: MovieEntity)

    @Delete
    suspend fun delete(movies: MovieEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(movies: MovieEntity)

}