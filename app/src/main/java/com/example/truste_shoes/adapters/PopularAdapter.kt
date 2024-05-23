package com.example.truste_shoes.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.truste_shoes.Model.ItemModel
import com.example.truste_shoes.databinding.ViewholderRecommendedBinding
import com.example.truste_shoes.ui.DetailActivity

class PopularAdapter(val items: MutableList<ItemModel>): RecyclerView.Adapter<PopularAdapter.Viewholder>() {
    lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.Viewholder {
        context = parent.context
        val view = ViewholderRecommendedBinding.inflate(LayoutInflater.from(context) , parent , false)
        return Viewholder(view )
    }



    override fun onBindViewHolder(holder: PopularAdapter.Viewholder, position: Int) {
        holder.binding.apply {
            titleTxt.text = items[position].title
            priceTxt.text = "$"  + items[position].price.toString()
            ratingTxt.text = items[position].rating.toString()
            val requestOptions = RequestOptions().transform(CenterCrop())
            Glide.with(holder.itemView.context)
                    .load(items[position].picUrl[0])
                    .apply (requestOptions)
                    .into(holder.binding.pic)

            holder.itemView.setOnClickListener {
                val intent = Intent(holder.itemView.context , DetailActivity::class.java)
                intent.putExtra("object" , items[position])
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int = items.size

    class Viewholder(val binding: ViewholderRecommendedBinding): RecyclerView.ViewHolder(binding.root) {

    }
}

