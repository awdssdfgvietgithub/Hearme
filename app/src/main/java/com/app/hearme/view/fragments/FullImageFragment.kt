package com.app.hearme.view.fragments

import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.app.hearme.MainActivity
import com.app.hearme.R
import com.app.hearme.databinding.FragmentFullImageBinding
import com.caverock.androidsvg.SVG
import com.caverock.androidsvg.SVGParseException

class FullImageFragment : Fragment() {
    private lateinit var binding: FragmentFullImageBinding
    private lateinit var mainActivity: MainActivity
    private lateinit var url: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        url = arguments?.getString("url").toString()
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_full_image, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity = activity as MainActivity

        val svgData = Base64.decode(url, Base64.DEFAULT)
        val svgInputStream = svgData.inputStream()

        try {
            val svg = SVG.getFromInputStream(svgInputStream)
            val drawable = PictureDrawable(svg.renderToPicture())
            binding.imageView.setLayerType(View.LAYER_TYPE_SOFTWARE, null)
            binding.imageView.setImageDrawable(drawable)
        } catch (e: SVGParseException) {
            Log.e("SVGDecode", "Error decoding SVG", e)
        }

        binding.imageButtonClose.setOnClickListener() {
            findNavController().popBackStack()
        }
    }

    override fun onResume() {
        super.onResume()
        mainActivity.customToolbar(
            "GONE"
        )
        mainActivity.showBottomNav("GONE")
    }
}