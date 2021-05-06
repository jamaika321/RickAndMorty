package com.example.rickandmorty.adapter

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.R
import com.example.rickandmorty.activity.HeroesCardActivity
import com.example.rickandmorty.pojo.Heroes
import com.example.rickandmorty.pojo.HeroesResponse
import com.squareup.picasso.Picasso

class MyAdapter(context: Context):  ListAdapter<Heroes, MyAdapter.ViewHolder>(WordsComparator()){

    val contextR: Context = context

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imageView = itemView.findViewById<ImageView?>(R.id.image)
        val txt_name = itemView.findViewById<TextView?>(R.id.txt_name)

        fun bind(heroesList: Heroes, context: Context){
            heroesList?.let {
                txt_name.text = it.name
                Picasso.get().load(it.image?.toUri()).into(imageView)
            }
            itemView?.setOnClickListener{
                val intent = Intent(context, HeroesCardActivity::class.java).apply {
                    putExtra("heroes", heroesList)
                }
                context.startActivity(intent)
            }
        }
        companion object {
            fun create(parent: ViewGroup): ViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_layout, parent, false)
                return ViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current, contextR)

    }


    class WordsComparator : DiffUtil.ItemCallback<Heroes>() {
        override fun areItemsTheSame(oldItem: Heroes, newItem: Heroes): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Heroes, newItem: Heroes): Boolean {
            return oldItem.name == newItem.name
        }
    }
}