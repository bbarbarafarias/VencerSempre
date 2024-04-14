package br.unip.yasaw.presenter.activities.main

import android.app.AlertDialog
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import br.unip.yasaw.R
import br.unip.yasaw.databinding.ActivityMainBinding
import br.unip.yasaw.databinding.AlertConfirmSignOutBinding
import br.unip.yasaw.databinding.AlertCustomBinding
import br.unip.yasaw.databinding.AlertLoadingBinding
import br.unip.yasaw.presenter.base.BaseActivity
import br.unip.yasaw.presenter.fragments.signIn.SignInViewModel
import kotlin.system.exitProcess

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mainViewModel: MainViewModel by viewModels()
    private val signInViewModel: SignInViewModel by viewModels()
    private val navHostFragment: NavHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
    }
    private val navController: NavController by lazy {
        navHostFragment.navController
    }
    private val destinationChangedListener: NavController.OnDestinationChangedListener =
        NavController.OnDestinationChangedListener { _, _, _ ->
            val currentScreen = navController.currentDestination?.id
            if (mainViewModel.getFragmentsWithoutBottomNavigation().contains(currentScreen)) {
                binding.bottomNavigation.visibility = View.GONE
            } else {
                binding.bottomNavigation.visibility = View.VISIBLE
            }
        }
    private var alertDialog: AlertDialog? = null

    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setUpActivity() {
        setUpObservables()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        println("Botão desabilitado.")
    }

    private fun setUpObservables() {
        navController.addOnDestinationChangedListener(destinationChangedListener)
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> {
                    navController.navigate(R.id.action_global_homeFragment)
                }

                R.id.page_2 -> {
                    navController.navigate(R.id.action_global_equipmentsFragment)
                }

                R.id.page_3 -> {
                    navController.navigate(R.id.action_global_reservationsFragment)
                }

                R.id.page_4 -> {
                    navController.navigate(R.id.action_global_helpFragment)
                }
            }
            true
        }
    }

    fun showCustomAlert(title: String, message: String) {
        hideAlert()
        val binding = AlertCustomBinding.inflate(layoutInflater)
        binding.alertTitle.text = title
        binding.alertMessage.text = message
        val alert = AlertDialog.Builder(this, R.style.AlertDialogTheme)
            .setView(binding.root)
            .create()
        alertDialog = alert
        alert.show()
    }

    fun showLoadingAlert() {
        hideAlert()
        val binding = AlertLoadingBinding.inflate(layoutInflater)
        val alert = AlertDialog.Builder(this, R.style.AlertDialogTheme)
            .setView(binding.root)
            .create()
        alertDialog = alert
        alert.show()
    }

    fun showExitAlert() {
        hideAlert()
        val binding = AlertConfirmSignOutBinding.inflate(layoutInflater)
        val alert = AlertDialog.Builder(this, R.style.AlertDialogTheme)
            .setView(binding.root)
            .create()
        binding.cancel.setOnClickListener {
            hideAlert()
        }
        binding.signOut.setOnClickListener {
            hideAlert()
            signInViewModel.signOut()
            exitProcess(0)
        }
        alertDialog = alert
        alert.show()
    }

    fun hideAlert() = alertDialog?.dismiss()

}