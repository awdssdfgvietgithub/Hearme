package com.app.hearme.view.fragments.forgotandresetpassword

import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.databinding.FragmentCreateNewPasswordBinding
import com.app.hearme.view.dialogs.AuthorizationProgressDialog
import com.app.hearme.viewmodel.UserViewModel


class CreateNewPasswordFragment : Fragment() {
    private lateinit var binding: FragmentCreateNewPasswordBinding
    private lateinit var mainActivity: MainActivity

    private val userViewModel: UserViewModel by activityViewModels()
    private var email: String? = null

    private var isShowPass = false
    private var isShowRePass = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_create_new_password,
            container,
            false
        )
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = activity as MainActivity
        email = mainActivity.email

        binding.edtPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val imageTintColor: ColorStateList
                if (s != null) {
                    if (s.isNotEmpty()) {
                        imageTintColor =
                            ColorStateList.valueOf(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.greyscale_900
                                )
                            )
                    } else {
                        imageTintColor =
                            ColorStateList.valueOf(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.greyscale_500
                                )
                            )
                    }
                    binding.imageLockPass.imageTintList =
                        imageTintColor
                    binding.imageVisiblePass.imageTintList =
                        imageTintColor
                }
            }
        })

        binding.edtRePassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val imageTintColor: ColorStateList
                if (s != null) {
                    if (s.isNotEmpty()) {
                        imageTintColor =
                            ColorStateList.valueOf(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.greyscale_900
                                )
                            )
                    } else {
                        imageTintColor =
                            ColorStateList.valueOf(
                                ContextCompat.getColor(
                                    requireContext(),
                                    R.color.greyscale_500
                                )
                            )
                    }
                    binding.imageLockRePass.imageTintList =
                        imageTintColor
                    binding.imageVisibleRePass.imageTintList =
                        imageTintColor
                }
            }
        })

        binding.imageVisiblePass.setOnClickListener() {
            val edt = binding.edtPassword
            if (!isShowPass) {
                edt.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.imageVisiblePass.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_visible
                    )
                )
                isShowPass = true
            } else if (isShowPass) {
                edt.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.imageVisiblePass.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_invisible
                    )
                )
                isShowPass = false
            }
            edt.setSelection(edt.length())
            edt.letterSpacing = 0f
        }

        binding.imageVisibleRePass.setOnClickListener() {
            val edt = binding.edtRePassword
            if (!isShowRePass) {
                edt.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.imageVisibleRePass.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_visible
                    )
                )
                isShowRePass = true
            } else if (isShowRePass) {
                edt.inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.imageVisibleRePass.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_invisible
                    )
                )
                isShowRePass = false
            }
            edt.setSelection(edt.length())
            edt.letterSpacing = 0f
        }

        binding.btnContinue.setOnClickListener() {
            val pass = binding.edtPassword.text.toString()
            val rePass = binding.edtRePassword.text.toString()
            if (pass.length < 6) {
                mainActivity.showSnack(it, "Password's length must be longer than 6")
            } else if (pass == rePass) {
                email?.let { it1 ->
                    userViewModel.updatePassword(
                        it1,
                        pass
                    )
                }
                AuthorizationProgressDialog().show(
                    childFragmentManager,
                    AuthorizationProgressDialog.TAG
                )
            } else {
                mainActivity.showSnack(it, "Password and Re-password aren't similar!")
            }
        }

        mainActivity.customToolbar(
            "VISIBLE", "Create New Password", null, R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        )
        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }
    }
}