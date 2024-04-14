package br.unip.yasaw.presenter.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        val view = binding.root
        setContentView(view)
        setUpActivity()
    }

    abstract fun getViewBinding(): T

    abstract fun setUpActivity()

}