package br.unip.yasaw.presenter.fragments.reservations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import br.unip.yasaw.R
import br.unip.yasaw.databinding.FragmentReservationsBinding
import br.unip.yasaw.presenter.activities.main.MainActivity
import br.unip.yasaw.presenter.adapters.reservations.ReservationsAdapter
import br.unip.yasaw.presenter.base.BaseFragment
import kotlinx.coroutines.launch

class ReservationsFragment : BaseFragment<FragmentReservationsBinding>() {

    private val reservationsViewModel: ReservationsViewModel by viewModels()
    private lateinit var reservationsAdapter: ReservationsAdapter

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentReservationsBinding {
        return FragmentReservationsBinding.inflate(inflater, container, false)
    }

    override fun setUpFragment() {
        setUpObservables()
    }

    private fun setUpObservables() {
        setUpAdapter()
    }

    private fun setUpAdapter() {
        reservationsViewModel.getReservations()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                reservationsViewModel.reservationsState.collect { state ->
                    when (state) {
                        is ReservationsViewModel.ReservationsState.Idle -> {
                            // TODO: Not yet implemented
                        }

                        is ReservationsViewModel.ReservationsState.Loading -> {
                            (activity as MainActivity).showLoadingAlert()
                        }

                        is ReservationsViewModel.ReservationsState.Success -> {
                            (activity as MainActivity).hideAlert()
                            if (state.reservations.isEmpty()) {
                                binding.reservations.visibility = View.GONE
                                binding.noReservations.visibility = View.VISIBLE
                            } else {
                                reservationsAdapter = ReservationsAdapter(state.reservations)
                                binding.reservations.apply {
                                    adapter = reservationsAdapter
                                    visibility = View.VISIBLE
                                }
                                binding.noReservations.visibility = View.GONE
                            }
                        }

                        is ReservationsViewModel.ReservationsState.Failure -> {
                            (activity as MainActivity).showCustomAlert(
                                getString(R.string.reservations),
                                state.message
                            )
                        }
                    }
                }
            }
        }
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_reservationsFragment_to_setReservationFragment)
        }
    }

}