package com.strikalov.kotlin2app.ui.main

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.strikalov.kotlin2app.R
import com.strikalov.kotlin2app.data.entity.Note
import kotlinx.android.synthetic.main.item_note.view.*


class NotesRVAdapter : RecyclerView.Adapter<NotesRVAdapter.ViewHolder>(){


    var notes: List<Note> = listOf()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        private val titleTV = itemView.findViewById<TextView>(R.id.tv_title)
        private val textTV = itemView.findViewById<TextView>(R.id.tv_text)

        fun bind(note: Note) = with(note) {
            titleTV.text = title
            textTV.text = text
            itemView.setBackgroundColor(color)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_note,
                parent,
                false
            )
        )

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(vh: ViewHolder, pos: Int) = vh.bind(notes[pos])

}