package br.unip.yasaw.presenter.fragments.splash

import android.os.Handler
import android.os.Looper
import androidx.navigation.NavController
import br.unip.yasaw.R
import br.unip.yasaw.presenter.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SplashViewModel : BaseViewModel() {

    private var firebaseAuth: FirebaseAuth = Firebase.auth

    private fun getCurrentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    fun verifyAuthentication(navController: NavController) {
        Handler(Looper.getMainLooper()).postDelayed({
            if (getCurrentUser() == null) {
                navController.navigate(R.id.action_splashFragment_to_signInFragment)
            } else {
                navController.navigate(R.id.action_splashFragment_to_nav_home)
            }
        }, 3000)
    }

}