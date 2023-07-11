package com.example.claculator.ui.theme

import android.widget.Button
import android.widget.TextView

class ClickListeners {
    // добавить отрицательные числа, если получится (оператор - в начале)

    private var first_number = ""
    private var operator = ""
    private var second_number = ""
    private var result = ""

    var pointAllowance = true // в строку можно добавить 0 или 00
    var readyToClear = false // если true - все данные готовы к очистке

    fun addDigit(btn: Button, tv: TextView, c: Char) {
        btn.setOnClickListener {
            if (readyToClear) {
                tv.text = ""
                readyToClear = false
            }
            if (tv.text.startsWith('0') && tv.text.length == 1)
                tv.text = tv.text.toString().replaceFirst('0', c)
            else {
                if (tv.text.isNotEmpty() && tv.text.last() == '%')
                    return@setOnClickListener
                else
                    tv.text = tv.text.toString() + c
            }
        }
    }

    fun addOperator(btn: Button, tv: TextView, c: String) { // % оператором не является
        btn.setOnClickListener {
            if (readyToClear) {
                tv.text = ""
                readyToClear = false
            }
            if (tv.text.toString().isEmpty()) // добавить ОТРИЦАТЕЛЬНЫЕ ЧИСЛА
                return@setOnClickListener
            else { // если строка не пустая
                if (tv.text.last().toString() == operator) {
                    tv.text = tv.text.dropLast(1).toString() + c
                    setOperator(c)
                    return@setOnClickListener
                }
                if (tv.text.toString().contains('+') ||
                    tv.text.toString().contains('-') || // отрицат.числа
                    tv.text.toString().contains('*') ||
                    tv.text.toString().contains('/')
                )
                    return@setOnClickListener
                else {
                    if (!tv.text.toString().last().isDigit() && tv.text.toString().last() != '%') {
                        tv.text = tv.text.dropLast(1).toString() + c
                        pointAllowance = true
                        setOperator(c)
                    } else {
                        if (tv.text.toString().last() != '%') {
                            first_number = tv.text.toString()
                            tv.text = tv.text.toString() + c
                            pointAllowance = true
                            setOperator(c)
                        } else {
                            tv.text = tv.text.toString() + c
                            pointAllowance = true
                            setOperator(c)
                        }
                    }
                }
            }
        }
    }

    // Метод для добавления НУЛЯ и ДВУХ НУЛЕЙ
    fun addZero(btn: Button, tv: TextView, c: String) {
        btn.setOnClickListener {
            if (readyToClear) {
                tv.text = ""
                readyToClear = false
            }
            if (tv.text.toString().isEmpty()) { // строка пустая
                tv.text = "0"
            } else { // строка не пустая
                if (tv.text.length == 1 && tv.text.toString().last() == '0')
                    return@setOnClickListener
                if (tv.text.length == 1 && tv.text.toString().last().isDigit())
                    tv.text = tv.text.toString() + c
                else { // далее - в строке больше 1 символа
                    if (tv.text.last() == '%') {
                        return@setOnClickListener
                    }
                    if (tv.text.toString().last() == '0') { // последний был нулём
                        val length = tv.text.length
                        if (tv.text.toString().get(length - 2) == '+' || // перед нулём был оператор (т.е. началось новое число)
                            tv.text.toString().get(length - 2) == '-' ||
                            tv.text.toString().get(length - 2) == '*' ||
                            tv.text.toString().get(length - 2) == '/' ||
                            tv.text.toString().get(length - 2) == '%'
                        )
                            return@setOnClickListener
                        else {
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
                            else {
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
            if (readyToClear) {
                tv.text = ""
                readyToClear = false
            }
            if (tv.text.toString().isEmpty())
                return@setOnClickListener
            if (pointAllowance && tv.text.toString().last().isDigit()) {
                tv.text = tv.text.toString() + '.'
                pointAllowance = false
            } else
                return@setOnClickListener
        }
    }

    fun clear(btn: Button, tv: TextView) {
        btn.setOnClickListener {
            tv.text = ""
            pointAllowance = true
            clearInnerData()
        }
    }

    fun delete(btn: Button, tv: TextView) {
        btn.setOnClickListener {
            if (tv.text.toString().isEmpty())
                clearInnerData()
            else {
                if (tv.text.toString().last() == '%') {
                    if (second_number.isEmpty()) // удаляем данные о первом числе
                        first_number = ""
                    if (second_number.isNotEmpty())
                        second_number = "" // очищаем только данные о втором числе, оно посчитается заново потом
                }
                if (tv.text.toString().last() == '.')
                    pointAllowance = true
                tv.text = tv.text.toString().substring(0, tv.text.lastIndex)
            }
        }
    }

    fun calculate(btn: Button, tv: TextView) {
        btn.setOnClickListener {
            if (tv.text.isEmpty() || tv.text.last().toString() == operator)
                return@setOnClickListener
            else {
                if (operator.isEmpty()) { // если введено только одно число (или одно число с %)
                    if (first_number.endsWith(".0"))
                        tv.text = first_number.toDouble().toInt().toString()
                    else
                        tv.text = first_number
                } else { // если есть два числа и оператор
                    if (second_number.isEmpty()) { // находим второе число для операции
                        second_number = (tv.text.split(operator)[1])
                    }
                    if (tv.text.last() == '%' &&
                        !tv.text.dropLast(1).toString()
                            .contains('%') // когда только у второго числа указан % - считаем отдельно
                    ) {
                        second_number =
                            (first_number.toDouble() / 100 * second_number.toDouble()).toString()
                    }
                    when (operator) {
                        ("+") -> result =
                            (first_number.toDouble() + second_number.toDouble()).toString()

                        ("-") -> result =
                            (first_number.toDouble() - second_number.toDouble()).toString()

                        ("*") -> result =
                            (first_number.toDouble() * second_number.toDouble()).toString()

                        ("/") -> {
                            if (second_number.toDouble() != 0.0)
                                result =
                                    (first_number.toDouble() / second_number.toDouble()).toString()
                            else {
                                tv.text = "Делить на 0 нельзя!"
                                return@setOnClickListener
                            }
                        }
                    }
                    if (result.endsWith(".0")) {
                        tv.text = result.toDouble().toInt().toString()
                        clearInnerData()
                        readyToClear = true
                    } else {
                        tv.text = result
                        clearInnerData()
                        readyToClear = true
                    }
                }
            }
        }
    }

    fun addPercentage(btn: Button, tv: TextView) {
        btn.setOnClickListener {
            if (readyToClear) {
                tv.text = ""
                readyToClear = false
            }
            if (tv.text.isEmpty() ||
                tv.text.last().toString() == operator ||
                tv.text.last() == '%' ||
                tv.text.last() == '.'
            )
                return@setOnClickListener
            else {
                if (operator.isEmpty()) { // если пользователь ввел только одно число
                    first_number = (tv.text.toString().toDouble() / 100).toString()
                    tv.text = tv.text.toString() + '%'
                } else { // если есть два числа
                    if (tv.text.dropLast(1).toString().contains('%')
                    ) { // если оба числа указаны с %
                        second_number = (tv.text.split(operator)[1].toDouble() / 100).toString()
                        tv.text = tv.text.toString() + '%'
                    } else { // если указан % только у второго числа
                        second_number = tv.text.split(operator)[1]
                        tv.text = tv.text.toString() + '%'
                    }
                }
            }
        }
    }


    private fun setOperator(c: String) {
        operator = c
    }

    private fun clearInnerData() {
        first_number = ""
        second_number = ""
        operator = ""
        result = ""
    }

}