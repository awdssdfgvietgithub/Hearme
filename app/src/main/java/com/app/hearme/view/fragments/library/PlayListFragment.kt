package com.app.hearme.view.fragments.library

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.data.adapter.PlaylistAdapter
import com.app.hearme.databinding.FragmentPlayListBinding
import com.app.hearme.model.Playlist
import com.app.hearme.viewmodel.UserViewModel

class PlayListFragment : Fragment() {
    private var _binding: FragmentPlayListBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainActivity: MainActivity
    private lateinit var playlistAdapter: PlaylistAdapter
    private var lst = ArrayList<Playlist>()
    private var email: String = ""
    private var spinnerItems = arrayOf("Recently Added", "Added Before")
    private var checkVisible = true

    private val userViewModel: UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_play_list, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = (activity as MainActivity)
        email = "phuongviet.huit@gmail.com"

        mainActivity.customToolbar(
            "VISIBLE",
            "Playlists",
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

        userViewModel.lstDataPlaylist.observe(viewLifecycleOwner) { viewModelList ->
            lst.clear()
            lst.addAll(viewModelList)
            val renderArrayList = arrayListOf<Playlist>()
            lst.reversed().forEach { playlist ->
                renderArrayList.add(playlist)
            }
            if (checkVisible)
                displayRecyclerView(renderArrayList)
        }

        binding.spinner.setSelection(0)
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val lstP1 = lst
                val lstP0 = ArrayList<Playlist>()
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

        val spinnerAdapter = ArrayAdapter(requireContext(), R.layout.style_spinner, spinnerItems)
        binding.spinner.adapter = spinnerAdapter

        binding.btnAddNewPlayList.setOnClickListener {
            showDialogBottom()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun displayRecyclerView(lstData: ArrayList<Playlist>) {
        val layoutRecyclerViewMusic =
            LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        playlistAdapter = PlaylistAdapter(lstData, 0) {
            findNavController().navigate(R.id.action_playListFragment_to_playlistDetailFragment, it)
        }

        binding.recyclerView.apply {
            layoutManager = layoutRecyclerViewMusic
            adapter = playlistAdapter
            playlistAdapter.notifyDataSetChanged()
        }
    }

    private fun showDialogBottom() {
        val dialog = Dialog(requireContext())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_add_playlist)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations =
            com.google.android.material.R.style.MaterialAlertDialog_Material3_Animation
        dialog.window?.setGravity(Gravity.BOTTOM)
        dialog.show()

        val edtTitle: EditText = dialog.findViewById(R.id.edtNamePlaylist)
        val btnYes: Button = dialog.findViewById(R.id.btnYes)
        val btnNo: Button = dialog.findViewById(R.id.btnCancel)
        val spinner: Spinner = dialog.findViewById(R.id.spinnerDialog)

        val spinnerItemsDialog = arrayOf("Public", "Private")
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, spinnerItemsDialog)
        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                checkVisible = p2 != 1
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        btnYes.setOnClickListener {
            userViewModel.addPlaylist(
                Playlist(
                    "pl000",
                    edtTitle.text.toString(),
                    R.drawable.ic_playlist_added,
                )
            )

            dialog.dismiss()
        }

        btnNo.setOnClickListener {
            dialog.dismiss()
        }

    }
}