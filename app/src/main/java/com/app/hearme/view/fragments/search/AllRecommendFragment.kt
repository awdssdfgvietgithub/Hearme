package com.app.hearme.view.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.data.adapter.MusicAdapter
import com.app.hearme.data.adapter.UserAdapterV2
import com.app.hearme.data.util.UserManager
import com.app.hearme.databinding.FragmentAllRecommendBinding
import com.app.hearme.databinding.FragmentTrendingNowBinding
import com.app.hearme.model.Music
import com.app.hearme.network.models.SearchUserResponse
import com.app.hearme.network.models.toUser
import com.app.hearme.viewmodel.MusicViewModel
import com.app.hearme.viewmodel.UserViewModel

class AllRecommendFragment : Fragment() {
    private lateinit var binding: FragmentAllRecommendBinding
    private lateinit var mAdapter: UserAdapterV2
    private val mViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_all_recommend, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        showLoading(true)
        mViewModel.recommendUserData.observe((activity as MainActivity)) { list ->
            showLoading(false)
            displayRecyclerRecommendList(list.map { it.userPredict })
        }

        mViewModel.fetchRecommendUser(UserManager.getInstance().getUserId(), 20)

        (activity as MainActivity).customToolbar(
            "VISIBLE",
            "All Recommend",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = false,
            showIcFilter = false,
            showIcSearch = false
        )

        (activity as MainActivity).binding.toolBar.setNavigationOnClickListener() {
            findNavController().popBackStack()
        }
    }

    private fun displayRecyclerRecommendList(listRecommendUser: List<SearchUserResponse.UserModel>): Boolean {
        if (listRecommendUser.isEmpty()) {
            return false
        }
        binding.recyclerView.visibility = View.VISIBLE
        mAdapter = UserAdapterV2(listRecommendUser.map {
            it.toUser()
        }.toCollection(ArrayList()), 0, this)
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
            adapter = mAdapter
        }

        return true
    }

    private fun showLoading (isShow: Boolean = true) {
        if (isShow) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}