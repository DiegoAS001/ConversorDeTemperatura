package br.edu.ifsp.dmo.conversordetemperatura.model

object FahrenheitToCelsiusStrategy: TemperatureConverter {
    override fun converter(temperature: Double): Double {
        return 1.8 * temperature + 32
    }
    override fun getScale(): String {
        return "ºF"
    }
}