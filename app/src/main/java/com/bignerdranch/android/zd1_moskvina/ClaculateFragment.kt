package com.bignerdranch.android.zd1_moskvina

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager

class ClaculateFragment : Fragment() {

    companion object {
        private const val ARG_AMOUNT = "amount"
        private const val ARG_MONTH = "month"
        private const val ARG_RESULT = "result"

       fun newInstance(amount: Int, month: Int, result: String): ClaculateFragment {
            val fragment = ClaculateFragment()
            fragment.arguments = Bundle().apply {
                putInt(ARG_AMOUNT, amount)
                putInt(ARG_MONTH, month)
                putString(ARG_RESULT, result)
            }
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_claculate, container, false)

        val sumText: TextView = view.findViewById(R.id.sumText)
        val srokText: TextView = view.findViewById(R.id.srokText)
        val rezText: TextView = view.findViewById(R.id.reztext)
        val btnReg: Button = view.findViewById(R.id.btnRegistration)

        val amount = arguments?.getInt(ARG_AMOUNT) ?: 0
        val month = arguments?.getInt(ARG_MONTH) ?: 0
        val result = arguments?.getString(ARG_RESULT) ?: ""

        sumText.text = "Сумма кредита: $amount руб."
        srokText.text = "Срок кредита: $month мес."
        rezText.text = result

        btnReg.setOnClickListener {
           parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        return view
    }
}