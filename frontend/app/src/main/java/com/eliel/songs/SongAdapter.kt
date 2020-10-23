package com.eliel.songs

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eliel.songs.models.Song

class SongAdapter(var songList: ArrayList<Song>, val context: Context) : RecyclerView.Adapter<SongAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.song_list_row, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(songList[position], context)
    }

    override fun getItemCount(): Int {
        return songList.size;
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(b: Song, context: Context){
            val txt_name: TextView = itemView.findViewById(R.id.textViewName)
            val txt_artist: TextView = itemView.findViewById(R.id.textViewArtist)
            val txt_lenght: TextView = itemView.findViewById(R.id.textViewLenght)
            val txt_year: TextView = itemView.findViewById(R.id.textViewYear)

            txt_name.text = b.name
            txt_artist.text = b.artist
            txt_lenght.text = b.lenght
            txt_year.text = b.year

            itemView.setOnClickListener {
                val intent = Intent(context, SongDetailActivity::class.java)
                intent.putExtra("songId", b.id)
                intent.putExtra("state", "Showing")
                context.startActivity(intent)
            }
        }
    }
}