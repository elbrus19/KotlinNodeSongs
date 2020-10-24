package com.eliel.songs.services

import android.content.Context
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.eliel.songs.models.Song
import org.json.JSONObject

class SongServiceImpl: ISongService {
    override fun getAll(context: Context, completionHandler: (response: ArrayList<Song>) -> Unit) {
        val path = SongSingleton.getInstance(context).baseUrl + "/api/songs"
        val arrayRequest = JsonArrayRequest(
            Request.Method.GET, path, null,
            { response ->
                var songs: ArrayList<Song> = ArrayList()
                for (i in 0 until response.length()) {
                    val song = response.getJSONObject(i)
                    val id = song.getInt("id")
                    val name = song.getString("name")
                    val artist = song.getString("artist")
                    val lenght = song.getString("lenght")
                    val year = song.getString("year")
                    songs.add(Song(id, name, artist, lenght, year))
                }
                completionHandler(songs)
            },
            { error ->
                completionHandler(ArrayList<Song>())
            })
        SongSingleton.getInstance(context).addToRequestQueue(arrayRequest)
    }

    override fun getById(context: Context, songId: Int, completionHandler: (response: Song?) -> Unit) {
        val path = SongSingleton.getInstance(context).baseUrl + "/api/songs/" + songId
        val objectRequest = JsonObjectRequest(Request.Method.GET, path, null,
            { response ->
                if(response == null) completionHandler(null)

                val id = response.getInt("id")
                val name = response.getString("name")
                val artist = response.getString("artist")
                val lenght = response.getString("lenght")
                val year = response.getString("year")

                val song = Song(id,name, artist, lenght, year)
                completionHandler(song)
            },
            { error ->
                completionHandler(null)
            })
        SongSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun deleteById(context: Context, songId: Int, completionHandler: () -> Unit) {
        val path = SongSingleton.getInstance(context).baseUrl + "/api/songs/" + songId
        val objectRequest = JsonObjectRequest(Request.Method.DELETE, path, null,
            { response ->
                completionHandler()
            },
            { error ->
                completionHandler()
            })
        SongSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun updateSong(context: Context, song: Song, completionHandler: () -> Unit) {
        val path = SongSingleton.getInstance(context).baseUrl + "/api/songs/" + song.id
        val songJson: JSONObject = JSONObject()
        songJson.put("id", song.id.toString())
        songJson.put("name", song.name)
        songJson.put("artist", song.artist)
        songJson.put("lenght", song.lenght)
        songJson.put("year", song.year)

        val objectRequest = JsonObjectRequest(Request.Method.PUT, path, songJson,
            { response ->
                completionHandler()
            },
            { error ->
                completionHandler()
            })
        SongSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }

    override fun createSong(context: Context, song: Song, completionHandler: () -> Unit) {
        val path = SongSingleton.getInstance(context).baseUrl + "/api/songs"
        val songJson: JSONObject = JSONObject()
        songJson.put("name", song.name)
        songJson.put("artist", song.artist)
        songJson.put("lenght", song.lenght)
        songJson.put("year", song.year)

        val objectRequest = JsonObjectRequest(Request.Method.POST, path, songJson,
            { response -> completionHandler() },
            { error -> completionHandler() })
        SongSingleton.getInstance(context).addToRequestQueue(objectRequest)
    }
}