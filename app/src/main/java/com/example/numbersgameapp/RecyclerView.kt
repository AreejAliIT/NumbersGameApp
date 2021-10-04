package com.example.numbersgameapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class GuessRV(private val context: Context, private val guessArray: ArrayList<String>) :
    RecyclerView.Adapter<GuessRV.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    private lateinit var guessText :TextView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val msg = guessArray[position]
        holder.itemView.apply {
            guessText = findViewById(R.id.guessText)
            guessText.text = msg
        }
    }

    override fun getItemCount() = guessArray.size

}
