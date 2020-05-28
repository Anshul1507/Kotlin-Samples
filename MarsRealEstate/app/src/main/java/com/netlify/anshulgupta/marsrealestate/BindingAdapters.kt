package com.netlify.anshulgupta.marsrealestate

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.netlify.anshulgupta.marsrealestate.network.MarsProperty
import com.netlify.anshulgupta.marsrealestate.overview.PhotoGridAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView,data:List<MarsProperty>?){
    val adapter = recyclerView.adapter as PhotoGridAdapter
    adapter.submitList(data)
}
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView,imgUrl: String?){
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

