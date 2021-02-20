package com.djeneral.a7minutesworkout.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.djeneral.a7minutesworkout.R
import com.djeneral.a7minutesworkout.classes.ExerciseModel
import com.djeneral.a7minutesworkout.databinding.ItemExerciseStatusBinding

class ExerciseStatusAdapter(val items: ArrayList<ExerciseModel>, val context: Context) : RecyclerView.Adapter<ExerciseStatusAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemExerciseStatusBinding) : RecyclerView.ViewHolder(binding.root){
        val tvItem = binding.tvItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding = ItemExerciseStatusBinding
//                .inflate(LayoutInflater.from(parent.context), parent, false)
//        return ViewHolder(binding)

        return ViewHolder(ItemExerciseStatusBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: ExerciseModel = items[position]
        holder.tvItem.text = model.getId().toString()
        when {
            model.getIsSelected() -> {
                holder.tvItem.background = ContextCompat.getDrawable(context, R.drawable.item_circular_color_green_background)
                holder.tvItem.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            }
            model.getIsCompleted() -> {
                holder.tvItem.background = ContextCompat.getDrawable(context, R.drawable.item_circular_color_accent_background)
                holder.tvItem.setTextColor(ContextCompat.getColor(context, R.color.white))
            }
            else -> {
                holder.tvItem.background = ContextCompat.getDrawable(context, R.drawable.item_circular_color_gray_background)
                holder.tvItem.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary))
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}