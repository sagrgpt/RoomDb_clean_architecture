package com.showcase.cricksquad.ui.squads

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.showcase.cricksquad.Player
import com.showcase.cricksquad.R
import com.showcase.cricksquad.ui.HomeActivity
import kotlinx.android.synthetic.main.home_fragment.*

class SquadFragment : Fragment() {

    private val itemClickListener: (Player) -> Unit = { (activity as HomeActivity).showPlayerProfile(it.id) }
    private lateinit var listOfPlayers: List<Player>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listOfPlayers = arguments
            ?.getParcelableArrayList<Player>(PLAYER_LIST)
            ?.toList() ?: emptyList()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpList()
    }

    private fun setUpList() {
        LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        ).also { playerListRecycler.layoutManager = it }

        DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            .also { playerListRecycler.addItemDecoration(it) }

        playerListRecycler.adapter = SquadListAdapter(
            listOfPlayers,
            itemClickListener
        )
    }

    companion object {
        private const val PLAYER_LIST = "player_list"
        fun getInstance(players: List<Player>): SquadFragment {
            val fragment = SquadFragment()
            val args = Bundle()
            args.putParcelableArrayList(PLAYER_LIST, ArrayList(players))
            fragment.arguments = args
            return fragment
        }
    }

}