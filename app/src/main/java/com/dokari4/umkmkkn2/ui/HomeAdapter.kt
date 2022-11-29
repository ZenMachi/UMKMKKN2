package com.dokari4.umkmkkn2.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dokari4.umkmkkn2.data.local.UmkmEntity
import com.dokari4.umkmkkn2.databinding.ItemRowBinding

class HomeAdapter(private  val context: Context, private val data: MutableList<UmkmEntity> = mutableListOf(), private val listener: (UmkmEntity) -> Unit): ListAdapter<UmkmEntity, HomeAdapter.MyHolder>(
    DiffutilCallBack
) {
    object DiffutilCallBack: DiffUtil.ItemCallback<UmkmEntity>(){
        override fun areItemsTheSame(oldItem: UmkmEntity, newItem: UmkmEntity): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UmkmEntity, newItem: UmkmEntity): Boolean {
            return oldItem.id == newItem.id
        }

    }

//    fun setData(data: MutableList<UmkmEntity>) {
//        this.data.clear()
//        this.data.addAll(data)
//        notifyDataSetChanged()
//    }

    inner class MyHolder(private val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: UmkmEntity?) {
            binding.tvName.text = post?.umkmName
            binding.btnMaps.setOnClickListener {
                val url = Uri.parse(post?.umkmPhoneNumber)
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    url
                )
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(ItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
        holder.itemView.setOnClickListener {
            listener(post)
        }
    }
}