package com.eliel.songs

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eliel.songs.models.Song
import com.eliel.songs.services.SongServiceImpl
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SongActivityList : AppCompatActivity()  {
    private lateinit var songs: ArrayList<Song>
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: SongAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_list)

        songs = ArrayList<Song>()

        viewManager = LinearLayoutManager(this)

        viewAdapter = SongAdapter(songs, this)


        recyclerView = findViewById<RecyclerView>(R.id.recyclerViewSongs)

        recyclerView.layoutManager = viewManager

        recyclerView.adapter = viewAdapter

        getAllSongs()

        val fab: FloatingActionButton = findViewById(R.id.floatingActionButton)
        fab.setOnClickListener{
            val intent = Intent(this, SongDetailActivity::class.java)
            intent.putExtra("state", "Adding")
            startActivity(intent)
        }
    }

    private fun getAllSongs() {
        val songServiceImpl = SongServiceImpl()
        songServiceImpl.getAll(this) { response ->
            run {
                if (response != null) {
                    viewAdapter.songList = response
                }
                viewAdapter.notifyDataSetChanged()
            }
        }
    }
}