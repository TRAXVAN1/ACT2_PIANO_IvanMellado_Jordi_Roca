package com.example.act2piano

import android.content.Context
import android.graphics.*
import android.util.Log
import android.view.MotionEvent
import android.view.View

class PianoView(context: Context) : View(context) {

    private val whiteKeys = listOf(
        PianoKey("C2"), PianoKey("D2"), PianoKey("E2"),
        PianoKey("F2"), PianoKey("G2"), PianoKey("A2"),
        PianoKey("B2"), PianoKey("C3"), PianoKey("D3"), PianoKey("E3"),
        PianoKey("F3"), PianoKey("G3"), PianoKey("A3"), PianoKey("B3"),
        PianoKey("C4")
    )

    private val blackKeys = listOf(
        PianoKey("C#2"), PianoKey("D#2"), null, PianoKey("F#2"),
        PianoKey("G#2"), PianoKey("A#2"), null, PianoKey("C#3"),
        PianoKey("D#3"), null, PianoKey("F#3"), PianoKey("G#3"), PianoKey("A#3"), null, PianoKey("C#4")
    )

    private val whitePaint = Paint().apply {
        color = Color.rgb(252, 249, 179)
        style = Paint.Style.FILL
    }

    private val blackPaint = Paint().apply {
        color = Color.rgb(45, 0, 61)
        style = Paint.Style.FILL
    }

    private val pressedPaint = Paint().apply {
        color = Color.rgb(166, 165, 151)
        style = Paint.Style.FILL
    }

    private val borderPaint = Paint().apply {
        color = Color.rgb(46, 26, 11)
        style = Paint.Style.STROKE
        strokeWidth = 4f
    }

    private val textPaint = Paint().apply {
        color = Color.rgb(46, 26, 11)
        textSize = 50f
        textAlign = Paint.Align.CENTER
    }

    private val pressedKeys = mutableSetOf<String>()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val keyWidth = width / whiteKeys.size.toFloat()
        val keyHeight = height.toFloat()

        // Draw white keys
        for (i in whiteKeys.indices) {
            val left = i * keyWidth
            val right = left + keyWidth
            val paint = if (whiteKeys[i].note in pressedKeys) pressedPaint else whitePaint
            canvas.drawRect(left, 0f, right, keyHeight, paint)
            canvas.drawRect(left, 0f, right, keyHeight, borderPaint)
            canvas.drawText(whiteKeys[i].note, left + keyWidth / 2, keyHeight - 50, textPaint)
        }

        // Draw black keys
        val blackKeyWidth = keyWidth * 0.6f
        val blackKeyHeight = keyHeight * 0.6f
        for (i in blackKeys.indices) {
            blackKeys[i]?.let { key ->
                val left = (i + 0.7f) * keyWidth - blackKeyWidth / 2
                val right = left + blackKeyWidth
                val paint = if (key.note in pressedKeys) pressedPaint else blackPaint
                canvas.drawRect(left, 0f, right, blackKeyHeight, paint)
                canvas.drawRect(left, 0f, right, blackKeyHeight, borderPaint)

                textPaint.color = Color.WHITE
                canvas.drawText(key.note, left + blackKeyWidth / 2, blackKeyHeight - 20, textPaint)
                textPaint.color = Color.BLACK
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val keyWidth = width / whiteKeys.size.toFloat()
        val blackKeyWidth = keyWidth * 0.6f
        val blackKeyHeight = height * 0.6f

        when (event.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                val pointerIndex = event.actionIndex
                val x = event.getX(pointerIndex)
                val y = event.getY(pointerIndex)
                val key = findKeyAtPosition(x, y, keyWidth, blackKeyWidth, blackKeyHeight)
                key?.let {
                    pressedKeys.add(it.note)
                    (context as MainActivity).playSound(it.note)
                    invalidate()
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                val pointerIndex = event.actionIndex
                val x = event.getX(pointerIndex)
                val y = event.getY(pointerIndex)
                val key = findKeyAtPosition(x, y, keyWidth, blackKeyWidth, blackKeyHeight)
                key?.let {
                    pressedKeys.remove(it.note)
                    invalidate()
                }
            }
            MotionEvent.ACTION_MOVE -> {
                pressedKeys.clear()
                for (i in 0 until event.pointerCount) {
                    val x = event.getX(i)
                    val y = event.getY(i)
                    val key = findKeyAtPosition(x, y, keyWidth, blackKeyWidth, blackKeyHeight)
                    key?.let { pressedKeys.add(it.note) }
                }
                invalidate()
            }
        }
        return true
    }

    private fun findKeyAtPosition(x: Float, y: Float, keyWidth: Float, blackKeyWidth: Float, blackKeyHeight: Float): PianoKey? {
        // Check black keys first
        for (i in blackKeys.indices) {
            blackKeys[i]?.let { key ->
                val left = (i + 0.7f) * keyWidth - blackKeyWidth / 2
                val right = left + blackKeyWidth
                if (x >= left && x <= right && y <= blackKeyHeight) {
                    return key
                }
            }
        }

        // Check white keys
        val keyIndex = (x / keyWidth).toInt()
        if (keyIndex in whiteKeys.indices) {
            return whiteKeys[keyIndex]
        }

        return null
    }
}
