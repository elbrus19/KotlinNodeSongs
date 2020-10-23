package com.eliel.songs.services

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class SongSingleton constructor(context: Context) {
    val baseUrl = "http://localhost:8080"

    companion object {
        @Volatile
        private var INSTANCE: SongSingleton? = null

        fun getInstance(context: Context) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: SongSingleton(context).also {
                    INSTANCE = it
                }
            }

        val requestQueue: RequestQueue by lazy {
            Volley.newRequestQueue(context.applicationContext)
        }

        fun <T> addToRequestQueue(req: Request<T>) {
            requestQueue.add(req)
        }
    }
}