package com.app.hearme.view.fragments.profile_settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.data.adapter.CardPaymentAdapter
import com.app.hearme.databinding.FragmentPaymentBinding
import com.app.hearme.model.CardPayment


class PaymentFragment : Fragment() {
    private var _binding: FragmentPaymentBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity

    private var arrayList = ArrayList<CardPayment>()
    private var img = -1
    private var name = ""
    private var intLogo = -1
    private var strNameCard = ""
    private var strCoin = ""
    private var strTime = ""
    private var intBackground = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_payment, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = (activity as MainActivity)

        mainActivity.customToolbar(
            "VISIBLE",
            "Payment",
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
            img = bundle.getInt("img", R.drawable.logo_default)
            name = bundle.getString("name", "name_error")

            if (name != "name_error")
                arrayList.add(CardPayment(img, name, true))

            strCoin = bundle.getString("coin", "coin_error")
            strTime = bundle.getString("time", "time_error")
            intBackground = bundle.getInt("background", R.color.transparent)
        }

        val adapter = CardPaymentAdapter(this, initData())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.itemAnimator = DefaultItemAnimator()
        binding.recyclerView.addItemDecoration(DividerItemDecoration(requireContext(),
            LinearLayoutManager.VERTICAL))
        binding.recyclerView.adapter = adapter

        binding.btnAddNewCard.setOnClickListener {
            val bundleNewCard = Bundle()
            bundleNewCard.putString("coin", strCoin)
            bundleNewCard.putString("time", strTime)
            bundleNewCard.putInt("background", intBackground)
            findNavController().navigate(R.id.action_paymentFragment_to_addNewCardFragment,
                bundleNewCard)
        }

        binding.btnContinue.setOnClickListener {
            val nameItemChecked = arrayList.first { it.isSelected }
            when (nameItemChecked.name) {
                "Paypal" -> {
                    intLogo = R.drawable.paypal
                    strNameCard = "Paypal"
                }

                "Google Pay" -> {
                    intLogo = R.drawable.google
                    strNameCard = "Google Pay"
                }

                "Apple Pay" -> {
                    intLogo = R.drawable.apple
                    strNameCard = "Apple Pay"
                }

                else -> {
                    intLogo = R.drawable.mastercard
                    strNameCard = name
                }
            }

            val bundleReview = Bundle()
            bundleReview.putString("coin", strCoin)
            bundleReview.putString("time", strTime)
            bundleReview.putInt("background", intBackground)
            bundleReview.putString("nameCard", strNameCard)
            bundleReview.putInt("imgCard", intLogo)
            this.arguments = bundleReview

            findNavController().navigate(R.id.action_paymentFragment_to_reviewSummaryFragment,
                bundleReview)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        arrayList.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initData(): ArrayList<CardPayment> {
        arrayList.add(
            CardPayment(R.drawable.paypal, "Paypal", true)
        )
        arrayList.add(
            CardPayment(R.drawable.google, "Google Pay", false)
        )
        arrayList.add(
            CardPayment(R.drawable.apple, "Apple Pay", false)
        )
        return arrayList
    }
}