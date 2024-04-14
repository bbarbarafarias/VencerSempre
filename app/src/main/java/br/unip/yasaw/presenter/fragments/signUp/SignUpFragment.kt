package br.unip.yasaw.presenter.fragments.signUp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import br.unip.yasaw.R
import br.unip.yasaw.databinding.FragmentSignUpBinding
import br.unip.yasaw.presenter.activities.main.MainActivity
import br.unip.yasaw.presenter.base.BaseFragment
import kotlinx.coroutines.launch

class SignUpFragment : BaseFragment<FragmentSignUpBinding>() {

    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSignUpBinding {
        return FragmentSignUpBinding.inflate(inflater, container, false)
    }

    override fun setUpFragment() {
        setUpObservables()
    }

    private fun setUpObservables() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                signUpViewModel.authState.collect { state ->
                    when (state) {
                        is SignUpViewModel.AuthState.Idle -> {
                            // TODO: Not yet implemented
                        }

                        is SignUpViewModel.AuthState.Loading -> {
                            (activity as MainActivity).showLoadingAlert()
                        }

                        is SignUpViewModel.AuthState.Success -> {
                            (activity as MainActivity).hideAlert()
                            findNavController().navigate(R.id.action_signUpFragment_to_nav_home)
                        }

                        is SignUpViewModel.AuthState.Failure -> {
                            (activity as MainActivity).showCustomAlert(
                                getString(R.string.authentication),
                                state.message
                            )
                        }
                    }
                }
            }
        }
        binding.signUp.setOnClickListener {
            signUpViewModel.signUp(
                binding.mail.text.toString(),
                binding.password.text.toString(),
                binding.confirmPassword.text.toString()
            )
        }
        binding.signIn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}