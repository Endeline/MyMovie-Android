package com.endeline.mymovie.ui.user.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.endeline.mymovie.R
import com.endeline.mymovie.databinding.RegisterFragmentBinding
import com.endeline.mymovie.di.ViewModelFactory
import com.endeline.mymovie.extensions.isSameEditText
import com.endeline.mymovie.extensions.showOkDialog
import com.endeline.mymovie.extensions.validate

class RegisterFragment : DialogFragment() {

    companion object {
        const val REGISTER_STATUS_LIVE_DATA = "register_status"
    }

    private val viewModelFactory: ViewModelFactory.RegisterViewModel =
        ViewModelFactory.RegisterViewModel()

    private val viewModel by viewModels<RegisterViewModel>(factoryProducer = { viewModelFactory })

    private lateinit var binding: RegisterFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegisterFragmentBinding.inflate(inflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupComponent()
        subscribeUi()
    }

    @Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
    private fun subscribeUi() {
        viewModel.getRegisterStatusLiveData().observe(viewLifecycleOwner, Observer {
            when (it) {
                RegisterStatus.OK -> navigateBackWithOkRegisterStatus()
                RegisterStatus.USER_ALREADY_EXIST -> showErrorDialog(R.string.error_user_already_exist)
                RegisterStatus.FAILED -> showErrorDialog(R.string.error_unknown_error)
            }
        })
    }

    private fun setupComponent() = with(binding) {
        confirm.setOnClickListener {
            if (validateFields()) {
                viewModel.registerUser(
                    inputLogin.text.toString(),
                    inputPassword.text.toString()
                )
            }
        }
    }

    private fun validateFields(): Boolean = with(binding) {
        val loginCorrect = inputLogin.validate(
            getString(R.string.login_empty),
            getString(R.string.login_to_short)
        )
        val passwordCorrect = inputPassword.validate(
            getString(R.string.password_empty),
            getString(R.string.password_to_short)
        )
        val confirmPasswordCorrect = inputConfirmPassword.validate(
            getString(R.string.password_empty),
            getString(R.string.password_to_short)
        )
        val isSamePassword = isSameEditText(
            inputPassword,
            inputConfirmPassword,
            getString(R.string.not_same_password)
        )

        return@with loginCorrect && passwordCorrect && confirmPasswordCorrect && isSamePassword
    }

    private fun showErrorDialog(error: Int) = showOkDialog(
        R.string.error_title,
        error
    )

    private fun navigateBackWithOkRegisterStatus() = findNavController().apply {
        previousBackStackEntry?.savedStateHandle?.set(REGISTER_STATUS_LIVE_DATA, true)
        popBackStack()
    }

}
