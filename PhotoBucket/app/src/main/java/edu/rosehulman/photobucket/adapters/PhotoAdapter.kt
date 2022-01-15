package edu.rosehulman.photobucket.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import edu.rosehulman.photobucket.R
import edu.rosehulman.photobucket.model.Photo
import edu.rosehulman.photobucket.model.PhotosViewModel
import edu.rosehulman.photobucket.ui.PhotosListFragment

class PhotoAdapter (val fragment: PhotosListFragment) : RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    val model = ViewModelProvider(fragment.requireActivity()).get(PhotosViewModel::class.java)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(model.getPhotoAt(position))
    }

    override fun getItemCount() = model.size()

    fun addPhoto() {
        model.addPhoto()
        notifyDataSetChanged()
    }

    inner class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoView: ImageView = itemView.findViewById(R.id.photo)
        val captionView: TextView = itemView.findViewById(R.id.caption)
        val iconView: ImageView = itemView.findViewById(R.id.photo_icon)

        init {
            itemView.setOnClickListener {
                model.updatePos(adapterPosition)
                fragment.findNavController().navigate(R.id.nav_edit)
            }

            itemView.setOnLongClickListener{
                model.updatePos(adapterPosition)
                model.toggleFavourite()
                notifyItemChanged(adapterPosition)
                true
            }
        }


        fun bind(photo: Photo) {
            photoView.load(photo.URL){
                crossfade(true)
                transformations(
                    RoundedCornersTransformation(8F)
                )
            }
            photoView.apply {
                scaleType = ImageView.ScaleType.CENTER_CROP
            }
            var cap = photo.caption
            if(cap.length > 5) {
                cap = cap.substring(0,5)
            }
            captionView.text = cap
            iconView.setImageResource(
                if(photo.isFavourited) {
                    R.drawable.ic_baseline_favorite_24
                } else {
                    R.drawable.ic_baseline_photo_24
                }
            )
        }
    }

}