package com.app.hearme.view.fragments.search

import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.util.Base64
import android.util.Log
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
import com.app.hearme.databinding.FragmentViewDetailsProfileBinding
import com.app.hearme.model.User
import com.app.hearme.network.models.toUser
import com.app.hearme.viewmodel.UserViewModel
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException

class ViewDetailsProfileFragment : Fragment() {
    private lateinit var binding: FragmentViewDetailsProfileBinding
    private lateinit var mainActivity: MainActivity
    private var userEmail: String? = null
    private var userId: Int? = null
    private var user: User? = null

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userEmail = arguments?.getString("userEmail")
        userId = arguments?.getInt("userId")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_view_details_profile,
            container,
            false
        )
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = activity as MainActivity

        userId?.let { userViewModel.fetchUserInformation(it) }
//        user = userViewModel.lstDataUser.value?.first { it.email == userEmail }

        userViewModel.detailProfile.observe(viewLifecycleOwner) { data ->
            binding.user = data.user.toUser()

            val svgData = Base64.decode(data.user.toUser().avatarUrl, Base64.DEFAULT)
            val svgInputStream = svgData.inputStream()

            try {
                val svg = SVG.getFromInputStream(svgInputStream)
                val drawable = PictureDrawable(svg.renderToPicture())
                binding.iamge.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                binding.iamge.setImageDrawable(drawable)
            } catch (e: SVGParseException) {
                Log.e("SVGDecode", "Error decoding SVG", e)
            }
        }

        binding.containerFollow.setOnClickListener() {
            findNavController().navigate(
                R.id.action_viewDetailsProfileFragment_to_followerDetailFragment,
                Bundle().apply {
                    putString("emailID", userEmail)
                })
        }

        mainActivity.showBottomNav("GONE")
        mainActivity.customToolbar(
            "VISIBLE",
            navIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = true
        )
        mainActivity.binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }

        binding.iamge.setOnClickListener() {
            findNavController().navigate(R.id.fullImageFragment, Bundle().apply {
                putString("url", user?.avatarUrl)
            })
        }
    }
}