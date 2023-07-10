package com.example.claculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.example.claculator.databinding.ActivityMainBinding
import com.example.claculator.ui.theme.ClickListeners

class MainActivity : ComponentActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val clickListeners = ClickListeners()

        // Вид
//        if (binding.tvResult.toString().length > 30)
//            binding.tvResult.textSize = 32.0F

        // Операции
        clickListeners.clear(binding.btnClear, binding.tvResult)
        clickListeners.delete(binding.btnDeleteLast, binding.tvResult)

        // Цифры
        clickListeners.addDigit(binding.btnOne, binding.tvResult, '1')
        clickListeners.addDigit(binding.btnTwo, binding.tvResult, '2')
        clickListeners.addDigit(binding.btnThree, binding.tvResult, '3')
        clickListeners.addDigit(binding.btnFour, binding.tvResult, '4')
        clickListeners.addDigit(binding.btnFive, binding.tvResult, '5')
        clickListeners.addDigit(binding.btnSix, binding.tvResult, '6')
        clickListeners.addDigit(binding.btnSeven, binding.tvResult, '7')
        clickListeners.addDigit(binding.btnEight, binding.tvResult, '8')
        clickListeners.addDigit(binding.btnNine, binding.tvResult, '9')
        clickListeners.addDigit(binding.btnZero, binding.tvResult, '0')
        clickListeners.addPoint(binding.btnPoint, binding.tvResult) // ,
        clickListeners.addTwoZeros(binding.btnTwoZeros, binding.tvResult) // 00
    }
}