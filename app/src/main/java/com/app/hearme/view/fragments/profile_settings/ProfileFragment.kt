package com.app.hearme.view.fragments.profile_settings

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.drawable.PictureDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.data.util.UserManager
import com.app.hearme.databinding.FragmentProfileBinding
import com.app.hearme.viewmodel.ProfileFragmentViewModel
import com.app.hearme.viewmodel.UserViewModel
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileFragment : Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var storageReference: StorageReference
    private lateinit var mainActivity: MainActivity
    private var email: String? = ""
    private var avatar: String? = null
    private var fullName: String? = ""
    private val options = RequestOptions()
        .centerCrop()
        .error(R.drawable.ellipse)
        .diskCacheStrategy(DiskCacheStrategy.NONE)
        .priority(Priority.HIGH)
        .dontTransform()

    private val userViewModel: UserViewModel by activityViewModels()
    private val profileFragmentViewModel: ProfileFragmentViewModel by viewModels()

    private val changeImage =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
//            if (it.resultCode == Activity.RESULT_OK) {
//                val data = it.data
//                avatarUri = data?.data
//                uploadImageToFirebase()
//            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        mainActivity = activity as MainActivity
        email = "phuongviet.huit@gmail.com"
        binding.tvLanguage.text = mainActivity.language
        mainActivity.customToolbar(
            "VISIBLE",
            "Profile",
            null,
            R.color.transparent,
            ContextCompat.getDrawable(requireContext(), R.drawable.logo_nav),
            true
        )
        mainActivity.showBottomNav("VISIBLE")

        avatar = UserManager.getInstance().getAvatar()
        fullName = UserManager.getInstance().getDisplayName()

        if (avatar != null && UserManager.getInstance().getUserId() != -1) {
            val svgData = Base64.decode(avatar, Base64.DEFAULT)
            val svgInputStream = svgData.inputStream()

            try {
                val svg = SVG.getFromInputStream(svgInputStream)
                val drawable = PictureDrawable(svg.renderToPicture())
                binding.imgAvatar.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
                binding.imgAvatar.setImageDrawable(drawable)
            } catch (e: SVGParseException) {
                Log.e("SVGDecode", "Error decoding SVG", e)
            }
        } else
            binding.imgAvatar.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.ellipse)

        profileFragmentViewModel.selectedItem.observe(requireActivity()) {
            Glide.with(this)
                .load(it)
                .apply(options)
                .transition(DrawableTransitionOptions.withCrossFade(250))
                .into(binding.imgAvatar)
        }

        binding.tvNameUser.text = fullName
        binding.tvEmailUser.text = email

        binding.btnGetPremium.setOnClickListener {
            findNavController().navigate(R.id.action_item_nav_profile_to_premiumFragment)
        }

        binding.linearProfile.setOnClickListener {
            findNavController().navigate(R.id.action_item_nav_profile_to_profileDetailFragment)
        }
        binding.linearNotification.setOnClickListener {
            findNavController().navigate(R.id.action_item_nav_profile_to_settingNotificationFragment)
        }
        binding.linearAudioVideo.setOnClickListener {
            findNavController().navigate(R.id.action_item_nav_profile_to_audioVideoFragment)
        }
        binding.linearPlayback.setOnClickListener {
            findNavController().navigate(R.id.action_item_nav_profile_to_settingPlaybackFragment)
        }
        binding.linearDataSaver.setOnClickListener {
            findNavController().navigate(R.id.action_item_nav_profile_to_dataSaverStorageFragment)
        }
        binding.linearSecurity.setOnClickListener {
            findNavController().navigate(R.id.action_item_nav_profile_to_securityFragment)
        }
        binding.linearLanguage.setOnClickListener {
            findNavController().navigate(R.id.action_item_nav_profile_to_languageFragment)
        }
        binding.linearDarkMode.setOnClickListener {

        }

        binding.imageViewEditAvatar.setOnClickListener {
            val pickImg = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
            changeImage.launch(pickImg)
        }

        binding.imgAvatar.setOnClickListener() {
            findNavController().navigate(R.id.fullImageFragment, Bundle().apply {
                putString("url", avatar)
            })
        }
    }

    private fun uploadImageToFirebase() {
//        val progressDialog = ProgressDialog(requireContext())
//        progressDialog.setTitle("Uploading File...")
//        progressDialog.show()
//
//        CoroutineScope(Dispatchers.IO).launch {
//            storageReference =
//                FirebaseStorage.getInstance().getReference("images/users/user_$email.png")
//            storageReference.putFile(avatarUri!!).addOnSuccessListener { snapshot ->
//                if (progressDialog.isShowing)
//                    progressDialog.dismiss()
//                Toast.makeText(requireContext(), "Successfully!", Toast.LENGTH_SHORT).show()
//                val result = snapshot.storage.downloadUrl
//                result.addOnSuccessListener {
//                    avatarUrl = it.toString()
//                    if (avatarUrl != null) {
//                        email?.let { it1 -> userViewModel.updateAvatar(it1, avatarUrl!!) }
//                        profileFragmentViewModel.selectItem(avatarUrl!!)
//                    }
//                }
//            }.addOnFailureListener() {
//                if (progressDialog.isShowing)
//                    progressDialog.dismiss()
//                Toast.makeText(requireContext(), "Error!", Toast.LENGTH_SHORT).show()
//            }
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}