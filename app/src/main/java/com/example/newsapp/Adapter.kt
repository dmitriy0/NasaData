package com.example.newsapp

import android.app.Activity
import android.graphics.Canvas
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.squareup.picasso.Picasso


class Adapter(private val recyclerView: RecyclerView,private val activity: Activity, private val data: ArrayList<NasaData>) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewTitle: TextView = itemView.findViewById(R.id.title)
        val textViewAuthor: TextView = itemView.findViewById(R.id.author)
        //val textViewDate: TextView = itemView.findViewById(R.id.date)
        val textViewDescription: TextView = itemView.findViewById(R.id.description)
        val image: ImageView = itemView.findViewById(R.id.imageView)
        val layout: MaterialCardView = itemView.findViewById(R.id.layout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val element = data[position]
        holder.textViewTitle.text = element.title
        holder.textViewAuthor.text = element.author
        Picasso.get().load(element.imageUrl).into(holder.image)
        holder.layout.setOnClickListener(View.OnClickListener {
            if (holder.textViewDescription.text.isEmpty()){
                val linearLayoutManager: LinearLayoutManager  = recyclerView.layoutManager as LinearLayoutManager
                linearLayoutManager.scrollToPositionWithOffset(position, 0);
                holder.textViewDescription.updateLayoutParams{height = WRAP_CONTENT}
                holder.textViewDescription.text = element.description
            } else {
                holder.textViewDescription.text = ""
                holder.textViewDescription.updateLayoutParams{height = 0}
            }

        })
    }
    override fun getItemCount() = data.size

}