package com.kodingan.endemic.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kodingan.endemic.core.R
import com.kodingan.endemic.core.databinding.ItemListSpeciesBinding

import com.kodingan.endemic.core.domain.model.Species
import java.util.ArrayList

class SpeciesAdapter : RecyclerView.Adapter<SpeciesAdapter.ListViewHolder>() {

    var onItemClick: ((Species) -> Unit)? = null
    private var listData = ArrayList<Species>()

    fun setData(newListData: List<Species>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ListViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list_species, parent, false))

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val data = listData[position]
        holder.bind(data)
    }
    override fun getItemCount() = listData.size



    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListSpeciesBinding.bind(itemView)
        fun bind(data: Species) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(data.image)
                    .into(ivItemImage)
                tvItemTitle.text = data.name
                tvItemSubtitle.text = data.address

            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}