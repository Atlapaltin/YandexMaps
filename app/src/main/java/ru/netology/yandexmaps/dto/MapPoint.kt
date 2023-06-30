package ru.netology.yandexmaps.dto

import com.yandex.mapkit.geometry.Point

data class MapPoint(
    val id: Long,
    val text: String,
    val point: Point
)