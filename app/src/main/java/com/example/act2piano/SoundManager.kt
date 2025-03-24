package com.example.act2piano

import android.content.Context
import android.media.SoundPool
class SoundManager(context: Context) {
    private val soundPool = SoundPool.Builder().setMaxStreams(10).build()
    private val sounds = mutableMapOf<Int, Int>()
    private val soundsLoaded = mutableMapOf<Int, Boolean>()

    init {
        val keys = arrayOf(
            // Teclas blancas
            R.raw.a3,
            R.raw.b3,
            R.raw.c4,
            R.raw.d4,
            R.raw.e4,
            R.raw.f4,
            R.raw.g4,
            // Teclas negras
            R.raw.a_3,
            R.raw.c_3,
            R.raw.d_4,
            R.raw.f_4,
            R.raw.g_4
        )

        soundPool.setOnLoadCompleteListener { _, sampleId, status ->
            if (status == 0) {
                soundsLoaded[sampleId] = true
            }
        }

        for (i in keys.indices) {
            val soundId = soundPool.load(context, keys[i], 1)
            sounds[i] = soundId
            soundsLoaded[soundId] = false
        }
    }

    fun playSound(key: Int) {
        sounds[key]?.let { soundId ->
            if (soundsLoaded[soundId] == true) {
                soundPool.play(soundId, 1f, 1f, 1, 0, 1f)
            }
        }
    }
    fun playSoundContinuous(key: Int): Int {
        var streamId = 0
        sounds[key]?.let { soundId ->
            if (soundsLoaded[soundId] == true) {
                streamId = soundPool.play(soundId, 1f, 1f, 1, -1, 1f)
            }
        }
        return streamId
    }

    fun stopSound(streamId: Int) {
        if (streamId > 0) {
            soundPool.stop(streamId)
        }
    }

}
