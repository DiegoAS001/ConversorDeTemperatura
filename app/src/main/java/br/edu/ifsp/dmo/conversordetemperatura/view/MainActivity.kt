package br.edu.ifsp.dmo.conversordetemperatura.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.conversordetemperatura.R
import br.edu.ifsp.dmo.conversordetemperatura.databinding.ActivityMainBinding
import br.edu.ifsp.dmo.conversordetemperatura.model.CelsiusToFahrenheitStrategy
import br.edu.ifsp.dmo.conversordetemperatura.model.CelsiusToKelvinStrategy
import br.edu.ifsp.dmo.conversordetemperatura.model.FahrenheitToCelsiusStrategy
import br.edu.ifsp.dmo.conversordetemperatura.model.FahrenheitToKelvinStrategy
import br.edu.ifsp.dmo.conversordetemperatura.model.KelvinToCelsiusStrategy
import br.edu.ifsp.dmo.conversordetemperatura.model.KelvinToFahrenheitStrategy
import br.edu.ifsp.dmo.conversordetemperatura.model.TemperatureConverter
import kotlin.NumberFormatException
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var converterStrategy: TemperatureConverter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setClickListener()
    }

    private fun setClickListener() {
        binding.btnCelsiusToFahrenheit.setOnClickListener {
            handleConversion(CelsiusToFahrenheitStrategy)
        }
        binding.btnCelsiusToKelvin.setOnClickListener(View.OnClickListener {
            handleConversion(CelsiusToKelvinStrategy)
        })
        binding.btnFahrenheitToCelsius.setOnClickListener(View.OnClickListener {
            handleConversion(FahrenheitToCelsiusStrategy)
        })
        binding.btnFahrenheitToKelvin.setOnClickListener(View.OnClickListener {
            handleConversion(FahrenheitToKelvinStrategy)
        })
        binding.btnKelvinToCelsius.setOnClickListener(View.OnClickListener {
            handleConversion(KelvinToCelsiusStrategy)
        })
        binding.btnKelvinToFahrenheit.setOnClickListener(View.OnClickListener {
            handleConversion(KelvinToFahrenheitStrategy)
        })
    }

    private fun readTemperature(): Double {
        return try {
            binding.edittextTemperature.text.toString().toDouble()
        } catch (e: NumberFormatException) {
            throw NumberFormatException("Input Error")
        }
    }

    private fun handleConversion(strategy: TemperatureConverter) {
        converterStrategy = strategy
        try {
            val inputValue = readTemperature()
            binding.textviewResultNumber.text = String.format(
                "%.2f %s",
                converterStrategy.converter(inputValue),
                converterStrategy.getScale()
            )
            val messageResId = when (converterStrategy) {
                is CelsiusToFahrenheitStrategy -> R.string.msgCtoF
                is CelsiusToKelvinStrategy -> R.string.msgCtoK
                is FahrenheitToCelsiusStrategy -> R.string.msgFtoC
                is FahrenheitToKelvinStrategy -> R.string.msgFtok
                is KelvinToCelsiusStrategy -> R.string.msgKtoC
                is KelvinToFahrenheitStrategy -> R.string.msgKtoF
                else -> throw IllegalArgumentException("Unsupported strategy type")
            }
            binding.textviewResultMessage.text = getString(messageResId)

        } catch (e: Exception) {
            Toast.makeText(
                this,getString(R.string.error_popup_notify),
                Toast.LENGTH_SHORT
            ).show()
            Log.e("APP_DMO", e.stackTraceToString())
        }
    }



}