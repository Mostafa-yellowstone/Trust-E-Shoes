package com.example.truste_shoes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.truste_shoes.R
import com.example.truste_shoes.databinding.ViewholderColorBinding

class ColorAdapter(val items : MutableList<String>):RecyclerView.Adapter<ColorAdapter.Viewholder>() {
    private var selectedPosition = -1
    private var lastPositionSelected = -1
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorAdapter.Viewholder {
        context = parent.context
        val view = ViewholderColorBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return Viewholder(view)
    }
    override fun onBindViewHolder(holder: ColorAdapter.Viewholder, position: Int) {


        Glide.with(holder.itemView.context)
            .load(items[position])
            .into(holder.binding.pic)

        holder.binding.root.setOnClickListener{
            lastPositionSelected = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastPositionSelected)
            notifyItemChanged(selectedPosition)
        }
        if (selectedPosition == position){
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg_selected)
        }else{
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg)
        }
    }
    override fun getItemCount(): Int  = items.size
    class Viewholder(val binding:ViewholderColorBinding): RecyclerView.ViewHolder(binding.root) {
    }
}