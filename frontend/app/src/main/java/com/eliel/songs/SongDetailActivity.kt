package com.eliel.songs

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.eliel.songs.models.Song

class SongDetailActivity: AppCompatActivity()  {
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

}