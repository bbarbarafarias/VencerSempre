package br.unip.yasaw.presenter.fragments.signIn

import br.unip.yasaw.presenter.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SignInViewModel : BaseViewModel() {

    private var firebaseAuth: FirebaseAuth = Firebase.auth
    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState

    sealed class AuthState {

        data object Idle : AuthState()
        data object Loading : AuthState()
        data class Success(val firebaseUser: FirebaseUser) : AuthState()
        data class Failure(val message: String) : AuthState()

    }

    fun signIn(mail: String, password: String) {
        _authState.value =
            AuthState.Loading
        if (mail.isEmpty()) {
            _authState.value =
                AuthState.Failure("Insira seu e-mail.")
            return
        }
        if (password.isEmpty()) {
            _authState.value =
                AuthState.Failure("Insira sua senha.")
            return
        }
        firebaseAuth.signInWithEmailAndPassword(mail, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    _authState.value =
                        AuthState.Success(firebaseAuth.currentUser!!)
                } else {
                    _authState.value =
                        AuthState.Failure(task.exception?.message ?: "Unknown error")
                }
            }
    }

    fun signOut() = firebaseAuth.signOut()

}