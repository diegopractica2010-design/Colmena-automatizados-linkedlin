package cl.colmena.assistant

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 40, 32, 32)
        }
        root.addView(TextView(this).apply {
            text = "COLMENA ASSISTANT\\n\\nAsistente flotante para preparar mensajes personalizados."
            textSize = 23f
        })
        root.addView(action("1. Permitir ventana flotante") {
            if (!Settings.canDrawOverlays(this)) {
                startActivity(Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName")))
            } else startService(Intent(this, FloatingPanelService::class.java))
        })
        root.addView(action("2. Activar accesibilidad") {
            startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
        })
        root.addView(action("3. Abrir LinkedIn") {
            val launch = packageManager.getLaunchIntentForPackage("com.linkedin.android")
            if (launch != null) startActivity(launch)
            else startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/")))
        })
        root.addView(TextView(this).apply {
            text = "\\nUso: el asistente prepara el texto. Revisa el destinatario y envía manualmente desde LinkedIn. No automatiza envíos ni recorre perfiles."
            textSize = 14f
        })
        setContentView(root)
    }

    private fun action(label: String, onClick: () -> Unit) = Button(this).apply {
        text = label
        setOnClickListener { onClick() }
        layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}
