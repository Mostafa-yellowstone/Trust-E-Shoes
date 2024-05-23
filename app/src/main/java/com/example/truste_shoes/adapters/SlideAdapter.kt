package com.example.truste_shoes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.truste_shoes.Model.SliderModel
import com.example.truste_shoes.R

class SlideAdapter(private var slideItems: List<SliderModel> ,  private val viewPager2: ViewPager2):
    RecyclerView.Adapter<SlideAdapter.SliderViewHolder>() {
    private lateinit var context: Context

    private val runnable = Runnable {
        slideItems = slideItems
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SlideAdapter.SliderViewHolder {
        context = parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slider_item_container , parent ,false)
        return SliderViewHolder(view)
    }

    override fun getItemCount(): Int = slideItems.size


    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.setImage(slideItems[position] , context)
        if (position == slideItems.lastIndex-1){
            viewPager2.post(runnable)
        }
    }

    class SliderViewHolder(viewItem: View):RecyclerView.ViewHolder(viewItem)  {
     private val imageView: ImageView = itemView.findViewById(R.id.imageSlide)

        fun setImage(sliderItems: SliderModel , context: Context){
            val requestOptions = RequestOptions().transform(CenterInside())
            Glide.with(context)
                .load(sliderItems.url)
                .apply (requestOptions)
                .into(imageView)
        }
    }
}