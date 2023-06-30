package ru.netology.yandexmaps.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.netology.yandexmaps.entities.MapPointEntity

@Dao
interface MapPointsDao {
    @Query("SELECT * FROM MapPointEntity")
    fun getAll(): LiveData<List<MapPointEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(mapPoint: MapPointEntity)

    @Query("DELETE FROM MapPointEntity WHERE id = :id")
    fun removeById(id: Long)
}