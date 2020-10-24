package com.eliel.songs

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.eliel.songs.models.Song
import com.squareup.picasso.Picasso

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
        fun bindView(s: Song, context: Context){
            val url = "http://192.168.1.40:8080/img/song_"
            val txt_name: TextView = itemView.findViewById(R.id.textViewName)
            val txt_artist: TextView = itemView.findViewById(R.id.textViewArtist)
            val txt_lenght: TextView = itemView.findViewById(R.id.textViewLenght)
            val txt_year: TextView = itemView.findViewById(R.id.textViewYear)
            val img: ImageView = itemView.findViewById(R.id.imageViewSong)

            txt_name.text = s.name
            txt_artist.text = s.artist
            txt_lenght.text = s.lenght
            txt_year.text = s.year

            val imageUrl = url + s.id.toString() + ".jpg"
            Picasso.with(context).load(imageUrl).into(img);

            itemView.setOnClickListener {
                val intent = Intent(context, SongDetailActivity::class.java)
                intent.putExtra("songId", s.id)
                intent.putExtra("state", "Showing")
                context.startActivity(intent)
            }
        }
    }
}