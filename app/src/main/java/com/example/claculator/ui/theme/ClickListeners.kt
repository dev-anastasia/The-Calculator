package com.example.claculator.ui.theme

import android.widget.Button
import android.widget.TextView

class ClickListeners {
    // добавить отрицательные числа, если получится (оператор - в начале)

    private var first_number = 0.0
    private var operator = 'o'
    private var second_number = 0.0
    private var result = 0.0

    var pointAllowance = true

    fun addDigit(btn: Button, tv: TextView, c: Char) { // ГОТОВО
        btn.setOnClickListener {
            if (tv.text.startsWith('0') && tv.text.length == 1)
                tv.text = tv.text.toString().replaceFirst('0', c)
            else
                tv.text = tv.text.toString() + c
        }
    }

    fun addOperator(btn: Button, tv: TextView, c: Char) { // ГОТОВО
        btn.setOnClickListener {
            if (tv.text.toString().isEmpty())
                return@setOnClickListener
            else {
                if (tv.text.toString().contains('+') ||
                    tv.text.toString().contains('-') ||
                    tv.text.toString().contains('*') ||
                    tv.text.toString().contains('/')
                )
                    return@setOnClickListener
                else {
                    if (!tv.text.toString().last().isDigit()) {
                        tv.text = tv.text.dropLast(1).toString() + c
                        pointAllowance = true
                        setOperator(c)
                    } else {
                        first_number = tv.text.toString().toDouble()
                        tv.text = tv.text.toString() + c
                        pointAllowance = true
                        setOperator(c)
                    }
                }
            }
        }
    }

    fun addZero(btn: Button, tv: TextView, c: String) { // ГОТОВО
        btn.setOnClickListener {
            if (tv.text.toString().isEmpty()) {
                tv.text = "0"
                return@setOnClickListener
            } else { // строка не пустая
                if (tv.text.length == 1 && tv.text.toString().last() == '0') {
                    return@setOnClickListener
                }
                if (tv.text.length == 1 && tv.text.toString().last().isDigit()) {
                    tv.text = tv.text.toString() + c
                    return@setOnClickListener
                } else { // в строке больше 1 символа
                    if (tv.text.toString().last() == '0') {
                        val length = tv.text.length
                        if (tv.text.toString().get(length - 2) == '+' ||
                            tv.text.toString().get(length - 2) == '-' ||
                            tv.text.toString().get(length - 2) == '*' ||
                            tv.text.toString().get(length - 2) == '/' ||
                            tv.text.toString().get(length - 2) == '%'
                        ) {
                            return@setOnClickListener
                        } else {
                            tv.text = tv.text.toString() + c
                            return@setOnClickListener
                        }
                    } else { // последний символ НЕ был нулём
                        if (c == "00") {
                            val length = tv.text.length
                            if (tv.text.toString().get(length - 1) == '+' ||
                                tv.text.toString().get(length - 1) == '-' ||
                                tv.text.toString().get(length - 1) == '*' ||
                                tv.text.toString().get(length - 1) == '/' ||
                                tv.text.toString().get(length - 1) == '%'
                            ) {
                                tv.text = tv.text.toString() + '0'
                                return@setOnClickListener
                            }
                            if (tv.text.toString().get(length - 1) == '.') {
                                tv.text = tv.text.toString() + c
                                return@setOnClickListener
                            }
                        } else
                            tv.text = tv.text.toString() + c
                    }
                }
            }
        }
    }

    fun addPoint(btn: Button, tv: TextView) { // ГОТОВО
        btn.setOnClickListener {
            if (tv.text.toString().isEmpty())
                return@setOnClickListener
            if (pointAllowance && tv.text.toString().last().isDigit()) {
                tv.text = tv.text.toString() + '.'
                pointAllowance = false
            } else
                return@setOnClickListener
        }
    }

    fun addPercentage(btn: Button, tv: TextView, c: Char) { // ДОДЕЛАТЬ
        btn.setOnClickListener {
            if (tv.text.toString().isEmpty())
                return@setOnClickListener
            else {

            }
        }
    }

    fun clear(btn: Button, tv: TextView) { // ГОТОВО
        btn.setOnClickListener {
            if (tv.text.toString().isEmpty())
                return@setOnClickListener
            else {
                tv.text = ""
                pointAllowance = true
            }
        }
    }

    fun delete(btn: Button, tv: TextView) { // ГОТОВО
        btn.setOnClickListener {
            if (tv.text.toString().isEmpty())
                return@setOnClickListener
            else {
                if (tv.text.toString().last() == '.')
                    pointAllowance = true
                tv.text = tv.text.toString().substring(0, tv.text.lastIndex)

            }
        }
    }

    fun calculate(btn: Button, tv: TextView) {
        btn.setOnClickListener {
            second_number = (tv.text.split(operator)[1]).toDouble()
            when (operator) {
                ('+') -> result = first_number + second_number
                ('-') -> result = first_number - second_number
                ('*') -> result = first_number * second_number
                ('/') -> result = first_number / second_number
                else -> 0.0
            }
            if (result.toString().last() == '0')
                tv.text = result.toInt().toString()
            else tv.text = result.toString()
        }
    }

    private fun setOperator(c: Char) {
        operator = c
    }

}