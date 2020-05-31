package com.netlify.anshulgupta.dev_tube.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.netlify.anshulgupta.dev_tube.R
import com.netlify.anshulgupta.dev_tube.databinding.ItemLayoutBinding

class DevTubeAdapter (val callback: VideoClick) : RecyclerView.Adapter<DevTubeViewHolder>() {

    /**
     * The videos that our Adapter will show
     */
    var videos: List<Video> = emptyList()
    set(value) {
        field = value

        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DevTubeViewHolder {
        val dataBinding: ItemLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            DevTubeViewHolder.LAYOUT,
            parent,
            false
        )
        return DevTubeViewHolder(dataBinding)
    }

    override fun getItemCount(): Int = videos.size

    override fun onBindViewHolder(holder: DevTubeViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.video = videos[position]
            it.videoCallback = callback
        }
    }

}

class DevTubeViewHolder(val viewDataBinding: ItemLayoutBinding) :
RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.item_layout
    }
}




/**
 * Click listener for Videos. By giving the block a name it helps a reader understand what it does.
 *
 */
class VideoClick(val block: (Video) -> Unit) {
    /**
     * Called when a video is clicked
     *
     * @param video the video that was clicked
     */
    fun onClick(video: Video) = block(video)
}