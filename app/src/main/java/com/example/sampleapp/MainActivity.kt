package com.example.sampleapp

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.*

class MainActivity : Activity() {

    private var count = 0
    private var darkMode = true

    private lateinit var root: LinearLayout
    private lateinit var counterText: TextView
    private lateinit var nameInput: EditText

    private val orange = Color.rgb(255, 140, 40)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createInterface()
    }

    private fun createInterface() {
        root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 32, 24, 24)
        }

        val scroll = ScrollView(this)
        scroll.addView(root)

        setContentView(scroll)
        updateTheme()

        addText("MINILAB", 30f, orange)
        addText("Тестовая лаборатория Android", 14f)

        addSpace()

        addText("Счётчик", 20f)

        counterText = addText("0", 42f, orange)

        addButton("Увеличить") {
            count++
            counterText.text = count.toString()
        }

        addButton("Сбросить") {
            count = 0
            counterText.text = "0"
        }

        addSpace()

        addText("Приветствие", 20f)

        nameInput = EditText(this).apply {
            hint = "Введи своё имя"
            setSingleLine()
            setTextColor(if (darkMode) Color.WHITE else Color.BLACK)
        }

        root.addView(nameInput)

        addButton("Поздороваться") {
            val name = nameInput.text.toString().trim()

            if (name.isEmpty()) {
                Toast.makeText(
                    this,
                    "Введи имя",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Привет, $name!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        addSpace()

        addText("Устройство", 20f)

        addText("Модель: ${android.os.Build.MODEL}")
        addText("Android: ${android.os.Build.VERSION.RELEASE}")
        addText("SDK: ${android.os.Build.VERSION.SDK_INT}")

        addSpace()

        addButton("Сменить тему") {
            darkMode = !darkMode
            updateTheme()
        }

        addText("MiniLab v1.0", 12f, Color.GRAY)
    }

    private fun addText(
        value: String,
        size: Float = 16f,
        color: Int = if (darkMode) Color.WHITE else Color.BLACK
    ): TextView {
        val view = TextView(this).apply {
            text = value
            textSize = size
            setTextColor(color)
            setPadding(0, 8, 0, 8)
        }

        root.addView(view)
        return view
    }

    private fun addButton(
        title: String,
        action: () -> Unit
    ) {
        val button = Button(this).apply {
            text = title
            setTextColor(Color.WHITE)
            setBackgroundColor(orange)
            setOnClickListener { action() }
        }

        root.addView(
            button,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 8, 0, 8)
            }
        )
    }

    private fun addSpace() {
        val space = Space(this)
        root.addView(
            space,
            LinearLayout.LayoutParams(1, 24)
        )
    }

    private fun updateTheme() {
        root.setBackgroundColor(
            if (darkMode) Color.rgb(15, 15, 15)
            else Color.WHITE
        )
    }
}
