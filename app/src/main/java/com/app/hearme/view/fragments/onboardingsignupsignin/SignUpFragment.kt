package com.app.hearme.view.fragments.onboardingsignupsignin

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.data.UsersData
import com.app.hearme.databinding.FragmentSignUpBinding
import com.app.hearme.viewmodel.EmailViewModel
import com.app.hearme.viewmodel.UserViewModel

class SignUpFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var mainActivity: MainActivity

    private val viewModelUser: UserViewModel by activityViewModels()
    private val emailViewModel: EmailViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sign_up, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = activity as MainActivity

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

        binding.tvSignIn.setOnClickListener() {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }

        binding.btnSignUp.setOnClickListener() {
            val email = binding.edtEmail.text.toString().trim()
            val pass = binding.edtPass.text.toString().trim()
            val sizeUserDataOld = UsersData.data().size
            var checkEmail = false
            var checkPass = false

            // kiem tra dinh dang email va do dai pass
            val pattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9-]+\\.[a-zA-Z.]{2,18}".toRegex()
            repeat(email.length) {
                if (pattern.matches(email))
                    checkEmail = true
                else binding.txtLayoutEmail.error = "Please enter the correct email format"
            }

            // check length username email: 6 <= username <= 30

            if (email.length > 6) {
                if (email.substring(0, 6).find { it == '@' } != null) {
                    checkEmail = false
                    binding.txtLayoutEmail.error = "Please enter the correct email format"
                } else checkEmail = true
            }

            if (email.length > 30) {
                if (email.substring(0, 30).find { it == '@' } == null)
                    checkEmail = true
                else {
                    checkEmail = false
                    binding.txtLayoutEmail.error = "Please enter the correct email format"
                }
            }


            if (pass.length >= 6)
                checkPass = true
            else {
                binding.txtLayoutPass.error = "Password length must be >= 6"
                checkPass = false
            }

            // kiem tra trung lap email
            if (viewModelUser.checkDuplicateEmails(email)) {
                binding.edtEmail.text = null
                binding.edtEmail.requestFocus()
                checkEmail = false
                binding.txtLayoutEmail.error = "Email Already Exists !!"
            }

            if (checkEmail && checkPass) {
                viewModelUser.lstDataUser.observe(viewLifecycleOwner) {
                    if (it.size > sizeUserDataOld) {
                        findNavController().navigate(
                            R.id.fillYourProfileFragment  //action_signUpFragment_to_fillYourProfileFragment
                        )
                        emailViewModel.selectItem(binding.edtEmail.text.toString())
                    }
                }
                viewModelUser.addDataUser(email, pass)
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
}