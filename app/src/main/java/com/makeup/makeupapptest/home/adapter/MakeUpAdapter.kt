package com.makeup.makeupapptest.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.makeup.makeupapptest.R
import com.makeup.makeupapptest.databinding.ItemsProductBinding
import com.makeup.makeupapptest.home.models.ProductListItem
import com.makeup.makeupapptest.util.loadImageWithGlide

class MakeUpAdapter(
    private var items: ArrayList<ProductListItem>,
    private val clickListenerProduct: ClickListenerProduct
) : RecyclerView.Adapter<MakeUpAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemsProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ProductListItem, clickListenerProduct: ClickListenerProduct) {
            binding.productNameTextView.text = "${item.name?.trim()}"
            if (item.priceSign == null) {
                binding.priceTextView.text = "$ ${item.price}"
            } else {
                binding.priceTextView.text = "${item.priceSign} ${item.price}"
            }
            binding.productTypeTextView.text = item.productType
            binding.iconImageView.loadImageWithGlide(item.imageLink, R.drawable.no_image)

            binding.cardView.setOnClickListener {
                clickListenerProduct.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemsProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], clickListenerProduct)
    }

    override fun getItemCount() = items.size

    fun addData(list: List<ProductListItem>) {
        items.clear()
        items.addAll(list)
    }

    class ClickListenerProduct(val clickListenerProduct: (product: ProductListItem) -> Unit) {
        fun onClick(product: ProductListItem) = clickListenerProduct(product)
    }
}
