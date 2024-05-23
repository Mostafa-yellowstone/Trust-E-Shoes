package com.example.truste_shoes.adapters

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.truste_shoes.Model.BrandModel
import com.example.truste_shoes.R
import com.example.truste_shoes.databinding.ViewholderBrandBinding
import com.example.truste_shoes.databinding.ViewholderColorBinding
import com.example.truste_shoes.databinding.ViewholderSizeBinding

class SizeAdapter(val items : MutableList<String>):RecyclerView.Adapter<SizeAdapter.Viewholder>() {
    private var selectedPosition = -1
    private var lastPositionSelected = -1
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeAdapter.Viewholder {
        context = parent.context
        val view = ViewholderSizeBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return Viewholder(view)
    }
    override fun onBindViewHolder(holder: SizeAdapter.Viewholder, position: Int) {

        holder.binding.sizeTxt.text = items[position]

        holder.binding.root.setOnClickListener{
            lastPositionSelected = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastPositionSelected)
            notifyItemChanged(selectedPosition)
        }

        if (selectedPosition == position){
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg_selected)
            holder.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.purple))
        }else{
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.sizeTxt.setTextColor(context.resources.getColor(R.color.black))
        }
    }
    override fun getItemCount(): Int  = items.size
    class Viewholder(val binding:ViewholderSizeBinding): RecyclerView.ViewHolder(binding.root) {
    }
}