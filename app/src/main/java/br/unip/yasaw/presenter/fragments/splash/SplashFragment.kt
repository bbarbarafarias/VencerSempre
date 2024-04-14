package br.unip.yasaw.presenter.fragments.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import br.unip.yasaw.databinding.FragmentSplashBinding
import br.unip.yasaw.presenter.base.BaseFragment

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    private val splashViewModel: SplashViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSplashBinding {
        return FragmentSplashBinding.inflate(inflater, container, false)
    }

    override fun setUpFragment() {
        setUpObservables()
    }

    private fun setUpObservables() {
        splashViewModel.verifyAuthentication(findNavController())
    }

}