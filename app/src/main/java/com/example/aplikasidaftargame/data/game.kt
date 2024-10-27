package com.example.aplikasidaftargame.data


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.aplikasidaftargame.R



data class Game(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    val mb: Int,
    @StringRes val hobbies: Int
)


val games = listOf(
    Game(R.drawable.freefire, R.string.name_1, 1530, R.string.description_1),
    Game(R.drawable.ml, R.string.name_2, 3023, R.string.description_2),
    Game(R.drawable.lk, R.string.name_3, 348, R.string.description_3),
    Game(R.drawable.bl, R.string.name_4, 437, R.string.description_4),
    Game(R.drawable.ss, R.string.name_5, 892, R.string.description_5),
    Game(R.drawable.pubg, R.string.name_6, 4820, R.string.description_6),
    Game(R.drawable.hok, R.string.name_7, 2783, R.string.description_7),
    Game(R.drawable.coc, R.string.name_8, 2394, R.string.description_8),
    Game(R.drawable.cr, R.string.name_9, 1583, R.string.description_9)
)