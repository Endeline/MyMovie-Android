package com.endeline.mymovie.ui.video

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import androidx.fragment.app.Fragment
import com.endeline.mymovie.databinding.VideoFragmentBinding

class VideoFragment : Fragment() {

    companion object {
        private const val YOUTUBE_URL = "https://www.youtube.com/embed/"
        private const val SITE_YOUTUBE = "YouTube"
    }

    private lateinit var binding: VideoFragmentBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VideoFragmentBinding.inflate(inflater)

        binding.webView.apply {
            settings.javaScriptEnabled = true
            webChromeClient = WebChromeClient()
        }

        return binding.root
    }

    @SuppressLint("SourceLockedOrientationActivity")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        if (arguments?.getString("site") == SITE_YOUTUBE) {
            binding.webView.loadUrl("$YOUTUBE_URL${arguments?.getString("movie_link")}")
        }
    }

    override fun onDestroyView() {
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        super.onDestroyView()
    }

}