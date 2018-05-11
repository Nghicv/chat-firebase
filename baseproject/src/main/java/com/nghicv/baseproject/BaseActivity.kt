package com.nghicv.baseproject

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity<VM : BaseViewModel, V : ViewDataBinding> : AppCompatActivity() {

    protected abstract val viewModel: VM
    protected abstract val layoutId: Int @LayoutRes get
    protected lateinit var binding: V
    protected val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutId)
        binding.setVariable(BR.viewModel, viewModel)
    }

    override fun onResume() {
        super.onResume()
        subscriber()
    }

    override fun onPause() {
        unsubscriber()
        super.onPause()
    }

    protected fun unsubscriber() {
        compositeDisposable.clear()
    }

    protected fun bindCall(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    abstract fun subscriber()
}
