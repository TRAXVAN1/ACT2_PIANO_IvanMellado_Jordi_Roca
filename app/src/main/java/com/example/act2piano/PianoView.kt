package com.example.act2piano

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout

class PianoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    init {
        orientation = HORIZONTAL
        val notes = listOf("C", "D", "E", "F", "G", "A", "B")

        for (i in notes.indices) {
            val key = PianoKey(context).apply {
                soundIndex = i
                text = notes[i]  // Solo para referencia, el texto será invisible
                layoutParams = LayoutParams(0, LayoutParams.MATCH_PARENT, 1f)
            }
            addView(key)
        }
    }
}