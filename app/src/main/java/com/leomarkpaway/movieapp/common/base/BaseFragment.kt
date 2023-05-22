package com.leomarkpaway.movieapp.common.base

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import java.lang.reflect.ParameterizedType

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel> : Fragment() {

    lateinit var binding: VB
    lateinit var viewModel: VM
    private val type = (javaClass.genericSuperclass as ParameterizedType)
    private val classVB = type.actualTypeArguments[0] as Class<VB>
    private val classVM = type.actualTypeArguments[1] as Class<VM>

    private val inflateMethod = classVB.getMethod(
        "inflate",
        LayoutInflater::class.java,
        ViewGroup::class.java,
        Boolean::class.java
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = inflateMethod.invoke(null, inflater, container, false) as VB
        viewModel =  ViewModelProvider(requireActivity())[classVM]
        initViews()
        subscribe()
        return binding.root
    }

    fun hideSystemUI() {
        requireActivity().window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        requireActivity().window.statusBarColor = Color.TRANSPARENT
    }

    fun showSystemUI() {
        requireActivity().window.decorView.systemUiVisibility
    }

    fun onBackPress(closeActivity: Boolean = false,disableBack: Boolean = false, block: () -> Unit) {
        requireActivity().onBackPressedDispatcher.addCallback(this, object :
            OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (closeActivity) requireActivity().finish()

                if (isEnabled && !closeActivity && !disableBack) {
                    isEnabled = false
                    requireActivity().onBackPressed()
                }
                block()
            }
        })
    }

    open fun initViews() {}

    open fun subscribe() {}
}