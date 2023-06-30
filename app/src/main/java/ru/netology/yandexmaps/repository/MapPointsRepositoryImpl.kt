package ru.netology.yandexmaps.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import ru.netology.yandexmaps.dao.MapPointsDao
import ru.netology.yandexmaps.dto.MapPoint
import ru.netology.yandexmaps.entities.MapPointEntity

class MapPointsRepositoryImpl(private val dao: MapPointsDao) : MapPointsRepository {
    override fun getAll(): LiveData<List<MapPoint>> =
        Transformations.map(dao.getAll()) { list ->
            list.map { mapEntity ->
                mapEntity.toDto()
            }
        }

    override fun insert(mapPoint: MapPoint) {
        dao.insert(MapPointEntity.fromDto(mapPoint))
    }

    override fun removeById(id: Long) {
        dao.removeById(id)
    }
}