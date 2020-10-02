package com.showcase.cricksquad.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.showcase.cricksquad.R
import com.showcase.cricksquad.ui.common.BaseFragment
import kotlinx.android.synthetic.main.squad_fragment.*

class SquadFragment : BaseFragment() {

    private lateinit var viewModel: SquadViewModel
    lateinit var viewModelFactory: SquadVmFactory


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.squad_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getInjector().inject(this)
        setUpViewModel()
        button.setOnClickListener { viewModel.btnClicked() }
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(SquadViewModel::class.java)
    }

}