package com.example.act2piano

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.Button
import android.graphics.drawable.StateListDrawable
import android.graphics.drawable.ColorDrawable
class PianoKey @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : Button(context, attrs) {

    var soundIndex: Int = 0
    private var soundStreamId: Int = 0

    init {
        setBackgroundResource(R.drawable.key_selector)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        parent.requestDisallowInterceptTouchEvent(true)
        when (event?.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                isPressed = true
                (context as? MainActivity)?.let {
                    soundStreamId = it.playSoundContinuous(soundIndex)
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                isPressed = false
                (context as? MainActivity)?.stopSound(soundStreamId)
            }
        }
        return true
    }
}
