package com.example.act2piano

import android.content.Context
import android.media.MediaPlayer

class SoundManager(private val context: Context) {
    private val soundMap = mapOf(
        "C2" to R.raw.c2,
        "D2" to R.raw.d2,
        "D#2" to R.raw.db2,
        "E2" to R.raw.e2,
        "F2" to R.raw.f2,
        "G2" to R.raw.g2,
        "G#2" to R.raw.gb2,
        "A2" to R.raw.a2,
        "A#2" to R.raw.ab2,
        "B2" to R.raw.b2,
        "C3" to R.raw.c3,
        "D3" to R.raw.d3,
        "D#3" to R.raw.db3,
        "E3" to R.raw.e3,
        "F3" to R.raw.f3,
        "G3" to R.raw.g3,
        "G#3" to R.raw.gb3,
        "A3" to R.raw.a3,
        "A#3" to R.raw.ab3,
        "B3" to R.raw.b3,
        "C4" to R.raw.c4
    )

    fun playSound(note: String) {
        soundMap[note]?.let { soundResId ->
            val mediaPlayer = MediaPlayer.create(context, soundResId)
            mediaPlayer?.start()
            mediaPlayer?.setOnCompletionListener { it.release() }
        }
    }
}
