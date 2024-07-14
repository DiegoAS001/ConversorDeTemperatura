package br.edu.ifsp.dmo.conversordetemperatura.model

object FahrenheitToKelvinStrategy : TemperatureConverter {
    override fun converter(temperature: Double): Double {  return (temperature + 459.67) * 5/9 }

    override fun getScale(): String { return "ºK" }
}