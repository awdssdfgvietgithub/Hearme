package com.app.hearme.view.fragments.search

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.data.adapter.*
import com.app.hearme.databinding.ContainerSearchResultBinding
import com.app.hearme.databinding.FragmentExploreBinding
import com.app.hearme.model.*
import com.app.hearme.network.models.SearchUserResponse
import com.app.hearme.network.models.toUser
import com.app.hearme.viewmodel.*

class ExploreFragment : Fragment(), RecentSearchesAdapter.RecentSearchAdapterCallBack {
    private lateinit var binding: FragmentExploreBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var categoryAdapter: CategoryAdapter
    private val viewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    private val recentSearchViewModel: RecentSearchViewModel by activityViewModels()
    private val topicSearchViewModel: TopicSearchViewModel by activityViewModels()
    private val artistViewModel: ArtistViewModel by activityViewModels()
    private val musicViewModel: MusicViewModel by activityViewModels()
    private val userViewModel: UserViewModel by activityViewModels()

    private lateinit var recentSearchesAdapter: RecentSearchesAdapter
    private lateinit var musicAdapter: MusicAdapter
    private lateinit var artistAdapter: ArtistAdapter
    private lateinit var userAdapter: UserAdapterV2
    private lateinit var recommendAdapter: UserAdapterV2

    private lateinit var includeTopsSongsArtistsAlbumsPlaylistsProfiles: ContainerSearchResultBinding

