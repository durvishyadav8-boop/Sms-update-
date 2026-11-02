package com.personal.smsforwarder

import android.app.Activity
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView

class MainActivity : Activity() {

    private var active = false
    private lateinit var status: TextView
    private lateinit var button: Button

    private val bg = Color.rgb(24, 26, 29)
    private val card = Color.rgb(43, 46, 51)
    private val grey = Color.rgb(180, 185, 192)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showDashboard()
    }

    private fun showDashboard() {
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(24, 40, 24, 30)
            setBackgroundColor(bg)
        }

        val heading = TextView(this).apply {
            text = "Welcome"
            textSize = 29f
            setTextColor(Color.WHITE)
        }
        root.addView(heading)

        val subtitle = TextView(this).apply {
            text = "Your personal SMS dashboard"
            textSize = 14f
            setTextColor(grey)
        }
        root.addView(subtitle)

        root.addView(space(28))

        val statusCard = makeCard()
        statusCard.addView(cardTitle("Demo Status"))
        status = cardValue("STOPPED")
        statusCard.addView(status)
        root.addView(statusCard)

        root.addView(space(14))

        val permissionCard = makeCard()
        permissionCard.addView(cardTitle("SMS Permission"))
        permissionCard.addView(cardValue("Not requested — UI demo"))
        root.addView(permissionCard)

        root.addView(space(26))

        button = Button(this).apply {
            text = "START"
            textSize = 17f
            setTextColor(Color.WHITE)
            isAllCaps = true
            setOnClickListener {
                active = !active
                updateStatus()
            }
        }

        root.addView(button, LinearLayout.LayoutParams(220, 58))

        root.addView(space(26))

        root.addView(TextView(this).apply {
            text = "APP VERSION 1.0"
            textSize = 12f
            gravity = Gravity.CENTER
            setTextColor(grey)
        })

        setContentView(ScrollView(this).apply {
            setBackgroundColor(bg)
            addView(root)
        })

        updateStatus()
    }

    private fun updateStatus() {
        status.text = if (active) "DEMO ACTIVE" else "STOPPED"
        status.setTextColor(
            if (active) Color.rgb(70, 210, 120) else Color.rgb(240, 90, 90)
        )
        button.text = if (active) "STOP" else "START"
        button.setBackgroundColor(
            if (active) Color.rgb(190, 55, 55) else Color.rgb(40, 150, 85)
        )
    }

    private fun makeCard(): LinearLayout {
        return LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 18, 20, 18)
            background = GradientDrawable().apply {
                setColor(card)
                cornerRadius = 22f
            }
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        }
    }

    private fun cardTitle(value: String) = TextView(this).apply {
        text = value
        textSize = 14f
        setTextColor(grey)
    }

    private fun cardValue(value: String) = TextView(this).apply {
        text = value
        textSize = 19f
        setTextColor(Color.WHITE)
        setPadding(0, 8, 0, 0)
    }

    private fun space(height: Int) = View(this).apply {
        layoutParams = LinearLayout.LayoutParams(1, height)
    }
}
