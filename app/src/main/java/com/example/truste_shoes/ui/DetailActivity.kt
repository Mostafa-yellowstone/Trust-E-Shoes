package com.example.truste_shoes.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.truste_shoes.Helper.ManagmentCart
import com.example.truste_shoes.Model.ItemModel
import com.example.truste_shoes.Model.SliderModel
import com.example.truste_shoes.R
import com.example.truste_shoes.adapters.ColorAdapter
import com.example.truste_shoes.adapters.SizeAdapter
import com.example.truste_shoes.adapters.SlideAdapter
import com.example.truste_shoes.databinding.ActivityDetailBinding

class DetailActivity : BaseActivity() {
    private lateinit var binding:ActivityDetailBinding
    private lateinit var item: ItemModel
    private var numberOrder = 1
    lateinit var managmentCart: ManagmentCart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        managmentCart = ManagmentCart(this)
        getBundle()
        banners()
        initList()
    }

    private fun initList() {
        val sizeList = ArrayList<String>()
        for (size in item.size){
            sizeList.add(size.toString())
        }
        binding.sizeList.adapter = SizeAdapter(sizeList)
        binding.sizeList.layoutManager = LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false)

        val colorList = ArrayList<String>()
        for (imgUrl in item.picUrl){
            colorList.add(imgUrl)
        }
        binding.colorList.adapter = ColorAdapter(colorList)
        binding.colorList.layoutManager = LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL , false)

    }

    private fun banners(){
        val slideItems = ArrayList<SliderModel>()
        for (imgUrl in item.picUrl){
            slideItems.add(SliderModel(imgUrl))
        }
        binding.slider.adapter = SlideAdapter(slideItems , binding.slider)
        binding.slider.clipToPadding = true
        binding.slider.clipChildren = true
        binding.slider.offscreenPageLimit = 1

        if (slideItems.size > 1){
            binding.apply {
                dotIndicator.visibility = View.VISIBLE
                dotIndicator.attachTo(binding.slider)
            }
        }
    }

    private fun getBundle() {
        item = intent.getParcelableExtra("object")!!

        binding.apply {
            titleTxt.text = item.title
            descriptionTxt.text = item.description
            priceTxt.text = "$" + item.price
            ratingTxt.text = "${item.rating} Rating"
        }
            binding.addToCartBtn.setOnClickListener {
                item.numberInCart = numberOrder
                managmentCart.insertFood(item)
            }
            binding.backBtn.setOnClickListener { finish() }
            binding.cartBtn.setOnClickListener {
            startActivity(Intent(this@DetailActivity , CartActivity::class.java))
            }

        }

}