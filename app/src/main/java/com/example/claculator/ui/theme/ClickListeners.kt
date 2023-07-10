package com.example.claculator.ui.theme

import android.widget.Button
import android.widget.TextView

class ClickListeners {

    fun clear(btn: Button, tv: TextView) { // ГОТОВО
        btn.setOnClickListener {
            tv.text = ""
        }
    }

    fun delete(btn: Button, tv: TextView) { // ГОТОВО
        btn.setOnClickListener {
            if (tv.text == "")
                return@setOnClickListener
            else
                tv.text = tv.text.toString().substring(0, tv.text.lastIndex)
        }
    }

    fun addDigit(btn: Button, tv: TextView, c: Char) {
        btn.setOnClickListener {
            if (tv.text.startsWith('0') && tv.text.toString().length == 1)
                tv.text = tv.text.toString().replaceFirst('0', c)
            else
                tv.text = tv.text.toString() + c
        }
    }

    fun addPoint(btn: Button, tv: TextView) {
        btn.setOnClickListener {
            if (tv.text.toString().contains(','))
                return@setOnClickListener
            else
                tv.text = tv.text.toString() + ','
        }
    }

    fun addTwoZeros(btn: Button, tv: TextView) {
        btn.setOnClickListener {
            if (tv.text.toString().isEmpty())
                tv.text = "0"
            else if (tv.text.startsWith('0') && tv.text.toString().length == 1)
                tv.text = "0"
            else
                tv.text = tv.text.toString() + "00"
        }
    }


}

