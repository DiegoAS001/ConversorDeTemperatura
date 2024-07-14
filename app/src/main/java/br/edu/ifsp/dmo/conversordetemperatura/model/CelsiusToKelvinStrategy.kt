package br.edu.ifsp.dmo.conversordetemperatura.model

object CelsiusToKelvinStrategy : TemperatureConverter {

    override fun converter(temperature: Double) = (temperature + 273)
    override fun getScale() = "ºK"
}