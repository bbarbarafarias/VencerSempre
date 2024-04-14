package br.unip.yasaw.presenter.fragments.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import br.unip.yasaw.databinding.FragmentHelpBinding
import br.unip.yasaw.presenter.base.BaseFragment

class HelpFragment : BaseFragment<FragmentHelpBinding>() {

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHelpBinding {
        return FragmentHelpBinding.inflate(inflater, container, false)
    }

    override fun setUpFragment() {
        // TODO: Not yet implemented
    }

}