package com.showcase.cricksquad.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.showcase.cricksquad.R
import com.showcase.cricksquad.Team
import com.showcase.cricksquad.ui.common.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class HomeActivity : BaseActivity() {

    private lateinit var viewModel: HomeViewModel
    lateinit var viewModelFactory: HomeVmFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Squad List"
        getInjector().inject(this)
        setUpViewModel()
        viewModel.init()
    }

    fun showPlayerProfile(id: Long) {
        viewModel.viewProfile(id)
    }

    private fun setUpViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(HomeViewModel::class.java)

        viewModel.team()
            .observe(
                this,
                {
                    title = "${it[0].shortName} vs ${it[1].shortName}"
                    setUpViewPager(it)
                }
            )

        viewModel.profile()
            .observe(
                this,
                { ProfileBottomSheet(it).show(supportFragmentManager, "Player profile") }
            )
    }

    private fun setUpViewPager(teamList: List<Team>) {
        viewPager.adapter = ViewPagerAdapter(this, teamList)

        TabLayoutMediator(tabLayout, viewPager
        ) { tab, position ->
            tab.text = teamList[position].fullName
        }.attach()
    }

}