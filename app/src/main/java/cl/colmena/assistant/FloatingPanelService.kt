package cl.colmena.assistant

import android.app.Service
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Intent
import android.graphics.PixelFormat
import android.os.Build
import android.os.IBinder
import android.view.Gravity
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

class FloatingPanelService : Service() {
    private lateinit var manager: WindowManager
    private var panel: android.view.View? = null

    override fun onCreate() {
        super.onCreate()
        manager = getSystemService(WINDOW_SERVICE) as WindowManager
        val box = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(18, 16, 18, 16)
            setBackgroundColor(0xF5FFFFFF.toInt())
        }
        box.addView(TextView(this).apply {
            text = "COLMENA ASSISTANT"
            textSize = 16f
            setTextColor(0xFF007F86.toInt())
        })
        val name = EditText(this).apply {
            hint = "Nombre del contacto"
            setSingleLine(true)
        }
        box.addView(name)
        val preview = TextView(this).apply {
            text = "Escribe el nombre y prepara el mensaje."
            textSize = 13f
        }
        box.addView(Button(this).apply {
            text = "Preparar mensaje"
            setOnClickListener {
                preview.text = MessageComposer.create(name.text.toString())
            }
        })
        box.addView(preview)
        box.addView(Button(this).apply {
            text = "Copiar mensaje"
            setOnClickListener {
                val clipboard = getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
                clipboard.setPrimaryClip(
                    ClipData.newPlainText("Mensaje Colmena", preview.text)
                )
            }
        })
        box.addView(Button(this).apply {
            text = "Cerrar panel"
            setOnClickListener { stopSelf() }
        })
        panel = box
        val type = if (Build.VERSION.SDK_INT >= 26) {
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
        } else {
            WindowManager.LayoutParams.TYPE_PHONE
        }
        val params = WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            type,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
            PixelFormat.TRANSLUCENT
        ).apply {
            gravity = Gravity.END or Gravity.CENTER_VERTICAL
            x = 12
            y = 0
        }
        manager.addView(box, params)
    }

    override fun onDestroy() {
        panel?.let { manager.removeView(it) }
        panel = null
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
