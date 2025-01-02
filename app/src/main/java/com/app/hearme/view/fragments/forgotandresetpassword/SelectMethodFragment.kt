package com.app.hearme.view.fragments.forgotandresetpassword

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
import com.app.hearme.databinding.FragmentSelectMethodBinding
import com.app.hearme.viewmodel.UserViewModel

class SelectMethodFragment : Fragment() {
    private lateinit var binding: FragmentSelectMethodBinding
    private lateinit var mainActivity: MainActivity

    private val viewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_select_method,
                container,
                false
            )
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = activity as MainActivity

        viewModel.lstDataUser.value?.first { it.email == mainActivity.email }.apply {
            binding.textPhone.text = "+${mainActivity.protectedPhone(this?.phone.toString())}"
            binding.textEmail.text = mainActivity.protectedEmail(this?.email.toString())
        }

        binding.btnSMS.setOnCheckedChangeListener() { button, isChecked ->
            if (isChecked) {
                button.isChecked = true
                binding.btnEmail.isChecked = false
            } else {
                button.isChecked = false
            }
        }

        binding.btnEmail.setOnCheckedChangeListener() { button, isChecked ->
            if (isChecked) {
                button.isChecked = true
                binding.btnSMS.isChecked = false
            } else {
                button.isChecked = false
            }
        }

        binding.btnContinue.setOnClickListener() {
            val method = if (binding.btnSMS.isChecked) {
                binding.textPhone.text.toString()
            } else if (binding.btnEmail.isChecked) {
                binding.textEmail.text.toString()
            } else {
                view?.let { it1 -> mainActivity.showSnack(it1, "Please, let's choose one method!") }
                return@setOnClickListener
            }
            findNavController().navigate(
                R.id.action_selectMethodFragment_to_fillOTPFragment,
                Bundle().apply {
                    putString("method", method)
                })
        }

        mainActivity.customToolbar(
            "VISIBLE", "Forgot Password", null, R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        )
        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }
    }
}