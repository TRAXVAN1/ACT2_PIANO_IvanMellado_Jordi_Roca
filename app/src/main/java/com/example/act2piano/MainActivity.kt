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

        val pianoContainer = findViewById<FrameLayout>(R.id.pianoContainer)
        val pianoView = PianoView(this)
        pianoContainer.addView(pianoView)
    }

    fun playSound(index: Int) {
        soundManager.playSound(index)
    }
}
