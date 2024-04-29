package com.manjeet.imageloadingassignment.adpter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.manjeet.imageloadingassignment.databinding.ItemLoadImageBinding
import com.manjeet.imageloadingassignment.extension.ImageLoader.bindImage
import com.manjeet.imageloadingassignment.model.MediaCoverageResponseItem


class ImageLoadAdapter(private val context: Context, private val mediaCoverageListModel: ArrayList<MediaCoverageResponseItem>
) : RecyclerView.Adapter<ImageLoadAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: ItemLoadImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(employeeModel: MediaCoverageResponseItem) {
//            val imageLoader = ImageLoader(binding.ivImageItem)
            val imageUrl = employeeModel.thumbnail?.domain+"/"+employeeModel.thumbnail?.basePath+"/0/"+employeeModel.thumbnail?.key
//            Log.d("ImageLoadAdapter", "bind: ${imageUrl}")
//            imageLoader.execute(imageUrl)
            Log.d("dnaskjdnaskdnaskj", "bind: ${imageUrl}")
            binding.ivImageItem.bindImage(imageUrl)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = ItemLoadImageBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mediaCoverageListModel.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mediaCoverageListModel[position])
    }
}