package com.example.act2piano

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.View

class PianoView(context: Context) : View(context) {

    private val whiteKeys = listOf(
        PianoKey("C2"), PianoKey("D2"), PianoKey("E2"), PianoKey("F2"),
        PianoKey("G2"), PianoKey("A2"), PianoKey("B2"),
        PianoKey("C3"), PianoKey("D3"), PianoKey("E3"), PianoKey("F3"),
        PianoKey("G3"), PianoKey("A3"), PianoKey("B3"), PianoKey("C4")
    )

    private val blackKeys = listOf(
        null, PianoKey("D#2"), PianoKey("E#2"), null, PianoKey("G#2"),
        PianoKey("A#2"), PianoKey("B#2"), null, PianoKey("D#3"),
        PianoKey("E#3"), null, PianoKey("G#3"), PianoKey("A#3"), PianoKey("B#3"), null
    )

    private val whitePaint = Paint().apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }

    private val blackPaint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.FILL
    }

    private val textPaint = Paint().apply {
        color = Color.BLACK
        textSize = 50f
        textAlign = Paint.Align.CENTER
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val keyWidth = width / whiteKeys.size.toFloat()
        val keyHeight = height.toFloat()

        // 🔹 Dibujar teclas blancas
        for (i in whiteKeys.indices) {
            val left = i * keyWidth
            val right = left + keyWidth
            canvas.drawRect(left, 0f, right, keyHeight, whitePaint)
            canvas.drawText(whiteKeys[i].note, left + keyWidth / 2, keyHeight - 50, textPaint)
        }

        // 🔹 Dibujar teclas negras
        val blackKeyWidth = keyWidth * 0.6f
        val blackKeyHeight = keyHeight * 0.6f
        for (i in blackKeys.indices) {
            blackKeys[i]?.let { key ->
                val left = (i + 0.7f) * keyWidth - blackKeyWidth / 2
                val right = left + blackKeyWidth
                canvas.drawRect(left, 0f, right, blackKeyHeight, blackPaint)

                textPaint.color = Color.WHITE
                canvas.drawText(key.note, left + blackKeyWidth / 2, blackKeyHeight - 20, textPaint)
                textPaint.color = Color.BLACK
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val keyWidth = width / whiteKeys.size.toFloat()
            val blackKeyWidth = keyWidth * 0.6f
            val blackKeyHeight = height * 0.6f
            val x = event.x
            val y = event.y

            // 🔹 Primero comprobamos si se tocó una tecla negra
            for (i in blackKeys.indices) {
                blackKeys[i]?.let { key ->
                    val left = (i + 0.7f) * keyWidth - blackKeyWidth / 2
                    val right = left + blackKeyWidth
                    if (x >= left && x <= right && y <= blackKeyHeight) {
                        Log.d("PianoView", "Tecla negra presionada: ${key.note}")
                        (context as MainActivity).playSound(key.note)
                        return true
                    }
                }
            }

            // 🔹 Si no se tocó una negra, comprobamos las blancas
            val keyIndex = (x / keyWidth).toInt()
            if (keyIndex in whiteKeys.indices) {
                Log.d("PianoView", "Tecla blanca presionada: ${whiteKeys[keyIndex].note}")
                (context as MainActivity).playSound(whiteKeys[keyIndex].note)
            }

            return true
        }
        return false
    }
}
