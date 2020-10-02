package com.showcase.cricksquad.ui.squads

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.showcase.cricksquad.Player
import com.showcase.cricksquad.R

class SquadListAdapter(
    private var dataSet: List<Player>,
    private val listener: (Player) -> Unit
) : RecyclerView.Adapter<SquadListAdapter.SquadListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SquadListViewHolder {
        return SquadListViewHolder(parent.inflate(R.layout.player_row))
    }

    override fun onBindViewHolder(holder: SquadListViewHolder, position: Int) {
        holder.bindData(dataSet[position], listener)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun addToDataSet(players: List<Player>) {
        dataSet = players
        notifyDataSetChanged()
    }

    class SquadListViewHolder(
        itemView: View
    ): RecyclerView.ViewHolder(itemView) {

        private val name = itemView.findViewById<TextView>(R.id.playerName)
        private val captainBadge = itemView.findViewById<ImageView>(R.id.captainBadge)
        private val keeperBadge = itemView.findViewById<ImageView>(R.id.keeperBadge)
        private val container = itemView.findViewById<ConstraintLayout>(R.id.playerRow)
        private val profilePic = itemView.findViewById<ImageView>(R.id.icon)

        fun bindData(
            item: Player,
            listener: (Player) -> Unit
        ) {
            setName(item)
            setBadge(item)
            setProfilePic(item)
            container.setOnClickListener { listener(item) }
        }

        private fun setName(item: Player) {
            name?.text = item.name
        }

        private fun setBadge(item: Player) {
            if(item.isCaptain)
                captainBadge?.visibility = View.VISIBLE
             else
                captainBadge?.visibility = View.GONE
            if(item.isKeeper)
                keeperBadge?.visibility = View.VISIBLE
            else
                keeperBadge?.visibility = View.GONE
        }

        private fun setProfilePic(item: Player) {
            if(item.teamId.rem(2) == 0L)
                profilePic?.setDrawable(R.drawable.ic_player_a)
            else
                profilePic?.setDrawable(R.drawable.ic_player_b)
        }

        private fun ImageView.setDrawable(id: Int) {
            setImageDrawable(ContextCompat.getDrawable(context, id))
        }

    }
}

private fun ViewGroup.inflate(
    @LayoutRes layoutRes: Int,
    attachToRoot: Boolean = false
): View = LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)
