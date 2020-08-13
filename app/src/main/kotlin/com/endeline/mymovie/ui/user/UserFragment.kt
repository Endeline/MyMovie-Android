package com.endeline.mymovie.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.endeline.mymovie.R
import com.endeline.mymovie.databinding.LoginFragmentBinding
import com.endeline.mymovie.databinding.ProfileFragmentBinding
import com.endeline.mymovie.di.ViewModelFactory
import com.endeline.mymovie.extensions.validate

class UserFragment : Fragment() {

    private val viewModelFactory: ViewModelFactory.UserViewModel = ViewModelFactory.UserViewModel()

    private val viewModel by viewModels<UserViewModel>(factoryProducer = { viewModelFactory })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val isLogged: Boolean = viewModel.isUserLogged()

        return if (isLogged) {
            createLoggedLayout(inflater)
        } else {
            createNotLoggedLayout(inflater)
        }
    }

    private fun createLoggedLayout(inflater: LayoutInflater) : View {
        val binding = ProfileFragmentBinding.inflate(inflater)

        return binding.root
    }

    private fun createNotLoggedLayout(inflater: LayoutInflater) : View {
        val binding = LoginFragmentBinding.inflate(inflater)

        initNotLoggedLayout(binding)

        return binding.root
    }

    private fun initNotLoggedLayout(binding: LoginFragmentBinding) = with(binding) {
        viewModel.getChangeLoginStatusLiveData().observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().popBackStack()
            } else {
                //todo register info
            }
        })

        loginButton.setOnClickListener {
            if (isCorrectData(binding)) {
                viewModel.login(inputLogin.text.toString(), inputPassword.text.toString())
            }
        }

        forgotPasswordButton.setOnClickListener {
            findNavController().navigate(UserFragmentDirections.toForgotPassword())
        }

        registerButton.setOnClickListener {
            findNavController().navigate(UserFragmentDirections.toRegister())
        }
    }

    private fun isCorrectData(binding: LoginFragmentBinding): Boolean = with(binding) {
        val logicCorrect = inputLogin.validate(getString(R.string.login_empty), getString(R.string.login_to_short))
        val passwordCorrect = inputPassword.validate(getString(R.string.password_empty), getString(R.string.password_to_short))

        return logicCorrect && passwordCorrect
    }

}
