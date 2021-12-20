package com.ikgptu.stook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerAdapter(
        private var notiList : ArrayList<NotificationClass>) : RecyclerView.Adapter<MyRecyclerAdapter.RecyclerViewHolder>(){
    // Define below ViewHolder
    inner class RecyclerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val titleView : TextView = itemView.findViewById(R.id.titleView)
        val descView : TextView = itemView.findViewById(R.id.descView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout,parent,false)
        return RecyclerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        if(notiList[position].title != null){
        holder.titleView.text = notiList[position].title
        holder.descView.text = notiList[position].notification
        }else{
            holder.titleView.isVisible = false
            holder.descView.isVisible = false
        }
    }

    override fun getItemCount(): Int = notiList.size
}