package cl.colmena.assistant

object MessageComposer {
    fun create(name: String): String {
        val first = name.trim().split(Regex("\\s+")).firstOrNull().orEmpty()
        val greeting = if (first.isBlank()) "Hola, ¿cómo estás?" else "Hola, $first, ¿cómo estás?"
        return """$greeting

Muchas gracias por aceptar mi solicitud de contacto. Espero que estés muy bien.

Te cuento que actualmente trabajo en Isapre Colmena, ayudando a mujeres y sus familias a evaluar alternativas de cobertura de salud, ya sea desde Fonasa o revisando un cambio de Isapre.

Si te interesa conocer otras opciones o revisar tu plan actual, puedo orientarte sin compromiso.

¡Un abrazo y mucho éxito!"""
    }
}
