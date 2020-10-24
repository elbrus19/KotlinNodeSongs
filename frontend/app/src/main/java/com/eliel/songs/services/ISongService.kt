package com.eliel.songs.services

import android.content.Context
import com.eliel.songs.models.Song

interface ISongService {
    fun getAll(context: Context, completionHandler: (response: ArrayList<Song>) -> Unit)

    fun getById(context: Context, songId: Int, completionHandler: (response: Song?) -> Unit)

    fun deleteById(context: Context, songId: Int, completionHandler: () -> Unit)

    fun updateSong(context: Context, song: Song, completionHandler: () -> Unit)

    fun createSong(context: Context, song: Song, completionHandler: () -> Unit)
}