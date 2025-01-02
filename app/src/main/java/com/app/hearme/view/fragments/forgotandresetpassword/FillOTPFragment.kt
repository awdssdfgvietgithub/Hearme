package com.app.hearme.view.fragments.forgotandresetpassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.databinding.FragmentFillOTPBinding

class FillOTPFragment : Fragment() {
    private lateinit var binding: FragmentFillOTPBinding
    private lateinit var mainActivity: MainActivity
    private var email: String? = null
    private var method: String? = null

    private var numbersList = ArrayList<String>()
    private var passCode = ""
    private lateinit var num1: String
    private lateinit var num2: String
    private lateinit var num3: String
    private lateinit var num4: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_fill_o_t_p,
                container,
                false
            )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        mainActivity = activity as MainActivity
        email = "phuongviet.huit@gmail.com"
        method = arguments?.getString("method")

        binding.textSendCode.text = "Code has been send to $method"

        binding.edtNum4.setOnClickListener() {
            binding.containerNumPad.visibility = View.VISIBLE
        }

        binding.edtNum1.isEnabled = false
        binding.edtNum2.isEnabled = false
        binding.edtNum3.isEnabled = false
        binding.edtNum4.isEnabled = false

        binding.btnNumber0.setOnClickListener() {
            numbersList.add("0")
            passNumber(numbersList)
        }
        binding.btnNumber1.setOnClickListener() {
            numbersList.add("1")
            passNumber(numbersList)
        }
        binding.btnNumber2.setOnClickListener() {
            numbersList.add("2")
            passNumber(numbersList)
        }
        binding.btnNumber3.setOnClickListener() {
            numbersList.add("3")
            passNumber(numbersList)
        }
        binding.btnNumber4.setOnClickListener() {
            numbersList.add("4")
            passNumber(numbersList)
        }
        binding.btnNumber5.setOnClickListener() {
            numbersList.add("5")
            passNumber(numbersList)
        }
        binding.btnNumber6.setOnClickListener() {
            numbersList.add("6")
            passNumber(numbersList)
        }
        binding.btnNumber7.setOnClickListener() {
            numbersList.add("7")
            passNumber(numbersList)
        }
        binding.btnNumber8.setOnClickListener() {
            numbersList.add("8")
            passNumber(numbersList)
        }
        binding.btnNumber9.setOnClickListener() {
            numbersList.add("9")
            passNumber(numbersList)
        }
        binding.btnNumberClear.setOnClickListener() {
            if (binding.edtNum4.text.isNotEmpty()) {
                numbersList.removeAt(3)
                binding.edtNum4.text.clear()
                binding.edtNum4.isPressed = false
            } else if (binding.edtNum3.text.isNotEmpty()) {
                numbersList.removeAt(2)
                binding.edtNum3.text.clear()
                binding.edtNum3.isPressed = false
            } else if (binding.edtNum2.text.isNotEmpty()) {
                numbersList.removeAt(1)
                binding.edtNum2.text.clear()
                binding.edtNum2.isPressed = false
            } else if (binding.edtNum1.text.isNotEmpty()) {
                numbersList.removeAt(0)
                binding.edtNum1.text.clear()
                binding.edtNum1.isPressed = false
            }
            passNumber(numbersList)
        }

        binding.btnVerify.setOnClickListener() {
            if (passCode.length < 4) {
                Toast.makeText(requireContext(), "Invalid code!", Toast.LENGTH_SHORT).show()
            } else if (passCode == "1111") {
                findNavController().navigate(R.id.action_fillOTPFragment_to_createNewPasswordFragment)
            } else {
                editState(binding.btnVerify, "up")
                view?.let { it1 -> mainActivity.showSnack(it1, "Incorrect code, try again!") }
            }
        }

        mainActivity.customToolbar(
            "VISIBLE", "Forgot Password", null, R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        )
        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }
    }

    private fun passNumber(numbersList: ArrayList<String>) {
        if (numbersList.size != 0) {
            when (numbersList.size) {
                1 -> {
                    binding.edtNum1.isPressed = true
                    num1 = numbersList[0]
                    binding.edtNum1.setText(num1)
                }
                2 -> {
                    binding.edtNum1.isPressed = false
                    binding.edtNum2.isPressed = true
                    num2 = numbersList[1]
                    binding.edtNum2.setText(num2)
                }
                3 -> {
                    binding.edtNum2.isPressed = false
                    binding.edtNum3.isPressed = true
                    num3 = numbersList[2]
                    binding.edtNum3.setText(num3)
                }
                4 -> {
                    binding.edtNum3.isPressed = false
                    binding.edtNum4.isPressed = true
                    num4 = numbersList[3]
                    binding.edtNum4.setText(num4)
                    passCode = num1 + num2 + num3 + num4
                    editState(binding.btnVerify, "down")
                    binding.edtNum4.isEnabled = true
                    binding.edtNum4.isClickable = false
                    binding.edtNum4.isFocusable = false
                }
            }
        }
    }

    private fun editState(
        appCompatButton: AppCompatButton, type: String //up or down
    ) {
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        if (type.lowercase() == "up") {
            appCompatButton.apply {
                background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.bg_button_continue_1)
                layoutParams = params.apply {
                    bottomMargin = 24
                    leftMargin = 24
                    rightMargin = 24
                }
            }
            binding.containerNumPad.visibility = View.VISIBLE
        } else if (type.lowercase() == "down") {
            appCompatButton.apply {
                background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.bg_button_continue_2)
                layoutParams = params.apply {
                    bottomMargin = 48
                    leftMargin = 24
                    rightMargin = 24
                }
            }
            binding.containerNumPad.visibility = View.GONE
        }
    }
}