package com.endeline.movielibrary.ui.gui.video

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.endeline.movielibrary.databinding.VideoFragmentBinding
import dagger.android.support.DaggerFragment

class VideoFragment : DaggerFragment() {

    private var _binding: VideoFragmentBinding? = null
    private val binding get() = _binding!!

    private val args by navArgs<VideoFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = VideoFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        setComponent()
    }

    override fun onDestroyView() {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        super.onDestroyView()

        _binding = null
    }



    @SuppressLint("SetJavaScriptEnabled")
    private fun setComponent() = with(binding) {
        webView.apply {
            settings.javaScriptEnabled = true
            webChromeClient = WebChromeClient()
        }

        if (args.site == SITE_YOUTUBE) {
            webView.loadUrl("$YOUTUBE_URL${args.movieLink}")
        }
    }

    companion object {
        private const val YOUTUBE_URL = "https://www.youtube.com/embed/"
        private const val SITE_YOUTUBE = "YouTube"
    }
}
