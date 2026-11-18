package com.bignerdranch.android.zd1_moskvina

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class CreditFragment : Fragment() {
    private lateinit var btnBack: Button
    private lateinit var seek: SeekBar
    private lateinit var amountText: TextView
    private lateinit var srok: EditText
    private lateinit var btnCal: Button
    private lateinit var rezult: TextView

    private var amount = 30000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_credit, container, false)

        btnBack = view.findViewById(R.id.btnBack)
        seek = view.findViewById(R.id.slider)
        amountText = view.findViewById(R.id.amountText)
        srok = view.findViewById(R.id.srokET)
        btnCal = view.findViewById(R.id.calculate)
        rezult = view.findViewById(R.id.rezText)

        amountText.text = "$amount руб."

        seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                amount = 30000 + progress
                amountText.text = "$amount руб."
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        btnBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        btnCal.setOnClickListener {
            val month = srok.text.toString().trim().toIntOrNull()
            if (month == null || month <= 0) {
                Toast.makeText(requireContext(), "Введите срок кредита в месяцах", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val base = amount.toDouble() / month
            val s1 = base + amount * 0.059

            val result = when {
                month <= 12 -> "Ежемесячный платёж: ${thousand(s1)} тыс. руб."
                month <= 24 -> {
                    val paid12 = base * 12
                    val s2 = base + (amount - paid12) * 0.051
                    "Первые 12 мес.: ${thousand(s1)} тыс. руб.\n" +
                            "С 13-го мес.: ${thousand(s2)} тыс. руб."
                }
                else -> {
                    val paid12 = base * 12
                    val s2 = base + (amount - paid12) * 0.051
                    val paid24 = base * 24
                    val s3 = base + (amount - paid24) * 0.042
                    "Первые 12 мес.: ${thousand(s1)} тыс. руб.\n" +
                            "С 13-го мес.: ${thousand(s2)} тыс. руб.\n" +
                            "С 25-го мес.: ${thousand(s3)} тыс. руб."
                }
            }

            rezult.text = result

            AlertDialog.Builder(requireContext())
                .setTitle("Результат")
                .setMessage("Сумма кредита: $amount руб.\nСрок: $month мес.\n$result")
                .setPositiveButton("OK") { _, _ ->
                    parentFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragmentContainer,
                            ClaculateFragment.newInstance(amount, month, result)
                        )
                        .addToBackStack(null)
                        .commit()
                }
                .show()
        }
        return view
    }

    private fun thousand(value: Double): String = String.format("%.2f", value / 1000)
}