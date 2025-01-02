package com.app.hearme

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.PictureDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import android.util.Base64
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.core.app.NotificationCompat
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.app.hearme.data.MoreSongData
import com.app.hearme.data.adapter.MoreSongDropdownAdapter
import com.app.hearme.data.control.CustomSpinner
import com.app.hearme.model.Music
import com.app.hearme.view.fragments.homeactionmenu.HomeFragment
import com.app.hearme.view.fragments.library.MyLibraryFragment
import com.app.hearme.view.fragments.profile_settings.ProfileFragment
import com.app.hearme.view.fragments.search.*
import com.app.hearme.view.tab_viewpager.TabPodcastFragment
import com.app.hearme.view.tab_viewpager.TabSongFragment
import com.app.hearme.viewmodel.*
import com.app.hearme.R
import com.app.hearme.data.util.UserManager
import com.app.hearme.databinding.ActivityMainBinding
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException
import java.io.IOException
import java.net.URL
import kotlin.system.exitProcess


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    lateinit var exoPlayer: ExoPlayer
    val viewModelTopicSearch: TopicSearchViewModel by viewModels()
    val viewModelRecentSearch: RecentSearchViewModel by viewModels()
    val viewModelMusic: MusicViewModel by viewModels()
    val songPlayViewModel: SongPlayViewModel by viewModels()
    private val viewModelArtist: ArtistViewModel by viewModels()
    private val viewModelCategory: CategoriesViewModel by viewModels()
    private val viewModelPlaylist: PlaylistViewModel by viewModels()
    private val viewModelEmail: EmailViewModel by viewModels()
    val viewModelUser: UserViewModel by viewModels()

    var email: String = ""

    var checkRemember = true

    //    var checkInHome = false
    var checkInHistory = false
    var language: String = "English (US)"
    val dataListSong = ArrayList<Int>()
    var mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(binding.root)

        Log.v("State", "onCreate() -> Activity")


        dataListSong.add(R.raw.shape_of_you_nokia)
        dataListSong.add(R.raw.beauteous_upbeat_electronic)
        dataListSong.add(R.raw.funny_dance_music)
        dataListSong.add(R.raw.happy_rock)
        dataListSong.add(R.raw.beauteous_upbeat_electronic)

        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.transparent)
        WindowCompat.getInsetsController(window, window.decorView).apply {
            isAppearanceLightStatusBars = true
        }


