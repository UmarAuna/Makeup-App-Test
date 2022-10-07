package com.makeup.makeupapptest.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.makeup.makeupapptest.databinding.ItemsSortBinding
import com.makeup.makeupapptest.home.models.SortData

class SortAdapter(
    private var items: List<SortData> = emptyList(),
    private val clickListenerSort: ClickListenerSort
) : RecyclerView.Adapter<SortAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemsSortBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SortData, clickListenerSort: ClickListenerSort) {
            binding.itemTextView.text = item.productType

            binding.itemTextView.setOnClickListener {
                clickListenerSort.onClick(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemsSortBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position], clickListenerSort)
    }

    override fun getItemCount() = items.size

    fun updateList(items: List<SortData>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ClickListenerSort(val clickListenerSort: (sort: SortData) -> Unit) {
        fun onClick(sort: SortData) = clickListenerSort(sort)
    }
}