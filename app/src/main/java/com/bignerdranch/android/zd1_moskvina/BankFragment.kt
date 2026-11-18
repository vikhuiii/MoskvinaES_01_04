package com.bignerdranch.android.zd1_moskvina

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class BankFragment : Fragment() {
    private lateinit var btn: Button
    private lateinit var login: EditText
    private lateinit var password: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bank, container, false)

        btn = view.findViewById(R.id.enterBtn)
        login = view.findViewById(R.id.loginET)
        password = view.findViewById(R.id.passwET)

        btn.setOnClickListener {
            if (login.text.toString().trim().isNotEmpty()
                && password.text.toString().trim().isNotEmpty()
            ) {
                parentFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, CreditFragment())
                    .addToBackStack(null)
                    .commit()
            } else {
                Toast.makeText(requireContext(), "Введите логин и пароль", Toast.LENGTH_LONG).show()
            }
        }
        return view
    }
}