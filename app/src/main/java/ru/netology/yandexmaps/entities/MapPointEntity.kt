package ru.netology.yandexmaps.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yandex.mapkit.geometry.Point
import ru.netology.yandexmaps.dto.MapPoint

@Entity
data class MapPointEntity(
    @PrimaryKey
    val id: Long,
    val text: String,
    val pointLatitude: Double,
    val pointLongitude: Double
) {
    fun toDto() = MapPoint(
        id,
        text,
        Point(pointLatitude, pointLongitude)
    )

    companion object {
        fun fromDto(dto: MapPoint) =
            MapPointEntity(
                dto.id, dto.text, dto.point.latitude, dto.point.longitude
            )
    }
}