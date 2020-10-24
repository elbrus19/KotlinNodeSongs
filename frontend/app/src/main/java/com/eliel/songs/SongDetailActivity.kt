package com.eliel.songs

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.eliel.songs.models.Song
import com.eliel.songs.services.SongServiceImpl
import com.eliel.songs.services.SongSingleton
import com.google.android.material.textfield.TextInputEditText
import com.squareup.picasso.Picasso

class SongDetailActivity: AppCompatActivity() {
    private lateinit var state: String
    private lateinit var textInputEditTextName: EditText
    private lateinit var textInputEditTextArtist: EditText
    private lateinit var textInputEditTextLenght: EditText
    private lateinit var textInputEditTextYear: EditText
    private lateinit var buttonEdit: Button
    private lateinit var buttonDelete: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song_detail)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)



        state = this.intent.getStringExtra("state").toString()

        val songId = this.intent.getIntExtra("songId", 1)

        textInputEditTextName = findViewById(R.id.TextInputEditTextName)
        textInputEditTextArtist = findViewById(R.id.TextInputEditTextArtist)
        textInputEditTextLenght = findViewById(R.id.TextInputEditTextLenght)
        textInputEditTextYear = findViewById(R.id.TextInputEditTextYear)

        buttonDelete = findViewById(R.id.buttonDelete)
        buttonDelete.setOnClickListener {
            deleteSong(songId)
        }

        if(state == "Showing") getSong(songId)

        buttonEdit = findViewById(R.id.buttonEdit)
        buttonEdit.setOnClickListener {
            when(state){
                "Showing" -> {
                    changeButtonsToEditing()
                }
                "Editing" -> {
                    val song = Song(songId, textInputEditTextName.text.toString(), textInputEditTextArtist.text.toString(), textInputEditTextLenght.text.toString(), textInputEditTextYear.text.toString())
                    updateSong(song)
                }
                "Adding" -> {
                    val song = Song(songId, textInputEditTextName.text.toString(), textInputEditTextArtist.text.toString(), textInputEditTextLenght.text.toString(), textInputEditTextYear.text.toString())
                    createSong(song)
                }
            }
        }

        if(state == "Adding") changeButtonsToAdding()
    }

    private fun updateSong(song: Song) {
        val songServiceImpl = SongServiceImpl()
        songServiceImpl.updateSong(this, song) { ->
            run {
                changeButtonsToShowing(song.id)
                val intent = Intent(this, SongActivityList::class.java)
                startActivity(intent)
            }
        }
    }


    private fun createSong(song: Song) {
        val songServiceImpl = SongServiceImpl()
        songServiceImpl.createSong(this, song) { ->
            run {
                changeButtonsToShowing(song.id)
                val intent = Intent(this, SongActivityList::class.java)
                startActivity(intent)
            }
        }
    }

    private fun changeButtonsToAdding() {
        buttonDelete.visibility = View.GONE
        buttonDelete.isEnabled = false
        buttonEdit.setText("Add Song")
        textInputEditTextName.isEnabled = true
        textInputEditTextArtist.isEnabled = true
        textInputEditTextLenght.isEnabled = true
        textInputEditTextYear.isEnabled = true
        state = "Adding"
    }

    private fun changeButtonsToShowing(songId: Int){
        buttonDelete.visibility = View.VISIBLE
        buttonDelete.isEnabled = true
        buttonEdit.setText("Edit Song")
        textInputEditTextName.isEnabled = false
        textInputEditTextArtist.isEnabled = false
        textInputEditTextLenght.isEnabled = false
        textInputEditTextYear.isEnabled = false
        state = "Showing"
    }

    private fun changeButtonsToEditing() {
        buttonDelete.visibility = View.GONE
        buttonDelete.isEnabled = false
        buttonEdit.setText("Apply changes")
        textInputEditTextName.isEnabled = true
        textInputEditTextArtist.isEnabled = true
        textInputEditTextLenght.isEnabled = true
        textInputEditTextYear.isEnabled = true
        state = "Editing"
    }


    private fun getSong(songId: Int) {
        val songServiceImpl = SongServiceImpl()
        songServiceImpl.getById(this, songId) { response ->
            run {

                val txt_name: TextInputEditText = findViewById(R.id.TextInputEditTextName)
                val txt_artist: TextInputEditText = findViewById(R.id.TextInputEditTextArtist)
                val txt_lenght: TextInputEditText = findViewById(R.id.TextInputEditTextLenght)
                val txt_year: TextInputEditText = findViewById(R.id.TextInputEditTextYear)
                val img: ImageView = findViewById(R.id.imageViewSongDetail)

                txt_name.setText(response?.name ?: "")
                txt_artist.setText(response?.artist ?: "")
                txt_lenght.setText(response?.lenght ?: "")
                txt_year.setText(response?.year ?: "")

                val url = SongSingleton.getInstance(this).baseUrl + "/img/song_"
                val imageUrl = url + (response?.id.toString() ?: "0" ) + ".jpg"
                Picasso.with(this).load(imageUrl).into(img);
            }
        }
    }

    private fun deleteSong(bicycleId: Int) {
        val bicycleServiceImpl = SongServiceImpl()
        bicycleServiceImpl.deleteById(this, bicycleId) { ->
            run {
                val intent = Intent(this, SongActivityList::class.java)
                startActivity(intent)
            }
        }
    }
}