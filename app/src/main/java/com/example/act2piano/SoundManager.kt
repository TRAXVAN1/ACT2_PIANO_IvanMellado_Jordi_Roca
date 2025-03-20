package com.example.act2piano

import android.content.Context
import android.media.SoundPool

class SoundManager(context: Context) {
    private val soundPool = SoundPool.Builder().setMaxStreams(10).build()
    private val sounds = mutableMapOf<Int, Int>()

    init {
        val keys = arrayOf(
            R.raw.note_c,
            R.raw.note_d,
            R.raw.note_e,
            R.raw.note_f,
            R.raw.note_g,
            R.raw.note_a,
            R.raw.note_b
        )

        for (i in keys.indices) {
            sounds[i] = soundPool.load(context, keys[i], 1)
        }
    }

    fun playSound(key: Int) {
        sounds[key]?.let {
            soundPool.play(it, 1f, 1f, 0, 0, 1f)
        }
    }
}
