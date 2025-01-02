package com.app.hearme.view.fragments.profile_settings

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.databinding.FragmentSecurityBinding
import com.app.hearme.viewmodel.UserViewModel

class SecurityFragment : Fragment() {
    private var _binding: FragmentSecurityBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    private var email: String? = ""

    private val userViewModel: UserViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_security, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = (activity as MainActivity)
        email = "phuongviet.huit@gmail.com"

        mainActivity.customToolbar(
            "VISIBLE",
            "Security",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        )

        mainActivity.showBottomNav("GONE")

        mainActivity.binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        binding.btnChangePIN.setOnClickListener {
            showDialogChangePIN()
        }

        binding.btnChangePass.setOnClickListener {
            showDialogChangePassword()
        }

        binding.switchRememberMe.setOnClickListener {
            mainActivity.checkRemember = binding.switchRememberMe.isChecked
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun showDialogChangePIN() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_pin)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val edtOldNum1: EditText = dialog.findViewById(R.id.edtOldNum_1)
        val edtOldNum2: EditText = dialog.findViewById(R.id.edtOldNum_2)
        val edtOldNum3: EditText = dialog.findViewById(R.id.edtOldNum_3)
        val edtOldNum4: EditText = dialog.findViewById(R.id.edtOldNum_4)

        val edtNewNum1: EditText = dialog.findViewById(R.id.edtNewNum_1)
        val edtNewNum2: EditText = dialog.findViewById(R.id.edtNewNum_2)
        val edtNewNum3: EditText = dialog.findViewById(R.id.edtNewNum_3)
        val edtNewNum4: EditText = dialog.findViewById(R.id.edtNewNum_4)

        val btnYes: Button = dialog.findViewById(R.id.btnYes)
        val btnNo: Button = dialog.findViewById(R.id.btnNo)

        edtOldNum1.requestFocus()

        edtOldNum1.setOnClickListener {
            if (edtOldNum1.text.isNotEmpty()) {
                edtOldNum2.isEnabled = true
                edtOldNum2.requestFocus()
            }
        }

        edtOldNum2.setOnClickListener {
            if (edtOldNum2.text.isNotEmpty()) {
                edtOldNum3.isEnabled = true
                edtOldNum3.requestFocus()
            }
        }

        edtOldNum3.setOnClickListener {
            if (edtOldNum3.text.isNotEmpty()) {
                edtOldNum4.isEnabled = true
                edtOldNum4.requestFocus()
            }
        }

        edtOldNum4.setOnClickListener {
            if (edtOldNum4.text.isNotEmpty()) {
                edtNewNum1.isEnabled = true
                edtNewNum1.requestFocus()
            }
        }

        edtNewNum1.setOnClickListener {
            if (edtNewNum1.text.isNotEmpty()) {
                edtNewNum2.isEnabled = true
                edtNewNum2.requestFocus()
            }
        }

        edtNewNum2.setOnClickListener {
            if (edtNewNum2.text.isNotEmpty()) {
                edtNewNum3.isEnabled = true
                edtNewNum3.requestFocus()
            }
        }

        edtNewNum3.setOnClickListener {
            if (edtNewNum3.text.isNotEmpty()) {
                edtNewNum4.isEnabled = true
                edtNewNum4.requestFocus()
            }
        }

        edtNewNum4.setOnClickListener {
            if (edtNewNum4.text.isNotEmpty())
                btnYes.isEnabled = true
        }

        btnYes.setOnClickListener {
            val strOldPin = edtOldNum1.text.toString().plus(edtOldNum2.text.toString())
                .plus(edtOldNum3.text.toString()).plus(edtOldNum4.text.toString())
            val strNewPin = edtNewNum1.text.toString().plus(edtNewNum2.text.toString())
                .plus(edtNewNum3.text.toString()).plus(edtNewNum4.text.toString())
            val strPin = userViewModel.lstDataUser.value?.first { it.email == email }?.pin
            if (strOldPin != strPin.toString()) {
                Toast.makeText(requireContext(), "Wrong pin code !!", Toast.LENGTH_SHORT).show()
                edtOldNum1.setText("")
                edtOldNum2.setText("")
                edtOldNum3.setText("")
                edtOldNum4.setText("")
                edtNewNum1.setText("")
                edtNewNum2.setText("")
                edtNewNum3.setText("")
                edtNewNum4.setText("")
                edtOldNum1.requestFocus()
            } else {
                Toast.makeText(requireContext(),
                    "Changed pin code successfully !!",
                    Toast.LENGTH_SHORT).show()
                userViewModel.updateUserPin(email.toString(), strNewPin.toInt())
                dialog.dismiss()
            }
        }

        btnNo.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun showDialogChangePassword() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_password)
        dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val btnYes: Button = dialog.findViewById(R.id.btnYes)
        val btnNo: Button = dialog.findViewById(R.id.btnNo)

        val txtLayoutOldPass: TextInputLayout = dialog.findViewById(R.id.txtLayoutOldPass)
        val txtLayoutNewPass: TextInputLayout = dialog.findViewById(R.id.txtLayoutNewPass)
        val txtLayoutConfirmPass: TextInputLayout = dialog.findViewById(R.id.txtLayoutConfirmPass)

        val pass = userViewModel.lstDataUser.value?.first { it.email == email }?.password

        val oldPass: EditText = dialog.findViewById(R.id.edtOldPass)
        val newPass: EditText = dialog.findViewById(R.id.edtNewPass)
        val confirmPass: EditText = dialog.findViewById(R.id.edtConfirmPass)

        var checkLengthNewPass = false
        var checkLengthConfirmPass = false

        btnYes.setOnClickListener {

            txtLayoutOldPass.error = ""
            txtLayoutNewPass.error = ""
            txtLayoutConfirmPass.error = ""

            if (newPass.length() == 0)
                txtLayoutNewPass.error = "Please enter a new password !!"
            else if (newPass.length() < 6)
                txtLayoutNewPass.error = "New Password length must be longer than 6 !!"
            else checkLengthNewPass = true
            if (confirmPass.length() == 0)
                txtLayoutConfirmPass.error = "Please enter a confirm password !!"
            else if (confirmPass.length() < 6)
                txtLayoutConfirmPass.error = "Confirm Password length must be longer than 6 !!"
            else checkLengthConfirmPass = true

            Toast.makeText(requireContext(), "check new: $checkLengthNewPass", Toast.LENGTH_SHORT)
                .show()
            Toast.makeText(requireContext(),
                "check con: $checkLengthConfirmPass",
                Toast.LENGTH_SHORT).show()

            if (pass != oldPass.text.toString())
                txtLayoutOldPass.error = "Enter wrong password !!"
            else {
                if (newPass.text.toString() == confirmPass.text.toString() && checkLengthConfirmPass && checkLengthNewPass) {
                    userViewModel.updatePassword(email.toString(), newPass.text.toString())
                    Toast.makeText(requireContext(),
                        "Password changed successfully !",
                        Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                } else txtLayoutConfirmPass.error = "Does not match the new password !!"
            }
        }

        btnNo.setOnClickListener {
            dialog.dismiss()
        }
    }
}