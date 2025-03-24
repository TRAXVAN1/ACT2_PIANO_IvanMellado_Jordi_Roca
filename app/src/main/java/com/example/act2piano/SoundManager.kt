package com.example.act2piano

import android.content.Context
import android.media.MediaPlayer

class SoundManager(private val context: Context) {
    private val soundMap = mapOf(
        //"B1" to R.raw.b1,
        "C2" to R.raw.c2,
        "C#2" to R.raw.db2,
        "D2" to R.raw.d2,
        "D#2" to R.raw.eb2,
        "E2" to R.raw.e2,
        "F2" to R.raw.f2,
        "F#2" to R.raw.gb2,
        "G2" to R.raw.g2,
        "G#2" to R.raw.ab2,
        "A2" to R.raw.a2,
        "A#2" to R.raw.bb2,
        "B2" to R.raw.b2,
        "C3" to R.raw.c3,
        "C#3" to R.raw.db3,
        "D3" to R.raw.d3,
        "D#3" to R.raw.eb3,
        "E3" to R.raw.e3,
        "F3" to R.raw.f3,
        "F#3" to R.raw.gb3,
        "G3" to R.raw.g3,
        "G#3" to R.raw.ab3,
        "A3" to R.raw.a3,
        "A#3" to R.raw.bb3,
        "B3" to R.raw.b3,
        "C4" to R.raw.c4,
        "C#4" to R.raw.db4
    )

    fun playSound(note: String) {
        soundMap[note]?.let { soundResId ->
            val mediaPlayer = MediaPlayer.create(context, soundResId)
            mediaPlayer?.start()
            mediaPlayer?.setOnCompletionListener { it.release() }
        }
    }
}
