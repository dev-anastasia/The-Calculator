package com.example.claculator.ui.theme

import android.widget.Button
import android.widget.TextView

class ClickListeners {

    var pointAllowance = true
    var zeroAllowance = true

    fun addDigit(btn: Button, tv: TextView, c: Char) { // ГОТОВО
        btn.setOnClickListener {
            if (tv.text.startsWith('0') && tv.text.toString().length == 1)
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
                if (!tv.text.toString().last().isDigit()) {
                    tv.text = tv.text.dropLast(1).toString() + c
                    pointAllowance = true
                } else {
                    tv.text = tv.text.toString() + c
                    pointAllowance = true
                }
            }
        }
    }

    fun addZero(btn: Button, tv: TextView) {
        btn.setOnClickListener {
                if (tv.text.startsWith('0') && tv.text.toString().length == 1)
                    return@setOnClickListener
                else {
                    tv.text = tv.text.toString() + '0'
                }
        }
    }

    fun addTwoZeros(btn: Button, tv: TextView) { // ГОТОВО
        btn.setOnClickListener {
            if (tv.text.toString().isEmpty())
                tv.text = "0"
            else {
                if (tv.text.startsWith('0') && tv.text.toString().length == 1)
                    tv.text = "0"
                else
                    tv.text = tv.text.toString() + "00"
            }
        }
    }

    fun addPoint(btn: Button, tv: TextView) { // ГОТОВО
        btn.setOnClickListener {
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
            tv.text = ""
            pointAllowance = true
        }
    }

    fun delete(btn: Button, tv: TextView) { // ГОТОВО
        btn.setOnClickListener {
            if (tv.text == "")
                return@setOnClickListener
            else {
                if (tv.text.toString().last() == '.')
                    pointAllowance = true
                tv.text = tv.text.toString().substring(0, tv.text.lastIndex)

            }
        }
    }
}