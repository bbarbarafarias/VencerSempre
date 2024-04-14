package br.unip.yasaw.presenter.fragments.reservations.set

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import br.unip.yasaw.R
import br.unip.yasaw.data.models.ReservationModel
import br.unip.yasaw.databinding.FragmentSetReservationBinding
import br.unip.yasaw.presenter.activities.main.MainActivity
import br.unip.yasaw.presenter.base.BaseFragment
import br.unip.yasaw.presenter.fragments.equipments.EquipmentsViewModel
import br.unip.yasaw.presenter.fragments.reservations.ReservationsViewModel
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import kotlinx.coroutines.launch

class SetReservationFragment : BaseFragment<FragmentSetReservationBinding>() {

    private val reservationsViewModel: ReservationsViewModel by viewModels()
    private val equipmentsViewModel: EquipmentsViewModel by viewModels()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSetReservationBinding {
        return FragmentSetReservationBinding.inflate(inflater, container, false)
    }

    override fun setUpFragment() {
        setUpObservables()
    }

    private fun setUpObservables() {
        setUpEquipmentsAdapter()
        setUpPeriodsAdapter()
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                reservationsViewModel.setReservationState.collect { state ->
                    when (state) {
                        is ReservationsViewModel.SetReservationState.Idle -> {
                            // TODO: Not yet implemented
                        }

                        is ReservationsViewModel.SetReservationState.Loading -> {
                            (activity as MainActivity).showLoadingAlert()
                        }

                        is ReservationsViewModel.SetReservationState.Success -> {
                            (activity as MainActivity).hideAlert()
                            findNavController().navigate(R.id.action_setReservationFragment_to_reservationsFragment)
                        }

                        is ReservationsViewModel.SetReservationState.Failure -> {
                            (activity as MainActivity).showCustomAlert(
                                getString(R.string.reservations),
                                state.message
                            )
                        }
                    }
                }
            }
        }
        binding.date.setOnClickListener {
            val materialDatePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText(R.string.select_date)
                    .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                    .build()
            materialDatePicker.addOnPositiveButtonClickListener {
                binding.date.setText(materialDatePicker.headerText)
            }
            materialDatePicker.show(childFragmentManager, "DATE_PICKER")
        }
        binding.time.setOnClickListener {
            val materialTimePicker =
                MaterialTimePicker.Builder()
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .setHour(12)
                    .setMinute(10)
                    .setTitleText(R.string.select_time)
                    .build()
            materialTimePicker.addOnPositiveButtonClickListener {
                binding.time.setText("${materialTimePicker.hour}:${materialTimePicker.minute}")
            }
            materialTimePicker.show(childFragmentManager, "TIME_PICKER")
        }
        binding.save.setOnClickListener {
            val reservationModel = ReservationModel(
                binding.equipment.text.toString(),
                binding.course.text.toString(),
                binding.period.text.toString(),
                binding.date.text.toString(),
                binding.time.text.toString()
            )
            reservationsViewModel.addReservation(reservationModel)
        }
        binding.back.setOnClickListener {
            findNavController().navigate(R.id.action_setReservationFragment_to_reservationsFragment)
        }
    }

    private fun setUpEquipmentsAdapter() {
        val items = equipmentsViewModel.getEquipments().map { it.name }
        val equipmentsAdapter = ArrayAdapter(requireContext(), R.layout.item_field, items)
        binding.equipment.setAdapter(equipmentsAdapter)
    }

    private fun setUpPeriodsAdapter() {
        val items = reservationsViewModel.getPeriods()
        val periodsAdapter = ArrayAdapter(requireContext(), R.layout.item_field, items)
        binding.period.setAdapter(periodsAdapter)
    }

}