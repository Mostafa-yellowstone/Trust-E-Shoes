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

class BrandAdapter(val items : MutableList<BrandModel>):RecyclerView.Adapter<BrandAdapter.Viewholder>() {
    private var selectedPosition = -1
    private var lastPositionSelected = -1
    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandAdapter.Viewholder {
        context = parent.context
        val view = ViewholderBrandBinding.inflate(LayoutInflater.from(context) , parent , false)
        return Viewholder(view)
    }
    override fun onBindViewHolder(holder: BrandAdapter.Viewholder, position: Int) {
        val item = items[position]
        holder.binding.title.text = item.title
        Glide.with(holder.itemView.context)
            .load(item.picUrl)
            .into(holder.binding.pic)

        holder.binding.root.setOnClickListener{
            lastPositionSelected = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastPositionSelected)
            notifyItemChanged(selectedPosition)
        }
        holder.binding.title.setTextColor(context.getColor(R.color.white))
        if (selectedPosition == position){
            holder.binding.pic.setBackgroundResource(0)
            holder.binding.mainLayout.setBackgroundResource(R.drawable.purple_bg)
            ImageViewCompat.setImageTintList(holder.binding.pic , ColorStateList.valueOf(context.getColor(R.color.white)))
            holder.binding.title.visibility = View.VISIBLE
        }else{
            holder.binding.pic.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.mainLayout.setBackgroundResource(0)
            ImageViewCompat.setImageTintList(holder.binding.pic , ColorStateList.valueOf(context.getColor(R.color.black)))
            holder.binding.title.visibility = View.GONE
        }
    }
    override fun getItemCount(): Int  = items.size
    class Viewholder(val binding:ViewholderBrandBinding): RecyclerView.ViewHolder(binding.root) {
    }
}