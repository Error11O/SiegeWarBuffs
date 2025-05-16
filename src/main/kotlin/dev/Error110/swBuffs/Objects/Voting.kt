package dev.Error110.swBuffs.Objects

import java.util.*

public object Voting {
    var voteToggle = true
    var buffName = ""
    var vote : HashMap<UUID, Int> = HashMap<UUID, Int> ()

    fun getVote(id: UUID): Int {
        if (!vote.containsKey(id)) return 0
        return vote.get(id)!!
    }

    fun setVote(id: UUID, voteInt: Int) {
        vote.put(id, voteInt)
    }

    fun delVote(id: UUID): Boolean {
        if (vote.containsKey(id)) {
            vote.remove(id)
            return true
        }
        return false
    }

    fun totalVote(): Item {
        var points = 0
        var mechanics = 0
        var none = 0

        for (value in vote.values) {
            when (value) {
                1 -> points++
                2 -> mechanics++
                3 -> none++
                else -> continue
            }
            //could add a vote logger in future
        }
        when (maxOf(points, mechanics, none)) {
            points -> return Item("points", points)
            mechanics -> return Item("mechanics", mechanics)
            none -> return Item("none", none)
        }
        return Item("none", none)
    }

    fun hasVote(id: UUID): Boolean {
        if (vote.containsKey(id)) return true
        return false
    }
}

data class Item(val title: String, val value: Int)