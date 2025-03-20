package com.example.act2piano

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.Button

class PianoKey @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : Button(context, attrs) {

    var soundIndex: Int = 0

    init {
        setBackgroundResource(R.drawable.key_selector)
        setTextColor(Color.TRANSPARENT)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        parent.requestDisallowInterceptTouchEvent(true)
        when (event?.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                setPressed(true)
                (context as? MainActivity)?.playSound(soundIndex)
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> setPressed(false)
        }
        return true
    }

}
