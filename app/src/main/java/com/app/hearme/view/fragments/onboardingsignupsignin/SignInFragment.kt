package com.app.hearme.view.fragments.onboardingsignupsignin

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.databinding.FragmentSignInBinding
import com.app.hearme.model.User
import com.app.hearme.viewmodel.EmailViewModel
import com.app.hearme.viewmodel.UserViewModel


class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private lateinit var mainActivity: MainActivity
    private val binding get() = _binding!!
    private lateinit var lstDataUser: List<User>
    private lateinit var sharedPreferences: SharedPreferences
    var email = ""
    private var pass = ""
    private var isChecked = true

    private val viewModelUser: UserViewModel by activityViewModels()

    private val emailViewModel: EmailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_in, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = activity as MainActivity

        viewModelUser.isLogonSuccess.observe(viewLifecycleOwner) { isSuccess ->
            showLoadingBar(false)
            if (isSuccess) {
                saveDataLogin()
                findNavController().navigate(R.id.action_signInFragment_to_item_nav_home)
            }
        }

        viewModelUser.lstDataUser.observe(viewLifecycleOwner) {
            Log.v(TAG, "SignIn -> ${it.size}")
        }

        viewModelUser.lstDataUser.observe(viewLifecycleOwner, Observer {
            lstDataUser = it
            Log.v(TAG, "${it.size}")
            if (lstDataUser.isNullOrEmpty())
                Toast.makeText(context, "list is null or empty", Toast.LENGTH_SHORT).show()
        })

        binding.edtEmail.setOnFocusChangeListener { _, hasFocus ->
            val color = if (hasFocus) resources.getColor(R.color.bg_button) else Color.BLACK
            binding.txtLayoutEmail.setStartIconTintList(ColorStateList.valueOf(color))
            binding.txtLayoutEmail.error = ""
        }

        binding.edtPass.setOnFocusChangeListener { _, hasFocus ->
            val color = if (hasFocus) resources.getColor(R.color.bg_button) else Color.BLACK
            binding.txtLayoutPass.setStartIconTintList(ColorStateList.valueOf(color))
            binding.txtLayoutPass.setEndIconTintList(ColorStateList.valueOf(color))

            if (binding.edtPass.text?.length!! >= 6)
                binding.txtLayoutPass.error = ""
        }

        binding.tvSignUp.setOnClickListener() {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }

        binding.btnSignIn.setOnClickListener() {
            email = binding.edtEmail.text.toString().trim()
            pass = binding.edtPass.text.toString().trim()
            var checkEmail = false
            var checkPass = false

            // kiem tra dinh dang email va do dai pass
//            val pattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,18}".toRegex()
//            repeat(email.length) {
//                if (pattern.matches(email))
                    checkEmail = true
//                else binding.txtLayoutEmail.error = "Please enter the correct email format"
//            }

            if (pass.length >= 6)
                checkPass = true
            else binding.txtLayoutPass.error = "Password length must be >= 6"

            if (checkEmail && checkPass) {
                showLoadingBar(true)
                viewModelUser.executeLogin(username = email, password = pass)
//                val kq =
//                    lstDataUser.filter { it.email == binding.edtEmail.text.toString() && it.password == binding.edtPass.text.toString() }
//                if (kq.isNotEmpty()) {
//                    saveDataLogin()
//                    if (kq.first().isFirstSignIn)
//                        findNavController().navigate(R.id.fillYourProfileFragment)
//                    else
//                        findNavController().navigate(R.id.action_signInFragment_to_item_nav_home)
//                    emailViewModel.selectItem(email)
//                } else {
//                    Toast.makeText(
//                        context,
//                        "Please check Email or Password !!!",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    binding.edtEmail.requestFocus()
//                }
            }
        }

        getDataLogin()

        binding.tvForgot.setOnClickListener() {
            email = binding.edtEmail.text.toString()
            if (email.isEmpty()) {
                mainActivity.showSnack(requireView(), "Must to input email for finding password!")
            } else if (lstDataUser.any { it.email == email }) {
                findNavController().navigate(R.id.action_signInFragment_to_selectMethodFragment)
                emailViewModel.selectItem(binding.edtEmail.text.toString())
            } else {
                mainActivity.showSnack(requireView(), "The email is not exists!")
            }
        }
        mainActivity.showBottomNav("gone")
        mainActivity.customToolbar(
            "visible",
            null,
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = false,
            showIcFilter = false,
            showIcSearch = false,
            showIcNotification = false
        )
        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun getDataLogin() {
        if (mainActivity.checkRemember) {
            sharedPreferences =
                requireActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE)
            email = sharedPreferences.getString("username", "")!!
            pass = sharedPreferences.getString("pass", "")!!
            isChecked = sharedPreferences.getBoolean("check", false)

            binding.edtEmail.setText(email)
            binding.edtPass.setText(pass)
            binding.chkRemember.isChecked = isChecked
        } else {
            email = ""
            pass = ""
            isChecked = false
            binding.chkRemember.isEnabled = mainActivity.checkRemember
        }
    }

    private fun saveDataLogin() {
        if (binding.chkRemember.isChecked) {
            sharedPreferences =
                requireActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE)
            isChecked = binding.chkRemember.isChecked

            // luu data
            val editor = sharedPreferences.edit()
            editor.putString("username", email)
            editor.putString("pass", pass)
            editor.putBoolean("check", isChecked)
            editor.apply()
            Toast.makeText(context, "Data Login Saved", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showLoadingBar(isShow: Boolean = true) {
        binding.progressBar.visibility = if (isShow) View.VISIBLE else View.GONE
        binding.btnSignIn.isEnabled = !isShow
        binding.btnSignIn.isEnabled = !isShow
    }
}