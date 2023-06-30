package ru.netology.yandexmaps.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.yandexmaps.R
import ru.netology.yandexmaps.adapter.OnInteractionListener
import ru.netology.yandexmaps.adapter.PointsAdapter
import ru.netology.yandexmaps.databinding.FragmentShowPointsBinding
import ru.netology.yandexmaps.dto.MapPoint
import ru.netology.yandexmaps.viewmodel.MapPointsViewModel

class ShowPointsFragment : Fragment() {
    private val viewModel: MapPointsViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentShowPointsBinding.inflate(inflater, container, false)

        val adapter = PointsAdapter(object : OnInteractionListener {
            override fun onClick(mapPoint: MapPoint) {
                viewModel.selectedPoint = mapPoint
                viewModel.currentPosition = mapPoint.point
                findNavController().navigateUp()
            }

            override fun onEdit(mapPoint: MapPoint) {
                viewModel.selectedPoint = mapPoint
                viewModel.currentPosition = mapPoint.point
                findNavController().navigate(R.id.action_showPointsFragment_to_addPointFragment)
            }

            override fun onRemove(mapPoint: MapPoint) {
                viewModel.removeById(mapPoint.id)
            }
        })

        binding.list.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { state ->
            adapter.submitList(state)
        }

        return binding.root
    }
}