package com.emikhalets.simpleweather.ui.screens.search

import com.emikhalets.simpleweather.data.database.SearchDBEntity
import com.emikhalets.simpleweather.utils.UiString

data class SearchScreenState(
    val locations: List<SearchDBEntity> = emptyList(),
    val error: UiString = UiString.DynamicString("")
)