package com.app.hearme.view.fragments.profile_settings

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.databinding.FragmentEditProfileBinding
import com.app.hearme.viewmodel.UserViewModel
import java.text.SimpleDateFormat
import java.util.Calendar

class EditProfileFragment : Fragment() {
    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    private var email: String? = ""
    private var fullName: String? = ""
    private var spinnerItems = arrayOf("Male", "Female", "Other")

    private val userViewModel: UserViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_edit_profile, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = activity as MainActivity
        email = "phuongviet.huit@gmail.com"

        mainActivity.customToolbar(
            "VISIBLE",
            "Edit Profile",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        )

        (activity as MainActivity).showBottomNav("GONE")

        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }

        val formatter = SimpleDateFormat("dd/MM/yyyy")
        fullName = userViewModel.lstDataUser.value?.first { it.email == email }?.fullName
        val nickName = userViewModel.lstDataUser.value?.first { it.email == email }?.nickName
        val nation = userViewModel.lstDataUser.value?.first { it.email == email }?.nation
        val dob = userViewModel.lstDataUser.value?.first { it.email == email }?.dob
        val phone = userViewModel.lstDataUser.value?.first { it.email == email }?.phone
        var gender = userViewModel.lstDataUser.value?.first { it.email == email }?.gender

        binding.edtFullName.setText(fullName, TextView.BufferType.EDITABLE)
        binding.edtNickName.setText(nickName, TextView.BufferType.EDITABLE)
        binding.edtDob.setText(dob?.let { formatter.format(it) }, TextView.BufferType.EDITABLE)
        binding.edtEmail.setText(email, TextView.BufferType.EDITABLE)
        binding.edtPhoneNumber.setText(phone, TextView.BufferType.EDITABLE)
        binding.ccp.setDefaultCountryUsingNameCode(nation)
        binding.ccp.resetToDefaultCountry()

        binding.edtDob.setOnClickListener { calendarDialog(it) }

        val spinnerAdapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_dropdown_item,
            spinnerItems
        )
        binding.spinner.adapter = spinnerAdapter

        var position = 1
        if (gender == true)
            position = 0
        binding.spinner.setSelection(position)

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                gender = true
                if (p2 > 0)
                    gender = false
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                gender = position <= 0
            }
        }

        binding.btnUpdate.setOnClickListener {
            formatter.parse(binding.edtDob.text.toString())?.let { it1 ->
                if (gender != null) {
                    userViewModel.updateDataUser(
                        email.toString(),
                        binding.edtFullName.text.toString(),
                        binding.edtNickName.text.toString(),
                        it1,
                        binding.edtPhoneNumber.text.toString(),
                        gender!!
                    )
                }
            }
            Toast.makeText(context, "Update Success !!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_editProfileFragment_to_profileDetailFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun calendarDialog(it: View?) {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH) + 1
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dialog = DatePickerDialog(
            requireContext(),
            { _, mYear, mMonth, mDay ->
                binding.edtDob.setText("$mDay/$mMonth/$mYear")
            }, year, month, day
        )
        dialog.show()
    }
}