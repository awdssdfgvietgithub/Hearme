package com.app.hearme.view.fragments.profile_settings

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.databinding.FragmentAddNewCardBinding
import java.util.Calendar


class AddNewCardFragment : Fragment() {
    private var _binding: FragmentAddNewCardBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    var strLength = 1
    private var strCoin = ""
    private var strTime = ""
    private var intBackground = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_add_new_card, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = (activity as MainActivity)

        mainActivity.customToolbar(
            "VISIBLE",
            "Add New Card",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = false,
            showIcFilter = false,
            showIcSearch = false,
            showIcNotification = false,
            showIcEdit = false,
            showIcScan = true
        )

        mainActivity.showBottomNav("GONE")

        mainActivity.binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        val bundle = this.arguments
        if (bundle != null) {
            strCoin = bundle.getString("coin", "coin_error")
            strTime = bundle.getString("time", "time_error")
            intBackground = bundle.getInt("background", R.color.transparent)
        }

        binding.edtCardName.requestFocus()

        binding.edtCardName.addTextChangedListener {
            if (it.toString().length < 5)
                binding.edtCardName.error = "Please enter the card name !!"
            else binding.edtCardNumber.isEnabled = true
        }

        binding.edtCardNumber.addTextChangedListener(textWatcher)

        binding.edtDob.setOnClickListener {
            mainActivity.hideKeyBoard()
            calendarDialog(it)
            binding.edtCVV.isEnabled = true
            binding.edtCVV.requestFocus()
        }

        binding.edtCVV.addTextChangedListener {
            if (it.toString().length == 3) {
                mainActivity.hideKeyBoard()
                binding.btnAddNewCard.isEnabled = true
            }
        }

        binding.btnAddNewCard.setOnClickListener {
            val strName = "**** **** **** *"
            val name = binding.edtCardNumber.text.toString().substring(16)
            val bundlePayment = Bundle()
            bundlePayment.putInt("img", R.drawable.mastercard)
            bundlePayment.putString("name", strName.plus(name))
            bundlePayment.putString("coin", strCoin)
            bundlePayment.putString("time", strTime)
            bundlePayment.putInt("background", intBackground)
            this.arguments = bundlePayment

            findNavController().navigate(R.id.action_addNewCardFragment_to_paymentFragment,
                bundlePayment)
        }
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            val str = s.toString()
            val strNumber = str.filter { it.isDigit() }
            var strResult = str.filter { it != '*' }

            // Lấy chuỗi khi dài hơn 4 đã xuất hiện khoảng trắng
            strResult = if (strNumber.length > 12) {
                str.substring(0, strNumber.length + 3)
            } else if (strNumber.length > 8) {
                str.substring(0, strNumber.length + 2)
            } else if (strNumber.length > 4) {
                str.substring(0, strNumber.length + 1)
            } else strNumber

            var lengthSTRX = strResult.length

            if (strResult.length == 19) {
                mainActivity.hideKeyBoard()
                binding.edtDob.isEnabled = true
                binding.edtDob.requestFocus()
            }

            if (lengthSTRX == strLength) {

                // Tính độ dài chuỗi để thực hiện việc setText chỉ một lần
                if (strResult.length == 4 || strResult.length == 9 || strResult.length == 14)
                    strLength += 2
                else strLength++

                // Thêm các dấu sao còn thiếu
                if (lengthSTRX > 15)
                    lengthSTRX -= 3
                else if (lengthSTRX > 10)
                    lengthSTRX -= 2
                else if (lengthSTRX > 5)
                    lengthSTRX -= 1

                for (i in lengthSTRX until 16) {
                    if (strResult.length == 4 || strResult.length == 9 || strResult.length == 14)
                        strResult += " "
                    strResult += '*'
                }

                binding.edtCardNumber.setText(strResult)
                binding.edtCardNumber.setSelection(strLength - 1)
            }
        }
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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}