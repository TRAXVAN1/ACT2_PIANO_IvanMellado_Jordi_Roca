package com.example.act2piano

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.LinearLayout
class PianoView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    init {
        // Contenedor para teclas blancas
        val whiteKeysContainer = LinearLayout(context).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)
        }

        // Notas blancas
        val whiteNotes = listOf("C4", "D4", "E4", "F4", "G4", "A4", "B4")

        // Añadir teclas blancas
        for (i in whiteNotes.indices) {
            val key = PianoKey(context).apply {
                soundIndex = i
                text = whiteNotes[i]
                contentDescription = "Tecla blanca ${whiteNotes[i]}"
                layoutParams = LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1f)
                setBackgroundColor(Color.WHITE)
                setTextColor(Color.BLACK)
            }
            whiteKeysContainer.addView(key)
        }

        addView(whiteKeysContainer)

        // Añadir teclas negras
        val blackNotes = listOf("C#4", "D#4", "F#4", "G#4", "A#4")
        val blackKeyPositions = intArrayOf(0, 1, 3, 4, 5) // Posiciones después de las teclas blancas

        post {
            val whiteKeyWidth = width / whiteNotes.size
            val blackKeyWidth = (whiteKeyWidth * 0.6f).toInt()
            val blackKeyHeight = (height * 0.6f).toInt()

            for (i in blackKeyPositions.indices) {
                val position = blackKeyPositions[i]
                val key = PianoKey(context).apply {
                    soundIndex = whiteNotes.size + i
                    text = blackNotes[i]
                    contentDescription = "Tecla negra ${blackNotes[i]}"
                    layoutParams = LayoutParams(blackKeyWidth, blackKeyHeight).apply {
                        leftMargin = (position * whiteKeyWidth + whiteKeyWidth * 0.7f).toInt()
                    }
                    setBackgroundColor(Color.BLACK)
                    setTextColor(Color.WHITE)
                }
                addView(key)
            }
        }
    }
}
