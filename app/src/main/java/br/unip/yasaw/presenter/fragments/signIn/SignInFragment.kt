package br.unip.yasaw.presenter.fragments.signIn

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import br.unip.yasaw.R
import br.unip.yasaw.databinding.FragmentSignInBinding
import br.unip.yasaw.presenter.activities.main.MainActivity
import br.unip.yasaw.presenter.base.BaseFragment
import kotlinx.coroutines.launch

class SignInFragment : BaseFragment<FragmentSignInBinding>() {

    private val signInViewModel: SignInViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSignInBinding {
        return FragmentSignInBinding.inflate(inflater, container, false)
    }

    override fun setUpFragment() {
        setUpObservables()
    }

    private fun setUpObservables() {
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                signInViewModel.authState.collect { state ->
                    when (state) {
                        is SignInViewModel.AuthState.Idle -> {
                            // TODO: Not yet implemented
                        }

                        is SignInViewModel.AuthState.Loading -> {
                            (activity as MainActivity).showLoadingAlert()
                        }

                        is SignInViewModel.AuthState.Success -> {
                            (activity as MainActivity).hideAlert()
                            findNavController().navigate(R.id.action_signInFragment_to_nav_home)
                        }

                        is SignInViewModel.AuthState.Failure -> {
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
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
        binding.signIn.setOnClickListener {
            signInViewModel.signIn(
                binding.mail.text.toString(),
                binding.password.text.toString()
            )
        }
    }

}