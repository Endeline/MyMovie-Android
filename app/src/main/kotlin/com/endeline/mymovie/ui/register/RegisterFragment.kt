package com.endeline.mymovie.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.endeline.mymovie.databinding.RegisterFragmentBinding
import com.endeline.mymovie.ui.register.RegisterViewModel

class RegisterFragment : Fragment() {

    private lateinit var viewModel: RegisterViewModel

    private lateinit var binding: RegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterFragmentBinding.inflate(inflater)

        return binding.root
    }

}
