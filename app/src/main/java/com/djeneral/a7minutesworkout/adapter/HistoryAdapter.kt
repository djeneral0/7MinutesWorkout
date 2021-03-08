package com.djeneral.a7minutesworkout.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.djeneral.a7minutesworkout.databinding.ItemHistoryRowBinding

class HistoryAdapter(val context: Context, val items: ArrayList<String>) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>(){

    class  ViewHolder(val binding: ItemHistoryRowBinding) : RecyclerView.ViewHolder(binding.root){
        val llHistory = binding.llHistoryItemMain
        val tvItem = binding.tvItem
        val tvPosition = binding.tvPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemHistoryRowBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date: String = items[position]

        holder.tvPosition.text = (position + 1).toString()
        holder.tvItem.text = date

        when{
            position % 2 == 0 ->{ holder.llHistory.setBackgroundColor(Color.parseColor("#EBEBEB")) }
            else ->{ holder.llHistory.setBackgroundColor(Color.parseColor("#FFFFFF")) }
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }
}