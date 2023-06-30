package ru.netology.yandexmaps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.yandexmaps.databinding.PointLineBinding
import ru.netology.yandexmaps.dto.MapPoint

interface OnInteractionListener {
    fun onClick(mapPoint: MapPoint)
    fun onEdit(mapPoint: MapPoint)
    fun onRemove(mapPoint: MapPoint)
}

class PointsAdapter(
    private val onInteractionListener: OnInteractionListener,
) : ListAdapter<MapPoint, PointLineViewHolder>(PostDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointLineViewHolder {
        val binding = PointLineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PointLineViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: PointLineViewHolder, position: Int) {
        val pointLine = getItem(position)
        holder.bind(pointLine)
    }
}

class PointLineViewHolder(
    private val binding: PointLineBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(mapPoint: MapPoint) {
        binding.apply {
            description.text = mapPoint.text
            latitudeValue.text = mapPoint.point.latitude.toString()
            longitudeValue.text = mapPoint.point.longitude.toString()

            binding.root.setOnClickListener {
                onInteractionListener.onClick(mapPoint)
            }
            edit.setOnClickListener {
                onInteractionListener.onEdit(mapPoint)
            }
            remove.setOnClickListener {
                onInteractionListener.onRemove(mapPoint)
            }
        }
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<MapPoint>() {
    override fun areItemsTheSame(oldItem: MapPoint, newItem: MapPoint): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MapPoint, newItem: MapPoint): Boolean {
        return oldItem == newItem
    }
}
