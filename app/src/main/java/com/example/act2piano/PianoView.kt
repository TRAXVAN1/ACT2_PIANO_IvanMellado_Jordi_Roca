package com.example.act2piano

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.Log
import android.view.MotionEvent
import android.view.View

class PianoView(context: Context, private val soundManager: SoundManager) : View(context) {

    private val whiteKeys = arrayOf("C2", "D2", "E2", "F2", "G2", "A2", "B2",
        "C3", "D3", "E3", "F3", "G3", "A3", "B3", "C4")

    private val blackKeys = arrayOf("", "Db2", "Eb2", "", "Gb2", "Ab2", "Bb2", "",
        "Db3", "Eb3", "", "Gb3", "Ab3", "Bb3", "")

    private val whitePaint = Paint().apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }

    private val blackPaint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
    }

    private val pianoKeys = mutableListOf<PianoKey>()

    init {
        post {
            generateKeys()
            invalidate() // 🔹 Redibujar después de calcular dimensiones
        }
    }

    private fun generateKeys() {
        val keyWidth = width / whiteKeys.size.toFloat()
        val keyHeight = height.toFloat()

        pianoKeys.clear()

        // 🔹 Crear teclas blancas
        for (i in whiteKeys.indices) {
            val rect = RectF(i * keyWidth, 0f, (i + 1) * keyWidth, keyHeight)
            pianoKeys.add(PianoKey(whiteKeys[i], false, rect, whitePaint))
        }

        // 🔹 Crear teclas negras
        val blackKeyWidth = keyWidth * 0.6f
        val blackKeyHeight = keyHeight * 0.6f
        for (i in blackKeys.indices) {
            if (blackKeys[i].isNotEmpty()) {
                val left = (i + 0.7f) * keyWidth - blackKeyWidth / 2
                val rect = RectF(left, 0f, left + blackKeyWidth, blackKeyHeight)
                pianoKeys.add(PianoKey(blackKeys[i], true, rect, blackPaint))
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        pianoKeys.forEach { it.draw(canvas) }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            for (key in pianoKeys) {
                if (key.contains(event.x, event.y)) {
                    Log.d("PianoView", "Tecla presionada: ${key.note}")
                    soundManager.playSound(key.note)
                    return true
                }
            }
        }
        return false
    }
}
