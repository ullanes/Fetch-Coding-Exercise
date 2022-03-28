package com.example.fetchcodeex

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class RecyclerAdapter: RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    private var size = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.ViewHolder {

        val v = LayoutInflater.from(parent.context).inflate(R.layout.card_view,parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RecyclerAdapter.ViewHolder, position: Int) {
        val activity: MainActivity? = holder.itemView.context as MainActivity
        val list = activity?.getData()

        holder.listId.text = list?.get(position)?.listId.toString()
        holder.name.text = list?.get(position)?.name.toString()
        holder.id.text = list?.get(position)?.id.toString()
    }

    override fun getItemCount(): Int {return size }
    fun setSize(size: Int){

        this.size =size
    }


    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var listId : TextView
        var name : TextView
        var id : TextView

        init{
           listId = itemView.findViewById(R.id.tvListIdDisplay)
           name = itemView.findViewById(R.id.tvNameDisplay)
           id = itemView.findViewById(R.id.tvIdDisplay)

        }
    }
}