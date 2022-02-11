package edu.rosehulman.photobucket.adapters

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.RoundedCornersTransformation
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import edu.rosehulman.photobucket.Constants
import edu.rosehulman.photobucket.R
import edu.rosehulman.photobucket.model.Photo
import edu.rosehulman.photobucket.model.PhotosViewModel
import edu.rosehulman.photobucket.ui.PhotosListFragment

class PhotoAdapter (val fragment: PhotosListFragment) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {
    val model = ViewModelProvider(fragment.requireActivity()).get(PhotosViewModel::class.java)

    fun addAllListener(fragmentName: String) {
        model.addAllListener(fragmentName) {
            Log.d(Constants.TAG, "dawa")
            notifyDataSetChanged()
        }
    }

    fun addOnlyListener(fragmentName: String) {
        model.addOnlyListener(fragmentName) {
            Log.d(Constants.TAG, "dawa")
            notifyDataSetChanged()
        }
    }

    fun removeListener(fragmentName: String){
        model.removeListener(fragmentName)
    }


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
        val uidView: TextView = itemView.findViewById(R.id.uid)
        val iconView: ImageView = itemView.findViewById(R.id.photo_icon)

        init {
            itemView.setOnClickListener {
                model.updatePos(adapterPosition)
                if(model.getCurrentPhoto().uid == Firebase.auth.currentUser!!.uid) {
                    fragment.findNavController().navigate(R.id.nav_edit)
                }
                else{
                    fragment.findNavController().navigate(R.id.nav_detail)

                }
            }

            itemView.setOnLongClickListener{
                model.updatePos(adapterPosition)
                model.toggleFavourite()
                notifyItemChanged(adapterPosition)
                true
            }
        }


        fun bind(photo: Photo) {
            photoView.load(photo.url){
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

            var uide = photo.uid
            if(uide.length > 5) {
                uide = uide.substring(0,5)
            }
            uidView.text = uide
        }
    }

}