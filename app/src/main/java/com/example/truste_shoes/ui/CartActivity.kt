package com.example.truste_shoes.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.project1762.Helper.ChangeNumberItemsListener
import com.example.truste_shoes.Helper.ManagmentCart
import com.example.truste_shoes.R
import com.example.truste_shoes.adapters.CartAdapter
import com.example.truste_shoes.databinding.ActivityCartBinding

class CartActivity : BaseActivity() {
    private lateinit var binding: ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax: Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managmentCart = ManagmentCart(this)
        setVariables()
        initCartList()
        calculateCart()
    }

    private fun initCartList() {
        binding.viewCart.layoutManager = LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false)
        binding.viewCart.adapter = CartAdapter(managmentCart.getListCart() , this , object:ChangeNumberItemsListener{
            override fun onChanged() {

            }
        } )
        with(binding){
            empltyTxt.visibility = if (managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollView2.visibility = if (managmentCart.getListCart().isEmpty())View.GONE else View.VISIBLE
        }
    }

    private fun calculateCart(){
        val percentTax = 0.02
        val delivery = 10.0
        tax = Math.round((managmentCart.getTotalFee()*percentTax)*100)/ 100.0
        val total = Math.round((managmentCart.getTotalFee() + tax + delivery )* 100) / 100.0
        val itemTotal = Math.round(managmentCart.getTotalFee() * 100)/100.0

        with(binding){
            totalFeeTxt.text = "$$itemTotal"
            taxTxt.text = "$$tax"
            deliveryTxt.text = "$$delivery"
            totalTxt.text = "$$total"
        }
    }
    private fun setVariables() {
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}