package com.endeline.movielibrary.ui.gui.user.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.endeline.movielibrary.R
import com.endeline.movielibrary.databinding.RegisterFragmentBinding
import com.endeline.movielibrary.di.factory.ViewModelProviderFactory
import com.endeline.movielibrary.ui.extensions.isSameEditText
import com.endeline.movielibrary.ui.extensions.isValidInput
import com.endeline.movielibrary.ui.extensions.showOkDialog
import dagger.android.support.DaggerDialogFragment
import javax.inject.Inject

class RegisterFragment : DaggerDialogFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    private val viewModel by viewModels<RegisterViewModel> {
        viewModelFactory
    }

    private var _binding: RegisterFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegisterFragmentBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setComponents()
        subscribeUi()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setComponents() = with(binding) {
        confirm.setOnClickListener {
            if (validateFields()) {
                viewModel.registerUser(
                    inputLogin.text.toString(),
                    inputPassword.text.toString()
                )
            }
        }
    }

    @Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")
    private fun subscribeUi() = with(viewModel) {
        registerStatus.observe(viewLifecycleOwner) { status ->
            when (status) {
                RegisterStatus.OK -> navigateBackWithOkRegisterStatus()
                RegisterStatus.USER_ALREADY_EXIST -> showErrorDialog(R.string.error_user_already_exist)
                RegisterStatus.FAILED -> showErrorDialog(R.string.error_unknown_error)
            }
        }
    }

    private fun validateFields(): Boolean = with(binding) {
        val loginCorrect = inputLogin.isValidInput(
            getString(R.string.login_empty),
            getString(R.string.login_to_short)
        )
        val passwordCorrect = inputPassword.isValidInput(
            getString(R.string.password_empty),
            getString(R.string.password_to_short)
        )
        val confirmPasswordCorrect = inputConfirmPassword.isValidInput(
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

    companion object {
        const val REGISTER_STATUS_LIVE_DATA = "register_status"
    }
}
