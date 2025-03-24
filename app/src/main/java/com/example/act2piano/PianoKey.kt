package com.example.act2piano

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF

class PianoKey(
    val note: String,
    val isBlack: Boolean,
    private val rect: RectF,
    private val paint: Paint
) {
    fun draw(canvas: Canvas) {
        canvas.drawRect(rect, paint)
    }

    fun contains(x: Float, y: Float): Boolean {
        return rect.contains(x, y)
    }
}
