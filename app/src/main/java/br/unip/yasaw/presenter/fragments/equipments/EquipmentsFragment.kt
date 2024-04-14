package br.unip.yasaw.presenter.fragments.equipments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import br.unip.yasaw.databinding.FragmentEquipmentsBinding
import br.unip.yasaw.presenter.adapters.products.EquipmentsAdapter
import br.unip.yasaw.presenter.base.BaseFragment

class EquipmentsFragment : BaseFragment<FragmentEquipmentsBinding>() {

    private val equipmentsViewModel: EquipmentsViewModel by viewModels()
    private lateinit var equipmentsAdapter: EquipmentsAdapter

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentEquipmentsBinding {
        return FragmentEquipmentsBinding.inflate(inflater, container, false)
    }

    override fun setUpFragment() {
        equipmentsAdapter = EquipmentsAdapter(equipmentsViewModel.getEquipments())
        binding.equipments.apply {
            adapter = equipmentsAdapter
        }
    }

}