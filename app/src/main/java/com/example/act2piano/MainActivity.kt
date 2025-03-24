package com.example.act2piano

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
class MainActivity : AppCompatActivity() {
    private lateinit var soundManager: SoundManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        soundManager = SoundManager(this)
    }

    fun playSoundContinuous(key: Int): Int {
        return soundManager.playSoundContinuous(key)
    }

    fun stopSound(streamId: Int) {
        soundManager.stopSound(streamId)
    }
}
