package br.unip.yasaw.presenter.activities.main

import br.unip.yasaw.R
import br.unip.yasaw.presenter.base.BaseViewModel

class MainViewModel : BaseViewModel() {

    fun getFragmentsWithoutBottomNavigation(): MutableList<Int> {
        return mutableListOf(
            R.id.splashFragment,
            R.id.signInFragment,
            R.id.signUpFragment,
            R.id.setReservationFragment
        )
    }

}