    private var saveInstanceTextSearch: String = ""
    private var isScrollingUp = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            saveInstanceTextSearch = savedInstanceState.getString("textSearch").toString()
            Log.v("saveInstanceTextSearch", "$savedInstanceState")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_explore, container, false)
        includeTopsSongsArtistsAlbumsPlaylistsProfiles =
            binding.includeTopsSongsArtistsAlbumsPlaylistsProfiles
        return binding.root
    }

    private fun displayRecyclerViewTopicSearch(data: ArrayList<TopicSearch>) {
        includeTopsSongsArtistsAlbumsPlaylistsProfiles.recyclerViewTopicSearch.apply {
            layoutManager =
                LinearLayoutManager(view?.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = TopicSearchAdapter(data, 0)
        }
    }

    override fun onResume() {
        super.onResume()
        mainActivity = (activity as MainActivity)

        binding.includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.textViewSeeAllMayKnow.setOnClickListener {
            findNavController().navigate(R.id.action_item_nav_explore_to_allRecommendFragment)
        }

//        val a = includeTopsSongsArtistsAlbumsPlaylistsProfiles.recyclerViewTopicSearch
//        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.recyclerViewFoundList.addOnScrollListener(
//            object : RecyclerView.OnScrollListener() {
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    super.onScrolled(recyclerView, dx, dy)
//                    if (dy > 0) {
//                        if (isScrollingUp) {
////                            includeTopsSongsArtistsAlbumsPlaylistsProfiles.containsRecyclerViewTopicSearch.startAnimation(
////                                AnimationUtils.loadAnimation(context, R.anim.anim_upward))
//                            a.animate()
//                                .alpha(0.0f)
//                                .setListener(object : AnimatorListenerAdapter() {
//                                    override fun onAnimationEnd(animation: Animator) {
//                                        super.onAnimationEnd(animation);
//                                        a.visibility = View.GONE;
//                                    }
//                                })
//                            isScrollingUp = false
//                        }
//                    } else {
//                        if (!isScrollingUp) {
////                            includeTopsSongsArtistsAlbumsPlaylistsProfiles.containsRecyclerViewTopicSearch.startAnimation(
////                                AnimationUtils.loadAnimation(context, R.anim.anim_downward))
//                            a.animate()
//                                .alpha(1.0f)
//                                .setListener(object : AnimatorListenerAdapter() {
//                                    override fun onAnimationEnd(animation: Animator) {
//                                        super.onAnimationEnd(animation);
//                                        a.visibility = View.VISIBLE;
//                                    }
//                                })
//
//                            isScrollingUp = true
//                        }
//                    }
//                }
//            })

        if (saveInstanceTextSearch.isNotEmpty()) {
            binding.containerBrowseAll.visibility = View.GONE
            binding.containerRecentSearches.visibility = View.GONE
            includeTopsSongsArtistsAlbumsPlaylistsProfiles.root.visibility = View.VISIBLE
            mainActivity.showBottomNav("GONE")
            mainActivity.customToolbar("GONE")
            binding.searchView.setQuery(saveInstanceTextSearch, true)
        }

        recentSearchViewModel.lstDataRecentSearch.observe(mainActivity) {
            displayRecyclerViewRecentSearches(it)
        }

        viewModel.lstDataCategory.observe((activity as MainActivity), Observer {
            displayRecyclerView(it as ArrayList<Category>)
        })
        viewModel.getListDataArtist()

        initSearchBar()

        binding.textClearAll.setOnClickListener() {
            recentSearchViewModel.deleteAll()
        }

        mainActivity.customToolbar(
            "VISIBLE",
            "Explore",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.logo_nav),
            true
        )
        mainActivity.showBottomNav("VISIBLE")

        if (binding.searchView.query.isNotEmpty()) {
            binding.searchView.setQuery("${binding.searchView.query}", true)
        }

        userViewModel.searchUsersData.observe(viewLifecycleOwner) { data ->
            showLoading(false)
            if (displayRecyclerFoundProfilesList(userViewModel.searchUsersData.value ?: listOf())) {
                includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.root.visibility =
                    View.VISIBLE
                includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.includeNotfoundSearch.root.visibility =
                    View.GONE
                includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.recyclerViewMatched.visibility =
                    View.VISIBLE
                includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.layoutMatched.visibility =
                    View.VISIBLE
            } else {
                includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.root.visibility =
                    View.GONE
                includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.includeNotfoundSearch.root.visibility =
                    View.VISIBLE
                includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.recyclerViewMatched.visibility =
                    View.GONE
            }
        }

        userViewModel.recommendUserData.observe(viewLifecycleOwner) { data ->
            showLoading(false)
            if (displayRecyclerRecommendList(userViewModel.recommendUserData.value?.map { it.userPredict }
                    ?: listOf())) {
                showLoading(false)
                includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.root.visibility =
                    View.VISIBLE
                includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.layoutMayKnow.visibility =
                    View.VISIBLE
                includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.recyclerViewPeopleMayKnow.visibility =
                    View.VISIBLE
                includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.layoutMayKnow.visibility =
                    View.VISIBLE
            } else {
                showLoading(false)
                includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.root.visibility =
                    View.GONE
                includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.layoutMayKnow.visibility =
                    View.GONE
                includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.recyclerViewPeopleMayKnow.visibility =
                    View.GONE
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("textSearch", saveInstanceTextSearch)
        Log.v(TAG, "onSaveInstanceState saveInstanceTextSearch -> $saveInstanceTextSearch")
    }

    private fun displayRecyclerView(lstData: ArrayList<Category>) {
        val layoutRecyclerView = if (mainActivity.isLandscape()) GridLayoutManager(
            view?.context, 3, LinearLayoutManager.VERTICAL, false
        )
        else GridLayoutManager(view?.context, 2, LinearLayoutManager.VERTICAL, false)
        categoryAdapter = CategoryAdapter(lstData, 0) {
            if (it.getInt("position") == 1) findNavController().navigate(R.id.podcastFragment, it)
            else if (it.getInt("position") == 2) findNavController().navigate(
                R.id.itemExploreFragment,
                it
            )
            else findNavController().navigate(R.id.itemExploreFragment, it)
        }
        binding.recyclerView.apply {
            layoutManager = layoutRecyclerView
            adapter = categoryAdapter
        }
    }

    private fun initSearchBar() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                userViewModel.currentQuery = p0 ?: ""
                topicSearchViewModel.getTopicSearch().value?.let { displayRecyclerViewTopicSearch(it) }

                binding.containerRecentSearches.visibility = View.GONE
                includeTopsSongsArtistsAlbumsPlaylistsProfiles.root.visibility = View.VISIBLE
                mainActivity.showBottomNav("GONE")
                mainActivity.customToolbar("GONE")

                if (p0 != null) {
                    recentSearchViewModel.updateDataRecentSearch(p0)
                    topicSearchViewModel.getTopicSearch().observe(requireActivity()) { data ->
                        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeNotfoundSearch.root.visibility =
                            View.GONE
                        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.root.visibility =
                            View.GONE
                        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchPodcasts.root.visibility =
                            View.GONE
                        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.root.visibility =
                            View.GONE
                        when (data.first { it.isChecked }.name) {
                            "Top" -> {
                                if (displayRecyclerFoundTopList(p0)) {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.root.visibility =
                                        View.VISIBLE
                                } else {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeNotfoundSearch.root.visibility =
                                        View.VISIBLE
                                }
                            }

                            "Songs" -> {
                                if (displayRecyclerFoundSongsList(p0)) {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.root.visibility =
                                        View.VISIBLE
                                } else {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeNotfoundSearch.root.visibility =
                                        View.VISIBLE
                                }
                            }

                            "Artists" -> {
                                if (displayRecyclerFoundArtistsList(p0)) {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.root.visibility =
                                        View.VISIBLE
                                } else {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeNotfoundSearch.root.visibility =
                                        View.VISIBLE
                                }
                            }

                            "Albums" -> {
                                if (displayRecyclerFoundAlbumsList(p0)) {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.root.visibility =
                                        View.VISIBLE
                                } else {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeNotfoundSearch.root.visibility =
                                        View.VISIBLE
                                }
                            }

                            "Podcasts" -> {
                                if (displayRecyclerFoundPodcastsList(p0)) {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchPodcasts.root.visibility =
                                        View.VISIBLE
                                } else {
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeNotfoundSearch.root.visibility =
                                        View.VISIBLE
                                }
                            }

                            "Playlists" -> {
                                displayRecyclerFoundPlaylistsList(p0)
                            }

                            "Profiles" -> {
                                showLoading(true)
                                if (displayRecyclerFoundProfilesList(
                                        userViewModel.searchUsersData.value ?: listOf()
                                    )
                                ) {
                                    showLoading(false)
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.root.visibility =
                                        View.VISIBLE
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.includeNotfoundSearch.root.visibility =
                                        View.GONE
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.recyclerViewMatched.visibility =
                                        View.VISIBLE
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.layoutMatched.visibility =
                                        View.VISIBLE
                                } else {
                                    showLoading(false)
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.root.visibility =
                                        View.GONE
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.includeNotfoundSearch.root.visibility =
                                        View.VISIBLE
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.recyclerViewMatched.visibility =
                                        View.GONE
                                }

                                if (displayRecyclerRecommendList(userViewModel.recommendUserData.value?.map { it.userPredict }
                                        ?: listOf())) {
                                    showLoading(false)
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.root.visibility =
                                        View.VISIBLE
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.layoutMayKnow.visibility =
                                        View.VISIBLE
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.recyclerViewPeopleMayKnow.visibility =
                                        View.VISIBLE
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.layoutMayKnow.visibility =
                                        View.VISIBLE
                                } else {
                                    showLoading(false)
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.root.visibility =
                                        View.GONE
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.layoutMayKnow.visibility =
                                        View.GONE
                                    includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.recyclerViewPeopleMayKnow.visibility =
                                        View.GONE
                                }
                            }
                        }
                    }
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                binding.containerBrowseAll.visibility = View.GONE
                binding.containerRecentSearches.visibility = View.VISIBLE
                mainActivity.showBottomNav("GONE")
                mainActivity.customToolbar("GONE")
                saveInstanceTextSearch = p0.toString()
                return true
            }
        })
    }

    private fun displayRecyclerRecommendList(listRecommendUser: List<SearchUserResponse.UserModel>): Boolean {
        if (listRecommendUser.isEmpty()) {
            return false
        }

        recommendAdapter = UserAdapterV2(listRecommendUser.map {
            it.toUser()
        }.toCollection(ArrayList()), 1, this)
        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.recyclerViewPeopleMayKnow.apply {
            layoutManager =
                GridLayoutManager(view?.context, 1, LinearLayoutManager.HORIZONTAL, false)
            adapter = recommendAdapter
        }

        recommendAdapter.onItemClickListener = { userId ->
            findNavController().navigate(
                R.id.action_item_nav_explore_to_viewDetailsProfileFragment,
                Bundle().apply {
                    putInt("userId", userId)
                })
        }

        return true
    }

    private fun displayRecyclerFoundProfilesList(listFoundUser: List<SearchUserResponse.UserModel>): Boolean {
        if (listFoundUser.isEmpty()) {
            return false
        }

        userAdapter = UserAdapterV2(listFoundUser.map {
            it.toUser()
        }.toCollection(ArrayList()), 0, this)
        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.recyclerViewMatched.apply {
            layoutManager = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
            adapter = userAdapter
        }

        userAdapter.onItemClickListener = { userId ->
            findNavController().navigate(
                R.id.action_item_nav_explore_to_viewDetailsProfileFragment,
                Bundle().apply {
                    putInt("userId", userId)
                })
        }

        return true
    }

    private fun displayRecyclerFoundPlaylistsList(p0: String): Boolean {
        return false

    }

    private fun displayRecyclerFoundPodcastsList(p0: String): Boolean {
        //Search artist is podcast
        val listFoundArtist: ArrayList<Artist> = artistViewModel.lstDataArtists.value?.filter {
            !it.isSinger && it.artistName.trim().lowercase().contains(p0.trim().lowercase())
        } as ArrayList<Artist>

        //Search podcast
        val listFoundPodcast: ArrayList<Music> = musicViewModel.lstDataMusics.value?.filter {
            it.category.categoryID == "ca002" && it.musicName.trim().lowercase()
                .contains(p0.trim().lowercase())
        } as ArrayList<Music>

        if (listFoundArtist.isEmpty() && listFoundPodcast.isEmpty()) {
            return false
        }

        //Search podcast of artist above
        val listPodcastOfArtist: ArrayList<Music> = ArrayList()
        listFoundArtist.forEach { list ->
            listPodcastOfArtist.addAll(musicViewModel.lstDataMusics.value?.filter {
                it.category.categoryID == "ca002" && it.artist.artistId == list.artistId
            } as ArrayList<Music>)
        }

        artistAdapter = ArtistAdapter(listFoundArtist, 5)
        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchPodcasts.recyclerViewPodcastAndShow.apply {
            layoutManager =
                GridLayoutManager(view?.context, 1, LinearLayoutManager.HORIZONTAL, false)
            adapter = artistAdapter
        }

        musicAdapter = if (listPodcastOfArtist.isNotEmpty()) {
            MusicAdapter(
                listFoundPodcast.union(listPodcastOfArtist).toList() as ArrayList<Music>, 2, this
            )
        } else {
            MusicAdapter(listFoundPodcast, 2, this)
        }

        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchPodcasts.recyclerViewEpisode.apply {
            layoutManager = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
            adapter = musicAdapter
        }

        return true
    }

    private fun displayRecyclerFoundAlbumsList(p0: String): Boolean {
        val listFoundMusic: ArrayList<Music> = musicViewModel.lstDataMusics.value?.filter {
            it.musicName.trim().lowercase().contains(p0.trim().lowercase()) && it.isAlbum
        } as ArrayList<Music>

        if (listFoundMusic.isEmpty()) {
            return false
        }

        musicAdapter = MusicAdapter(
            listFoundMusic, 4, this
        )


        val layout = if (mainActivity.isLandscape()) GridLayoutManager(
            view?.context,
            3,
            LinearLayoutManager.VERTICAL,
            false
        )
        else GridLayoutManager(view?.context, 2, LinearLayoutManager.VERTICAL, false)

        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.recyclerViewFoundList.apply {
            layoutManager = layout
            adapter = musicAdapter
        }

        return true
    }

    private fun displayRecyclerFoundArtistsList(p0: String): Boolean {
        val listFoundArtist: ArrayList<Artist> = artistViewModel.lstDataArtists.value?.filter {
            it.artistName.trim().lowercase().contains(p0.trim().lowercase())
        } as ArrayList<Artist>

        if (listFoundArtist.isEmpty()) return false

        artistAdapter = ArtistAdapter(
            listFoundArtist,
            4,
        )

        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.recyclerViewFoundList.apply {
            layoutManager = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
            adapter = artistAdapter
        }

        return true
    }

    private fun displayRecyclerFoundSongsList(p0: String): Boolean {
        val listFoundMusic: ArrayList<Music> = musicViewModel.lstDataMusics.value?.filter {
            it.musicName.trim().lowercase().contains(
                p0.trim().lowercase()
            ) && it.category.categoryID != "ca002" && !it.isAlbum
        } as ArrayList<Music>

        if (listFoundMusic.isEmpty()) return false

        musicAdapter = MusicAdapter(
            listFoundMusic, 6, this
        )

        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.recyclerViewFoundList.apply {
            layoutManager = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
            adapter = musicAdapter
        }

        return true
    }

    private fun displayRecyclerFoundTopList(p0: String): Boolean {
        val listFoundMusic: ArrayList<Music> =
            musicViewModel.lstDataMusics.value?.sortedByDescending { it.totalListeners }?.filter {
                it.musicName.trim().lowercase()
                    .contains(p0.trim().lowercase()) && it.category.categoryID != "ca002"
            } as ArrayList<Music>
        val listFoundArtist: ArrayList<Artist> =
            artistViewModel.lstDataArtists.value?.sortedByDescending { it.totalNumberOfListeners }
                ?.filter {
                    it.artistName.trim().lowercase().contains(p0.trim().lowercase())
                } as ArrayList<Artist>

        if (listFoundArtist.isEmpty() && listFoundMusic.isEmpty()) return false

        musicAdapter = MusicAdapter(
            listFoundMusic, 3, this
        )
        artistAdapter = ArtistAdapter(
            listFoundArtist, 4
        )
        val concatAdapter = ConcatAdapter(musicAdapter, artistAdapter)

        includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearch.recyclerViewFoundList.apply {
            layoutManager = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
            adapter = concatAdapter
        }

        return true
    }

    private fun displayRecyclerViewRecentSearches(lstData: ArrayList<RecentSearch>) {
        val layout = LinearLayoutManager(view?.context, LinearLayoutManager.VERTICAL, false)
        recentSearchesAdapter = RecentSearchesAdapter(lstData)
        recentSearchesAdapter.setCallBack(this)
        binding.recyclerViewRecentSearches.apply {
            layoutManager = layout
            adapter = recentSearchesAdapter
        }
    }

    override fun setQuery(name: String) {
        binding.searchView.setQuery(name, true)
    }

    private fun showLoading(isShow: Boolean = true) {
        if (isShow) {
            binding.includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.progressBar.visibility =
                View.VISIBLE
            includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.root.visibility =
                View.VISIBLE
            includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.includeNotfoundSearch.root.visibility =
                View.GONE
            includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.recyclerViewMatched.visibility =
                View.GONE
            includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.recyclerViewPeopleMayKnow.visibility =
                View.GONE
            includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.layoutMatched.visibility =
                View.GONE
            includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.layoutMayKnow.visibility =
                View.GONE
        } else {
            binding.includeTopsSongsArtistsAlbumsPlaylistsProfiles.includeFoundSearchProfiles.progressBar.visibility =
                View.GONE
        }
    }
}