package com.app.hearme.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.databinding.FragmentTopItemBinding

class TopItemFragment : Fragment() {
    private var _binding: FragmentTopItemBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_item, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).customToolbar(
            "VISIBLE",
            "",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back)
        )
        (activity as MainActivity).binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }

        val bundle = requireArguments()
        val idCard = bundle.getInt("idCard")
        binding.tvInCard.text = bundle.getString("tvInCard")
        binding.tvTitle.text = bundle.getString("tvTitle")
        binding.tvDetail.text = bundle.getString("tvDetail")

        when (idCard) {
            0 -> binding.imgCardView.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.img_bg_01)
            1 -> binding.imgCardView.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.img_bg_02)
            2 -> binding.imgCardView.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.img_bg_03)
            3 -> binding.imgCardView.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.img_bg_04)
            4 -> binding.imgCardView.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.img_bg_05)
            5 -> binding.imgCardView.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.img_bg_06)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}