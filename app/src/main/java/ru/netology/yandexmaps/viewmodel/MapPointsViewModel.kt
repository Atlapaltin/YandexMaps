package ru.netology.yandexmaps.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.yandex.mapkit.geometry.Point
import ru.netology.yandexmaps.repository.MapPointsRepositoryImpl
import ru.netology.yandexmaps.db.AppDb
import ru.netology.yandexmaps.dto.MapPoint
import ru.netology.yandexmaps.repository.MapPointsRepository

class MapPointsViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: MapPointsRepository = MapPointsRepositoryImpl(
        AppDb.getInstance(application).mapPointsDao()
    )
    val data: LiveData<List<MapPoint>> = repository.getAll()
    fun insert(mapPoint: MapPoint) = repository.insert(mapPoint)
    fun removeById(id: Long) = repository.removeById(id)

    var selectedPoint: MapPoint? = null
    var currentPosition: Point = Point(55.751574, 37.573856)
}