# Colmena Assistant Android

Aplicación Android nativa en Kotlin para asistir en la preparación manual de mensajes personalizados desde LinkedIn.

## Compilación desde tablet
El repositorio incluye GitHub Actions para compilar un APK debug. Desde GitHub móvil/web:
1. Abre **Actions**.
2. Selecciona **Build Android APK**.
3. Pulsa **Run workflow**.
4. Cuando termine, abre la ejecución y descarga el artefacto **ColmenaAssistant-debug-apk**.

## Alcance de esta versión
- Aplicación Android nativa.
- Panel flotante.
- Plantilla local de mensaje personalizada con el primer nombre.
- Accesibilidad para leer texto visible con permiso explícito del usuario.
- Confirmación manual: el usuario revisa y pulsa Enviar en LinkedIn.

No automatiza envíos, no extrae listas masivas ni deduce género a partir de nombres o fotografías. No garantiza detección de conversaciones de LinkedIn; cualquier caso ambiguo debe revisarse manualmente.

## Requisitos
- Android 8.0 (API 26) o superior.
- JDK 17 y Android Gradle Plugin, resueltos por GitHub Actions.
