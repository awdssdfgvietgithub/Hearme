package com.app.hearme.view.tab_viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.data.adapter.MusicAdapter
import com.app.hearme.databinding.FragmentTabQueueBinding
import com.app.hearme.model.Music
import com.app.hearme.viewmodel.UserViewModel

class TabQueueFragment : Fragment() {
    private lateinit var binding: FragmentTabQueueBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var musicAdapter: MusicAdapter
    private var lst: ArrayList<Music> = ArrayList()
    private var email: String? = ""
    private var spinnerItems = arrayOf("Recently Added", "Added Before")

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tab_queue, container, false)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = (activity as MainActivity)
        email = mainActivity.email
        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.style_spinner, spinnerItems)
        binding.spinner.adapter = spinnerAdapter

        userViewModel.lstDataUser.observe((activity as MainActivity), Observer { arrayList ->
            lst = arrayList.first { user -> user.email == email }.listMusicsQueue

            val renderArrayList = arrayListOf<Music>()
            lst.reversed().forEach { music ->
                renderArrayList.add(music)
            }
            displayRecyclerView(renderArrayList)

            binding.spinner.setSelection(0)
            binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    val lstP1 = lst
                    val lstP0 = ArrayList<Music>()

                    for (i in lst.indices) {
                        lstP0.add(i, lst[lst.lastIndex - i])
                    }
                    if (p2 == 0)
                        displayRecyclerView(lstP0)
                    else
                        displayRecyclerView(lstP1)
                }
                override fun onNothingSelected(p0: AdapterView<*>?) {

                }
            }
        })
    }

    private fun displayRecyclerView(lstData: ArrayList<Music>) {
        val layoutRecyclerViewMusic =
            LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        musicAdapter = MusicAdapter(lstData, 2,this)
        binding.recyclerView.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = musicAdapter
        }
    }
}