//        requestRuntimePermission()

        exoPlayer = ExoPlayer.Builder(this).build()

        // run Media Player Service Running
        val mediaUrl = " "
        val intent = Intent(this@MainActivity, MediaPlayerService::class.java)
        intent.putExtra("media_url", mediaUrl)
        startService(intent)

        binding.lifecycleOwner = this
        Log.v("State", "binding -> Activity")

        viewModelEmail.selectedItem.observe(this, Observer {
//            Toast.makeText(this, "$it", Toast.LENGTH_SHORT).show()
            email = "phuongviet.huit@gmail.com"
//            Toast.makeText(this, "viewModelEmail -> $email", Toast.LENGTH_SHORT).show()
        })

        if (savedInstanceState != null) {
            email = "phuongviet.huit@gmail.com"
//            Toast.makeText(this, "savedInstanceState -> $email", Toast.LENGTH_SHORT).show()
        }

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main) as NavHostFragment
        navController = navHostFragment.navController

        customToolbar("GONE")
        showBottomNav("GONE")

        binding.bottomNavView.setOnItemSelectedListener { onClickItemBottomNav(it) }
        binding.toolBar.setOnMenuItemClickListener() {
            when (it.itemId) {
                R.id.item_search -> {
                    navController.navigate(R.id.item_nav_explore)

                    true
                }

                R.id.item_notification -> {
                    navController.navigate(R.id.action_item_nav_home_to_notificationFragment)
                    true
                }

                R.id.item_filter -> {
                    true
                }

                R.id.item_more_circle -> {
                    showDialogBottom()
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("email", email)
    }

    override fun onBackPressed() {
        val count = navHostFragment.childFragmentManager.backStackEntryCount
        if (count == 0) {
            showDialogBox()
        } else {
            super.onBackPressed()
            navHostFragment.childFragmentManager.addOnBackStackChangedListener {
                if (navHostFragment.childFragmentManager.backStackEntryCount < count) {
                    val count2 = navHostFragment.childFragmentManager.backStackEntryCount
                    when (navHostFragment.childFragmentManager.fragments.first()) {
                        is HomeFragment -> {
                            binding.bottomNavView.menu.getItem(0).isChecked = true
                        }

                        is ExploreFragment -> {
                            binding.bottomNavView.menu.getItem(1).isChecked = true
                        }

                        is MyLibraryFragment -> {
                            binding.bottomNavView.menu.getItem(2).isChecked = true
                        }

                        is ProfileFragment -> {
                            binding.bottomNavView.menu.getItem(3).isChecked = true
                        }
                    }
                }
            }
        }
    }

    private fun showDialogBox() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_back)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        val btnYes: TextView = dialog.findViewById(R.id.btnYesDialog)
        val btnNo: TextView = dialog.findViewById(R.id.btnNoDialog)

        btnYes.setOnClickListener {
            moveTaskToBack(true);
            exitProcess(-1)
        }

        btnNo.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun showDialogBottom() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_bottomsheet)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window?.attributes?.windowAnimations =
            com.google.android.material.R.style.MaterialAlertDialog_Material3_Animation
        dialog.window?.setGravity(Gravity.BOTTOM)
        dialog.show()

        val btnYes: Button = dialog.findViewById(R.id.btnYes)
        val btnNo: Button = dialog.findViewById(R.id.btnCancel)

        btnYes.setOnClickListener {
            dialog.dismiss()
            UserManager.getInstance().clearData()
            navController.navigate(R.id.signInFragment)
        }

        btnNo.setOnClickListener {
            dialog.dismiss()
        }
    }

    private fun onClickItemBottomNav(it: MenuItem) = when (it.itemId) {
        R.id.item_nav_home -> {
            navController.navigate(R.id.item_nav_home)
            true
        }

        R.id.item_nav_explore -> {
            navController.navigate(R.id.item_nav_explore)
            true
        }

        R.id.item_nav_library -> {
            navController.navigate(R.id.item_nav_library)
            true
        }

        R.id.item_nav_profile -> {
            navController.navigate(R.id.item_nav_profile)
            true
        }

        else -> false
    }

    fun hideKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val hideMe = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hideMe.hideSoftInputFromWindow(view.windowToken, 0)
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    fun customToolbar(
        isVisible: String,
        title: String? = null,
        userName: String? = null,
        backgroundColor: Int = R.color.transparent,
        navIcon: Drawable? = null,
        showIcMore: Boolean = false,
        showIcFilter: Boolean = false,
        showIcSearch: Boolean = false,
        showIcNotification: Boolean = false,
        showIcEdit: Boolean = false,
        showIcScan: Boolean = false,
        navIconUrl: String? = null,
        avatar: String? = null,
    ) {
        //Toolbar visibility
        when (isVisible.lowercase()) {
            "visible" -> binding.toolBar.visibility = View.VISIBLE
            "invisible" -> binding.toolBar.visibility = View.INVISIBLE
            else -> binding.toolBar.visibility = View.GONE
        }

        //Toolbar title
        if (title != null) {
            binding.toolBarTitle.isVisible = true
            binding.containerTitleForHome.isVisible = false
            binding.toolBarTitle.text = title
        } else if (userName != null) {
            binding.toolBarTitle.isVisible = false
            binding.containerTitleForHome.isVisible = true
            binding.textUserName.text = userName
        } else {
            binding.toolBarTitle.isVisible = false
            binding.containerTitleForHome.isVisible = false
        }

        //Toolbar background
        binding.toolBar.setBackgroundColor(
            resources.getColor(
                backgroundColor,
                null
            )
        )

        if (avatar != null && UserManager.getInstance().getUserId() != -1) {
            val svgData = Base64.decode(avatar, Base64.DEFAULT)
            val svgInputStream = svgData.inputStream()

            try {
                val svg = SVG.getFromInputStream(svgInputStream)
                val drawable = PictureDrawable(svg.renderToPicture())
                binding.imageViewAvatar.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                binding.imageViewAvatar.setImageDrawable(drawable)
            } catch (e: SVGParseException) {
                Log.e("SVGDecode", "Error decoding SVG", e)
            }
        } else {
            val options = RequestOptions()
                .centerCrop()
                .error(R.drawable.ellipse)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .priority(Priority.HIGH)
                .dontTransform()

            Glide.with(this)
                .load(R.drawable.ellipse)
                .apply(options)
                .transition(DrawableTransitionOptions.withCrossFade(250))
                .into(binding.imageViewAvatar)

            binding.toolBar.navigationIcon = null
        }

        if (navIcon == null && navIconUrl == null && avatar == null) {

        } else {
            //Toolbar nav icon
            if (navIcon != null) {
                binding.toolBar.navigationIcon = navIcon
            } else {
                binding.toolBar.navigationIcon = null
            }

            if (navIconUrl != null) {
                val options = RequestOptions()
                    .centerCrop()
                    .error(R.drawable.ellipse)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .priority(Priority.HIGH)
                    .dontTransform()

                Glide.with(this)
                    .load(navIconUrl)
                    .apply(options)
                    .transition(DrawableTransitionOptions.withCrossFade(250))
                    .into(binding.imageViewAvatar)
            }
        }

        binding.toolBar.menu.findItem(R.id.item_more_circle).isVisible = showIcMore
        binding.toolBar.menu.findItem(R.id.item_filter).isVisible = showIcFilter
        binding.toolBar.menu.findItem(R.id.item_search).isVisible = showIcSearch
        binding.toolBar.menu.findItem(R.id.item_notification).isVisible = showIcNotification
        binding.toolBar.menu.findItem(R.id.item_scan).isVisible = showIcScan
        binding.toolBar.menu.findItem(R.id.item_edit).isVisible = showIcEdit
    }

    fun showBottomNav(isVisible: String) {
        when (isVisible.lowercase()) {
            "visible" -> binding.bottomNavView.visibility = View.VISIBLE
            "invisible" -> binding.bottomNavView.visibility = View.INVISIBLE
            else -> binding.bottomNavView.visibility = View.GONE
        }
    }

    fun changeSizeDrawable(image: Int, width: Int, height: Int): Drawable {
        val drawable: Drawable = if (image != null)
            resources.getDrawable(image)
        else
            resources.getDrawable(R.drawable.avt_home)
        val bitmap: Bitmap = (drawable as BitmapDrawable).bitmap
        val newDrawable: Drawable =
            BitmapDrawable(resources, Bitmap.createScaledBitmap(bitmap, width, height, true))
        return newDrawable
    }

    fun showSnack(view: View, text: String) {
        val snack = Snackbar.make(
            view,
            text,
            Snackbar.LENGTH_SHORT
        )
        snack.show()
    }

    fun isLandscape(): Boolean =
        resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    fun changeSizeBitmap(bm: Bitmap, newWidth: Int, newHeight: Int): Bitmap? {
        val width = bm.width
        val height = bm.height
        val scaleWidth = newWidth.toFloat() / width
        val scaleHeight = newHeight.toFloat() / height
        // CREATE A MATRIX FOR THE MANIPULATION
        val matrix = Matrix()
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight)

        // "RECREATE" THE NEW BITMAP
        val resizedBitmap = Bitmap.createBitmap(
            bm, 0, 0, width, height, matrix, false
        )
        bm.recycle()
        return resizedBitmap
    }

    fun protectedPhone(phone: String): String {
        var editPhone = ""
        for (item in phone.indices) {
            if (item in 5..10)
                editPhone += "*"
            else
                editPhone += phone[item]
        }
        return editPhone
    }

    fun protectedEmail(email: String): String {
        var edtEmail = ""
        val splitAt = email.split("@")
        val str1 = splitAt[0]
        val str2 = splitAt[1]
        val len = str1.length
        var count = 0

        val int: Int = when (len) {
            in 6..8 -> {
                2
            }

            else -> {
                3
            }
        }
        for (item in str1.indices) {
            if (count < int) {
                edtEmail += email[item]
                count++
                continue
            }
            if (item < len - int)
                edtEmail += "*"
            else
                edtEmail += email[item]
        }

        return edtEmail.plus("@$str2")
    }

    fun initSpinnerMore(
        spinnerDropDownMore: CustomSpinner,
        music: Music,
        type: Int,
        fromFragment: Fragment,
    ) {
        spinnerDropDownMore.adapter =
            MoreSongDropdownAdapter(spinnerDropDownMore.context, MoreSongData.data(), type)

        spinnerDropDownMore.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    v: View,
                    position: Int,
                    id: Long,
                ) {
                    when (position) {
                        1 -> { // Add to love list
                            var isLove = false
                            if (viewModelUser.isMusicInBlackList(
                                    email,
                                    music
                                )
                            ) {
                                val builder = AlertDialog.Builder(v.context)
                                builder.apply {
                                    setTitle("Confirm")
                                    setMessage("${music.musicName} is putting into blacklist, are you want to remove it?")
                                    setPositiveButton(
                                        "YES"
                                    ) { dialog, _ ->
                                        viewModelUser.apply {
                                            updateBlackListMusic(
                                                email,
                                                music,
                                                false
                                            )
                                            updateListMusicsLoved(
                                                email,
                                                music,
                                                true
                                            )
                                        }
                                        showSnack(
                                            v,
                                            "You added ${music.musicName} to love list!"
                                        )
                                        dialog.dismiss()
                                    }
                                    setNegativeButton("NO") { dialog, _ ->
                                        dialog.dismiss()
                                    }
                                }.create().show()
                            } else {
                                if (viewModelUser.lstDataUser.value?.first { it.email == email }?.listMusicsLoved?.none { it.musicID == music.musicID } == true) {
                                    isLove = true
                                    showSnack(
                                        v,
                                        "You added ${music.musicName} to love list!"
                                    )
                                } else {
                                    isLove = false
                                    showSnack(
                                        v,
                                        "You removed ${music.musicName} from love list!"
                                    )
                                }
                                viewModelUser.updateListMusicsLoved(
                                    email,
                                    music,
                                    isLove
                                )
                            }
                        }

                        2 -> { // Add to playlist
                            val isAdd: Boolean
                            if (viewModelUser.isMusicInBlackList(
                                    email,
                                    music
                                )
                            ) {
                                val builder = AlertDialog.Builder(v.context)
                                builder.apply {
                                    setTitle("Confirm")
                                    setMessage("${music.musicName} is putting into blacklist, are you want to remove it?")
                                    setPositiveButton(
                                        "YES"
                                    ) { dialog, _ ->
                                        viewModelUser.apply {
                                            updateBlackListMusic(
                                                email,
                                                music,
                                                false
                                            )
                                            updateListPlayedMusic(
                                                email,
                                                music,
                                                true
                                            )
                                        }
                                        showSnack(
                                            v,
                                            "You added ${music.musicName} to playlist!"
                                        )
                                        dialog.dismiss()
                                    }
                                    setNegativeButton("NO") { dialog, _ ->
                                        dialog.dismiss()
                                    }
                                }.create().show()
                            } else {
                                if (viewModelUser.lstDataUser.value?.first { it.email == email }?.listPlaylist?.get(
                                        0
                                    )?.listMusic?.none { it.musicID == music.musicID } == true
                                ) {
                                    isAdd = true
                                    showSnack(
                                        v,
                                        "You added ${music.musicName} to playlist 'My Favorite Pop Songs'!"
                                    )
                                } else {
                                    isAdd = false
                                    showSnack(
                                        v,
                                        "You removed ${music.musicName} from playlist 'My Favorite Pop Songs'!"
                                    )
                                }
                                viewModelUser.updateListPlayedMusic(
                                    email,
                                    music,
                                    isAdd
                                )
                            }
                        }

                        3 -> { // Add to blacklist
                            val isDontPlay: Boolean
                            if (viewModelUser.lstDataUser.value?.first { it.email == email }?.blackListMusic?.none { it.musicID == music.musicID } == true) {
                                isDontPlay = true
                                showSnack(
                                    v,
                                    "You added ${music.musicName} to blacklist!"
                                )
                            } else {
                                isDontPlay = false
                                showSnack(
                                    v,
                                    "You removed ${music.musicName} from blacklist!"
                                )
                            }
                            viewModelUser.updateBlackListMusic(
                                email,
                                music,
                                isDontPlay
                            )
                        }

                        4 -> { // Down
                            val isDown: Boolean
                            if (viewModelUser.lstDataUser.value?.first { it.email == email }?.listMusicsDownloaded?.none { it.musicID == music.musicID } == true) {
                                isDown = true
                                showSnack(
                                    v,
                                    "You added ${music.musicName} to List Downloaded!"
                                )
                            } else {
                                isDown = false
                                showSnack(
                                    v,
                                    "You removed ${music.musicName} from List Downloaded!"
                                )
                            }
                            viewModelUser.updateListMusicsDownloaded(
                                email,
                                music,
                                isDown
                            )
                        }

                        5 -> { // View artist
                            var designation: Int? = null
                            when (fromFragment) {
                                is ExploreFragment -> {
                                    designation =
                                        R.id.action_item_nav_explore_to_viewDetailsArtistFragment
                                }

                                is TabSongFragment -> {
                                    designation =
                                        R.id.action_notificationFragment_to_viewDetailsArtistFragment
                                }

                                is ViewDetailsSongFragment -> {
                                    designation =
                                        R.id.action_viewDetailsSongFragment_to_viewDetailsArtistFragment
                                }

                                is ViewDetailsArtistFragment -> {
                                    showSnack(v, "You are here!")
                                }

                                is ViewDetailsAlbumFragment -> {
                                    designation =
                                        R.id.action_viewDetailsAlbumFragment_to_viewDetailsArtistFragment
                                }
                            }
                            if (designation != null) {
                                navController.navigate(
                                    designation,
                                    Bundle().apply {
                                        putString("artistID", music.artist.artistId)
                                    })
                            }
                        }

                        6 -> { // Go to album
                            Toast.makeText(v.context, "Go to album", Toast.LENGTH_SHORT).show()
                        }

                        7 -> { // Mark as Played
                            Toast.makeText(v.context, "Mark as Played", Toast.LENGTH_SHORT).show()

                        }

                        8 -> { // Go to Podcast
                            var designation: Int? = null
                            when (fromFragment) {
                                is ExploreFragment -> {
                                    designation =
                                        R.id.action_item_nav_explore_to_viewDetailsArtistOfPodcastFragment
                                }

                                is TabPodcastFragment -> {
                                    designation =
                                        R.id.action_notificationFragment_to_viewDetailsArtistOfPodcastFragment
                                }
//                                is ViewDetailsSongFragment -> {
//                                    designation =
//                                        R.id.action_viewDetailsSongFragment_to_viewDetailsArtistFragment
//                                }
                                is ViewDetailsArtistOfPodcastFragment -> {
                                    showSnack(v, "You are here!")
                                }
//                                is ViewDetailsAlbumFragment -> {
//                                    designation =
//                                        R.id.action_viewDetailsAlbumFragment_to_viewDetailsArtistFragment
//                                }
                            }
                            if (designation != null) {
                                navController.navigate(
                                    designation,
                                    Bundle().apply {
                                        putString("artistID", music.artist.artistId)
                                    })
                            }
                        }

                        9 -> { // Share
                            Toast.makeText(v.context, "Share", Toast.LENGTH_SHORT).show()

                        }
                    }
                }
            }
    }

    // For requesting permission
    private fun requestRuntimePermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                13
            )
            Log.v(TAG, "121w32321")
        }

