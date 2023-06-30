package ru.netology.yandexmaps.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.yandex.mapkit.geometry.Point
import ru.netology.yandexmaps.databinding.FragmentAddPointBinding
import ru.netology.yandexmaps.dto.MapPoint
import ru.netology.yandexmaps.viewmodel.MapPointsViewModel

class AddPointFragment : Fragment() {
    private val viewModel: MapPointsViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddPointBinding.inflate(
            inflater,
            container,
            false
        )
        with(binding) {
            val pointId = if (viewModel.selectedPoint is MapPoint) {
                header.text = "Редактирование точки"
                val selectedPoint = viewModel.selectedPoint as MapPoint
                latitudeValue.setText(selectedPoint.point.latitude.toString())
                longitudeValue.setText(selectedPoint.point.longitude.toString())
                descriptionValue.setText(selectedPoint.text)
                selectedPoint.id
            } else {
                header.text = "Добавление точки"
                latitudeValue.setText(viewModel.currentPosition.latitude.toString())
                longitudeValue.setText(viewModel.currentPosition.longitude.toString())
                delete.visibility = View.GONE
                (viewModel.data.value?.maxByOrNull { point -> point.id }?.id ?: 0) + 1
            }

            save.setOnClickListener {
                val newLatitudeValue = latitudeValue.text.toString().toDouble()
                val newLongitudeValue = longitudeValue.text.toString().toDouble()
                if (newLatitudeValue < -90 || newLatitudeValue > 90 ||
                    newLongitudeValue < -180 || newLongitudeValue > 180
                ) {
                    error.visibility = View.VISIBLE
                } else {
                    val mapPoint = MapPoint(
                        id = pointId,
                        text = descriptionValue.text.toString(),
                        point = Point(
                            newLatitudeValue,
                            newLongitudeValue
                        )
                    )
                    viewModel.insert(mapPoint)
                    viewModel.currentPosition = mapPoint.point
                    findNavController().navigateUp()
                }
            }

            delete.setOnClickListener {
                viewModel.removeById(pointId)
                findNavController().navigateUp()
            }

            cancel.setOnClickListener {
                findNavController().navigateUp()
            }
        }
        return binding.root
    }
}