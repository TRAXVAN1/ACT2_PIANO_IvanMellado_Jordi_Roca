package com.example.act2piano

import android.content.Context
import android.media.MediaPlayer

class SoundManager(private val context: Context) {
    private val soundMap = mutableMapOf<String, Int>()

    init {
        soundMap["A2"] = R.raw.a2
        soundMap["A3"] = R.raw.a3
        soundMap["Ab2"] = R.raw.ab2
        soundMap["Ab3"] = R.raw.ab3
        soundMap["B2"] = R.raw.b2
        soundMap["B3"] = R.raw.b3
        soundMap["Bb2"] = R.raw.bb2
        soundMap["Bb3"] = R.raw.bb3
        soundMap["C2"] = R.raw.c2
        soundMap["C3"] = R.raw.c3
        soundMap["C4"] = R.raw.c4
        soundMap["Db2"] = R.raw.db2
        soundMap["Db3"] = R.raw.db3
        soundMap["D2"] = R.raw.d2
        soundMap["D3"] = R.raw.d3
        soundMap["Eb2"] = R.raw.eb2
        soundMap["Eb3"] = R.raw.eb3
        soundMap["E2"] = R.raw.e2
        soundMap["E3"] = R.raw.e3
        soundMap["F2"] = R.raw.f2
        soundMap["F3"] = R.raw.f3
        soundMap["Gb2"] = R.raw.gb2
        soundMap["Gb3"] = R.raw.gb3
        soundMap["G2"] = R.raw.g2
        soundMap["G3"] = R.raw.g3
    }

    fun playSound(note: String) {
        val resId = soundMap[note]
        if (resId != null) {
            val mediaPlayer = MediaPlayer.create(context, resId)
            mediaPlayer?.start()
            mediaPlayer?.setOnCompletionListener { it.release() }
        }
    }
}
