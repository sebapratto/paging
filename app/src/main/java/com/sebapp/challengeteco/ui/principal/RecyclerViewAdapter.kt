package com.sebapp.challengeteco.ui.principal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sebapp.challengeteco.R
import com.sebapp.challengeteco.databinding.RecyclerRowBinding
import com.sebapp.challengeteco.domain.model.CharacterData


class RecyclerViewAdapter(
): PagingDataAdapter<CharacterData, RecyclerViewAdapter.MyViewHolder>(
    DiffUtilCallBack()
) {

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.MyViewHolder {
        val inflater = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.recycler_row, parent, false)

        return MyViewHolder(inflater)
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val binding = RecyclerRowBinding.bind(view)
        private var currentItem: CharacterData? = null

        init {
            binding.imageView.setOnClickListener {
                currentItem?.let { item ->
                    onItemClickListener?.let {
                        it(item.name!!)
                    }
                }
            }
        }

        fun bind(data: CharacterData) {

            currentItem = data

            binding.tvName.text = data.name
            binding.tvDesc.text = data.species

            Glide.with(binding.imageView)
                .load(data.image)
                .centerCrop()
                .into(binding.imageView)

        }

    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<CharacterData>() {
        override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.name == newItem.name
                    && oldItem.species == newItem.species
        }

    }

}