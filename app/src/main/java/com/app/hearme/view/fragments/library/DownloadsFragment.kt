package com.app.hearme.view.fragments.library

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.data.adapter.MusicAdapter
import com.app.hearme.databinding.FragmentDownloadsBinding
import com.app.hearme.model.Music

class DownloadsFragment : Fragment() {
    private lateinit var binding: FragmentDownloadsBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var musicAdapter: MusicAdapter
    private var lst: ArrayList<Music>? = null
    private var email: String? = ""
    private var spinnerItems = arrayOf("Recently Downloaded", "Downloaded Before")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_downloads, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = (activity as MainActivity)
        email = "phuongviet.huit@gmail.com"

        mainActivity.customToolbar(
            "VISIBLE",
            "Downloads",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back),
            showIcMore = true,
            showIcFilter = false,
            showIcSearch = true
        )
        mainActivity.showBottomNav("GONE")
        mainActivity.binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        mainActivity.viewModelUser.lstDataUser.observe(viewLifecycleOwner) {
            lst = it.first { user -> user.email == email }.listMusicsDownloaded
        }


        val lstP1 = lst as ArrayList<Music>
        val lstP0 = ArrayList<Music>()
        if (lst != null) {
            for (i in lst!!.indices) {
                lstP0.add(i, lst!![lst!!.lastIndex - i])
            }
        }

        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.style_spinner, spinnerItems)
        binding.spinner.adapter = spinnerAdapter

        if (lst != null) {
            binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    if (p2 == 0)
                        displayRecyclerView(lstP0)
                    else
                        displayRecyclerView(lstP1)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }

            // Shuffle list
            val lstShuffle = ArrayList(lst)
            lstShuffle.shuffle()
            binding.music =  lstShuffle[0]

        } else {
            binding.btnPlay.isEnabled = false
            binding.btnShuffle.isEnabled = false
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(TAG, "onDestroy ")
    }

    private fun displayRecyclerView(lstData: ArrayList<Music>) {
        val layoutRecyclerViewMusic =
            LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        musicAdapter = MusicAdapter(lstData, 6, this)
        binding.recyclerView.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = musicAdapter
        }
    }
}