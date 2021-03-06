package com.endeline.movielibrary.ui.gui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.endeline.movielibrary.R
import com.endeline.movielibrary.databinding.LoginFragmentBinding
import com.endeline.movielibrary.databinding.ProfileFragmentBinding
import com.endeline.movielibrary.di.factory.ViewModelProviderFactory
import com.endeline.movielibrary.ui.extensions.isValidInput
import com.endeline.movielibrary.ui.extensions.showDialogWithButtons
import com.endeline.movielibrary.ui.gui.user.register.RegisterFragment
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class UserFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    private val viewModel by viewModels<UserViewModel> {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val savedStateHandle = findNavController().currentBackStackEntry?.savedStateHandle
        savedStateHandle?.getLiveData(RegisterFragment.REGISTER_STATUS_LIVE_DATA, false)
            ?.observe(this) { profileRegistered ->
                if (profileRegistered) {
                    findNavController().popBackStack()
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return if (viewModel.isUserLogged()) {
            createLoggedLayout(inflater, container)
        } else {
            createNotLoggedLayout(inflater, container)
        }
    }

    private fun createLoggedLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        val binding = ProfileFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun createNotLoggedLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): View {
        val binding = LoginFragmentBinding.inflate(inflater, container, false)

        setNotLoggedComponent(binding)
        subscribeNotLoggedUi()

        return binding.root
    }

    private fun setNotLoggedComponent(binding: LoginFragmentBinding) = with(binding) {
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

    private fun subscribeNotLoggedUi() = with(viewModel) {
        changeLoginStatus.observe(viewLifecycleOwner) { isLogged ->
            if (isLogged) {
                findNavController().popBackStack()
            } else {
                showDialogWithButtons(
                    R.string.login_error_dialog_title,
                    R.string.login_error_description,
                    R.string.dialog_register,
                    R.string.dialog_forgot_password,
                    positiveButtonClick = {
                        findNavController().navigate(UserFragmentDirections.toRegister())
                    },
                    neutralButtonClick = {
                        findNavController().navigate(UserFragmentDirections.toForgotPassword())
                    }
                )
            }
        }
    }

    private fun isCorrectData(binding: LoginFragmentBinding): Boolean = with(binding) {
        val logicCorrect =
            inputLogin.isValidInput(
                getString(R.string.login_empty),
                getString(R.string.login_to_short)
            )
        val passwordCorrect = inputPassword.isValidInput(
            getString(R.string.password_empty),
            getString(R.string.password_to_short)
        )

        return logicCorrect && passwordCorrect
    }
}
