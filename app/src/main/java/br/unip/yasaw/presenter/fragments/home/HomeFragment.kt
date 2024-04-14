package br.unip.yasaw.presenter.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import br.unip.yasaw.databinding.FragmentHomeBinding
import br.unip.yasaw.presenter.activities.main.MainActivity
import br.unip.yasaw.presenter.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun setUpFragment() {
        setUpObservables()
    }

    private fun setUpObservables() {
        binding.signOut.setOnClickListener {
            (activity as MainActivity).showExitAlert()
        }
    }

}