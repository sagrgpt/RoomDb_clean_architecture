package com.showcase.cricksquad.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.showcase.cricksquad.Player
import com.showcase.cricksquad.R
import kotlinx.android.synthetic.main.profile_fragment.*

class ProfileBottomSheet(
    private val info: ProfileViewState
) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.profile_fragment,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        playerName.text = info.name
        battingStyle.text = info.battingStyle
        battingAvg.text = "${info.battingAvg}"
        strikeRate.text = "${info.strikeRate}"
        runs.text = "${info.runs}"
        teamName.text = info.teamName
        bowlingStyle.text = info.bowlingStyle
        bowlingAvg.text = "${info.bowlingAvg}"
        economyRate.text = "${info.economyRate}"
        wickets.text = "${info.wickets}"

        setProfilePic()
    }

    override fun getTheme() = R.style.BaseBottomSheetDialog

    private fun setProfilePic() {
        if (info.teamId.rem(2) == 0L)
            profilePic?.setDrawable(R.drawable.ic_player_a)
        else
            profilePic?.setDrawable(R.drawable.ic_player_b)
    }

    private fun ImageView.setDrawable(id: Int) {
        setImageDrawable(ContextCompat.getDrawable(context, id))
    }
}