package com.dokari4.umkmkkn2.ui

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dokari4.umkmkkn2.R
import com.dokari4.umkmkkn2.data.local.UmkmEntity
import com.dokari4.umkmkkn2.databinding.HorizontalItemRowBinding
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

    inner class MyHolder(private val binding: HorizontalItemRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(post: UmkmEntity?) {

            binding.imgPhoto.setImageResource(R.drawable.screenshot)
            binding.tvNameOwner.text = post?.umkmNamaPengusaha
            binding.tvVariant.text = post?.umkmJenisUsaha

//            binding.btnMaps.setOnClickListener {
//                val url = Uri.parse(post?.umkmAlamatUsaha)
//                val intent = Intent(
//                    Intent.ACTION_VIEW,
//                    url
//                )
//                context.startActivity(intent)
//            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(HorizontalItemRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
        holder.itemView.setOnClickListener {
            listener(post)
        }
    }
}