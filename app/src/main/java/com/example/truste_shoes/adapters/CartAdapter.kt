package com.example.truste_shoes.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.project1762.Helper.ChangeNumberItemsListener
import com.example.truste_shoes.Helper.ManagmentCart
import com.example.truste_shoes.Model.ItemModel
import com.example.truste_shoes.databinding.ViewholderCartBinding

class CartAdapter(private val listItemSelected:ArrayList<ItemModel> ,
                  context: Context ,
                  var changeNumberItemsListener: ChangeNumberItemsListener? = null ): RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    class ViewHolder(val binding: ViewholderCartBinding): RecyclerView.ViewHolder(binding.root) {

    }
    private val managementCart = ManagmentCart(context)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        val view  = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context) , parent , false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        val item = listItemSelected[position]
        holder.binding.apply {

            titleTxt.text = item.title
            feeEachItem.text = "$${item.price}"
            totalEachItem.text = "$${Math.round(item.numberInCart * item.price)}"
            numberItem.text = item.numberInCart.toString()

            Glide.with(holder.itemView.context)
                .load(item.picUrl[0])
                .apply(RequestOptions().transform(CenterCrop()))
                .into(pic)

            plusCartBtn.setOnClickListener {
                managementCart.plusItem(listItemSelected , position , object : ChangeNumberItemsListener{
                    override fun onChanged() {
                        notifyDataSetChanged()
                        changeNumberItemsListener?.onChanged()
                    }
                })
            }
            minusCartBtn.setOnClickListener {
                managementCart.minusItem(listItemSelected , position , object : ChangeNumberItemsListener{
                    override fun onChanged() {
                        notifyDataSetChanged()
                        changeNumberItemsListener?.onChanged()
                    }
                })
            }
        }
    }

    override fun getItemCount(): Int = listItemSelected.size
}