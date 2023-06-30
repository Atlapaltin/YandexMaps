package ru.netology.yandexmaps.repository

import androidx.lifecycle.LiveData
import ru.netology.yandexmaps.dto.MapPoint

interface MapPointsRepository {
    fun getAll(): LiveData<List<MapPoint>>
    fun insert(mapPoint: MapPoint)
    fun removeById(id: Long)
}