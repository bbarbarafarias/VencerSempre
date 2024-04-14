package br.unip.yasaw.presenter.fragments.reservations

import br.unip.yasaw.data.models.ReservationModel
import br.unip.yasaw.presenter.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ReservationsViewModel : BaseViewModel() {

    private var firebaseAuth: FirebaseAuth = Firebase.auth
    private val _reservationsState =
        MutableStateFlow<ReservationsState>(ReservationsState.Idle)
    val reservationsState: StateFlow<ReservationsState> = _reservationsState
    private val _setReservationState =
        MutableStateFlow<SetReservationState>(SetReservationState.Idle)
    val setReservationState: StateFlow<SetReservationState> = _setReservationState
    private val _delReservationState =
        MutableStateFlow<DelReservationState>(DelReservationState.Idle)
    val delReservationState: StateFlow<DelReservationState> = _delReservationState

    sealed class ReservationsState {

        data object Idle : ReservationsState()
        data object Loading : ReservationsState()
        data class Success(val reservations: MutableList<ReservationModel>) : ReservationsState()
        data class Failure(val message: String) : ReservationsState()

    }

    sealed class SetReservationState {

        data object Idle : SetReservationState()
        data object Loading : SetReservationState()
        data class Success(val message: String) : SetReservationState()
        data class Failure(val message: String) : SetReservationState()

    }

    sealed class DelReservationState {

        data object Idle : DelReservationState()
        data object Loading : DelReservationState()
        data class Success(val message: String) : DelReservationState()
        data class Failure(val message: String) : DelReservationState()

    }

    fun addReservation(reservationModel: ReservationModel) {
        _setReservationState.value =
            SetReservationState.Loading
        if (reservationModel.equipment.isEmpty()) {
            _setReservationState.value =
                SetReservationState.Failure("Equipamento não informado.")
            return
        }
        if (reservationModel.course.isEmpty()) {
            _setReservationState.value =
                SetReservationState.Failure("Curso não informado.")
            return
        }
        if (reservationModel.period.isEmpty()) {
            _setReservationState.value =
                SetReservationState.Failure("Período não informado.")
            return
        }
        if (reservationModel.date.isEmpty()) {
            _setReservationState.value =
                SetReservationState.Failure("Data não informada.")
            return
        }
        if (reservationModel.time.isEmpty()) {
            _setReservationState.value =
                SetReservationState.Failure("Horário não informado.")
            return
        }
        val uid = firebaseAuth.currentUser?.uid
        if (uid == null) {
            _setReservationState.value =
                SetReservationState.Failure("Usuário não autenticado.")
            return
        }
        val database = FirebaseDatabase.getInstance()
        val reference = database.reference.child("users").child(uid).child("reservations")
        val key = reference.push().key
        if (key == null) {
            _setReservationState.value =
                SetReservationState.Failure("Erro ao gerar chave para reserva.")
            return
        }
        val child = reference.child(key)
        child.setValue(reservationModel)
            .addOnSuccessListener {
                _setReservationState.value =
                    SetReservationState.Success("Reserva adicionada com sucesso.")
            }
            .addOnFailureListener { error ->
                _setReservationState.value =
                    SetReservationState.Failure(error.message ?: "Erro ao adicionar reserva.")
            }
    }

    fun delReservation(reservationModel: ReservationModel) {
        _delReservationState.value =
            DelReservationState.Loading
        val uid = firebaseAuth.currentUser?.uid
        if (uid == null) {
            _delReservationState.value =
                DelReservationState.Failure("Usuário não autenticado.")
            return
        }
        val database = FirebaseDatabase.getInstance()
        val reference = database.reference.child("users").child(uid).child("reservations")
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val reservation = snapshot.getValue(ReservationModel::class.java)
                    if (reservation == reservationModel) {
                        snapshot.ref.removeValue()
                            .addOnSuccessListener {
                                _delReservationState.value =
                                    DelReservationState.Success("Reserva removida com sucesso.")
                            }
                            .addOnFailureListener { error ->
                                _delReservationState.value =
                                    DelReservationState.Failure(
                                        error.message ?: "Erro ao remover reserva."
                                    )
                            }
                        return
                    }
                }
                _delReservationState.value =
                    DelReservationState.Failure("Reserva não encontrada.")
            }

            override fun onCancelled(error: DatabaseError) {
                _delReservationState.value =
                    DelReservationState.Failure(error.message)
            }
        })
    }

    fun getReservations() {
        _reservationsState.value =
            ReservationsState.Loading
        val uid = firebaseAuth.currentUser?.uid
        if (uid == null) {
            _reservationsState.value =
                ReservationsState.Failure("Usuário não autenticado.")
            return
        }
        val database = FirebaseDatabase.getInstance()
        val reference = database.reference.child("users").child(uid).child("reservations")
        reference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val reservations = mutableListOf<ReservationModel>()
                for (snapshot in dataSnapshot.children) {
                    val reservation = snapshot.getValue(ReservationModel::class.java)
                    reservation?.let {
                        reservations.add(it)
                    }
                }
                _reservationsState.value =
                    ReservationsState.Success(reservations)
            }

            override fun onCancelled(error: DatabaseError) {
                _reservationsState.value =
                    ReservationsState.Failure(error.message)
            }
        })
    }

    fun getPeriods(): MutableList<String> {
        return mutableListOf("Manhã", "Tarde", "Noite")
    }

}