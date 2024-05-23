package com.example.truste_shoes.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.truste_shoes.Model.BrandModel
import com.example.truste_shoes.Model.ItemModel
import com.example.truste_shoes.Model.SliderModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel: ViewModel() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()
    private val _banner = MutableLiveData<List<SliderModel>>()
     val banner : LiveData<List<SliderModel>>
        get() = _banner
    private val _brand = MutableLiveData<MutableList<BrandModel>>()
     val brand : LiveData<MutableList<BrandModel>>
         get() = _brand

    private val _popular = MutableLiveData<MutableList<ItemModel>>()
    val popular : LiveData<MutableList<ItemModel>>
        get() = _popular

    fun loadBanners() {
        val ref = firebaseDatabase.getReference("Banner")
        ref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<SliderModel>()
                for (childSnapShot in snapshot.children){
                    val imgBanner = childSnapShot.getValue(SliderModel::class.java)
                    if (imgBanner != null){
                        lists.add(imgBanner)
                    }
                }
                _banner.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
    fun loadBrand(){
        val ref = firebaseDatabase.getReference("Category")
        ref.addValueEventListener( object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<BrandModel>()
                for (childSnapShot in snapshot.children){
                    val brand_list = childSnapShot.getValue(BrandModel::class.java)
                    if (brand_list != null){
                        lists.add(brand_list)
                    }
                }
                _brand.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
    fun loadPopular(){
        val ref = firebaseDatabase.getReference("Items")
        ref.addValueEventListener( object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists = mutableListOf<ItemModel>()
                for (childSnapShot in snapshot.children){
                    val popular_list = childSnapShot.getValue(ItemModel::class.java)
                    if (popular_list != null){
                        lists.add(popular_list)
                    }
                }
                _popular.value = lists
            }

            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

}