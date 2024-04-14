package br.unip.yasaw.presenter.adapters.reservations

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.unip.yasaw.data.models.ReservationModel
import br.unip.yasaw.databinding.ItemReservationBinding

class ReservationsAdapter(
    private val reservations: MutableList<ReservationModel>
) : RecyclerView.Adapter<ReservationsAdapter.ReservationViewHolder>() {

    inner class ReservationViewHolder(
        private val binding: ItemReservationBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(reservationModel: ReservationModel) {
            binding.reservationEquipment.text = reservationModel.equipment
            binding.reservationCourse.text = reservationModel.course
            binding.reservationPeriod.text = reservationModel.period
            binding.reservationDate.text = reservationModel.date
            binding.reservationTime.text = reservationModel.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReservationViewHolder {
        val binding =
            ItemReservationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReservationViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return reservations.size
    }

    override fun onBindViewHolder(holder: ReservationViewHolder, position: Int) {
        holder.bind(reservations[position])
    }

}