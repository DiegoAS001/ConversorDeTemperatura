package br.edu.ifsp.dmo.conversordetemperatura.model

object KelvinToFahrenheitStrategy: TemperatureConverter {
    override fun converter(temperature: Double) = (temperature * 1.8) - 459.67
    override fun getScale() = "ºF"
}