//        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
//            Log.v(TAG,"121w32321")
//        } else {
//            Log.v(TAG,"Permission Granted")
//        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 13) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
//                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                Log.v(TAG, "Permission Granted")
            else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    13
                )
                Log.v(TAG, "xxxxxxxxxxxxxx")
            }
        }
//        if (grantResults[0] == 1) {
//            Log.v(TAG,"Permission Granted")
//        } else {
//            Log.v(TAG,"xxxxxxxxxxxxxx")
//            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
//        }
    }

    @SuppressLint("MissingPermission")
    fun showNotificationMedia(music: Music) {
        val notificationManagerCompat = NotificationManagerCompat.from(this)
        val mediaSession = MediaSessionCompat(this, "MediaNotification")

        var image: Bitmap? = null
        image = try {
            val url = URL(music.image)
            BitmapFactory.decodeStream(url.openConnection().getInputStream())
        } catch (e: IOException) {
            BitmapFactory.decodeResource(resources, R.drawable.img_music)
        }

        val notification = NotificationCompat.Builder(this, "HEAR_ME_APP")
            // Show controls on lock screen even when user hides sensitive content.
//            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSmallIcon(R.drawable.logo_default)
            .setLargeIcon(image)
            .setSubText("Hearme App")
            .setContentTitle(music.musicName)
            .setContentText(music.artist.artistName)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setCategory(NotificationCompat.CATEGORY_TRANSPORT)
            // Add media control buttons that invoke intents in your media service
            .addAction(R.drawable.ic_previous, "Previous", null) // #0
            .addAction(R.drawable.ic_pause, "Pause", null) // #1
            .addAction(R.drawable.ic_next, "Next", null) // #2
            // Apply the media style template
            .setStyle(
                androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(0, 1, 2/* #1: pause button \*/)
                    .setMediaSession(mediaSession.sessionToken)
            )
            .build()

        notificationManagerCompat.notify(1, notification)
    }

    fun zoomImage(imageView: ImageView, isZoomOut: Boolean) {
        imageView.layoutParams =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        imageView.scaleType = ImageView.ScaleType.FIT_XY
    }
}
