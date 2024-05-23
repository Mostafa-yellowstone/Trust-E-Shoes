package com.example.truste_shoes.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.truste_shoes.Model.SliderModel
import com.example.truste_shoes.R
import com.example.truste_shoes.adapters.BrandAdapter
import com.example.truste_shoes.adapters.PopularAdapter
import com.example.truste_shoes.adapters.SlideAdapter
import com.example.truste_shoes.databinding.ActivityIntroBinding
import com.example.truste_shoes.databinding.ActivityMainBinding
import com.example.truste_shoes.viewModel.MainViewModel

class MainActivity : BaseActivity() {
    private val viewModel = MainViewModel()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBanner()
        initBrand()
        initPopular()
        initBottomMenu()
    }

    private fun initBottomMenu() {
        binding.cartBtn.setOnClickListener { startActivity(Intent(this@MainActivity , CartActivity::class.java)) }
    }

    private fun initBrand() {
        binding.progressBarBrand.visibility = View.VISIBLE
        viewModel.brand.observe(this , {
            binding.viewBrand.layoutManager = LinearLayoutManager(this@MainActivity , LinearLayoutManager.HORIZONTAL , false)
            binding.viewBrand.adapter = BrandAdapter(it)
            binding.progressBarBrand.visibility = View.GONE
        })
        viewModel.loadBrand()
    }
    private fun initPopular() {
        binding.progressBarPopluar.visibility = View.VISIBLE
        viewModel.popular.observe(this , {
            binding.viewPopluar.layoutManager = GridLayoutManager(this@MainActivity , 2)
            binding.viewPopluar.adapter = PopularAdapter(it)
            binding.progressBarPopluar.visibility = View.GONE
        })
        viewModel.loadPopular()
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.banner.observe(this , {
            items -> banners(items)
            binding.progressBarBanner.visibility = View.GONE
        })
        viewModel.loadBanners()
    }
    private fun banners(images: List<SliderModel>){
        binding.viewPagerSlider.adapter = SlideAdapter(images , binding.viewPagerSlider)
        binding.viewPagerSlider.clipToPadding = false
        binding.viewPagerSlider.clipChildren = false
        binding.viewPagerSlider.offscreenPageLimit = 3
        binding.viewPagerSlider.getChildAt(0).overScrollMode =RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer().apply {
        addTransformer(MarginPageTransformer(40))
        }
        binding.viewPagerSlider.setPageTransformer(compositePageTransformer)
        if (images.size > 1 ){
            binding.dotIndicator.visibility = View.VISIBLE
            binding.dotIndicator.attachTo(binding.viewPagerSlider)
        }

    }



}