package com.emikhalets.simpleweather.ui.screens.search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emikhalets.simpleweather.R
import com.emikhalets.simpleweather.data.database.SearchDBEntity
import com.emikhalets.simpleweather.ui.theme.AppTheme
import com.emikhalets.simpleweather.ui.theme.inactiveBackground
import com.emikhalets.simpleweather.ui.theme.textFieldBackground
import com.emikhalets.simpleweather.utils.activeBackground
import com.emikhalets.simpleweather.utils.appSurface
import com.emikhalets.simpleweather.utils.previewSearchScreenLocationList

@Composable
fun SearchScreen() {
    var searchQuery by remember { mutableStateOf("") }
    var selectedPos by remember { mutableStateOf(-1) }

    SearchScreen(
        searchQuery = searchQuery,
        locationList = emptyList(),
        selectedPos = selectedPos,
        onSearchQueryChange = { newQuery -> searchQuery = newQuery },
        onSelectPosChange = { newPos -> selectedPos = newPos }
    )
}

@Composable
fun SearchScreen(
    searchQuery: String,
    locationList: List<SearchDBEntity>,
    selectedPos: Int,
    onSearchQueryChange: (String) -> Unit,
    onSelectPosChange: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .appSurface()
    ) {
        SearchScreenHeader(
            searchQuery = searchQuery,
            onSearchQueryChange = onSearchQueryChange,
            onLocationClick = { /*TODO*/ }
        )
        SearchScreenResultGrid(
            locationList = locationList,
            selectedPos = selectedPos,
            onSelectPosChange = onSelectPosChange
        )
    }
}

@Composable
fun SearchScreenHeader(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    onLocationClick: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp, 16.dp)
    ) {
        Spacer(modifier = Modifier.height(52.dp))
        Text(
            text = stringResource(id = R.string.search_title),
            color = MaterialTheme.colors.primary,
            fontSize = 28.sp,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.search_description),
            color = MaterialTheme.colors.secondary,
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            lineHeight = 28.sp
        )
        Spacer(modifier = Modifier.height(40.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
        ) {
            TextField(
                value = searchQuery,
                onValueChange = onSearchQueryChange,
                placeholder = {
                    Text(
                        text = stringResource(id = R.string.search_location_hint),
                        fontSize = 16.sp
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    textColor = MaterialTheme.colors.primary,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    leadingIconColor = MaterialTheme.colors.primary,
                    backgroundColor = MaterialTheme.colors.textFieldBackground,
                    placeholderColor = MaterialTheme.colors.secondary,
                ),
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = { onLocationClick() },
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .background(
                        color = MaterialTheme.colors.textFieldBackground,
                        shape = MaterialTheme.shapes.medium
                    )
                    .clip(MaterialTheme.shapes.medium)
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary
                )
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SearchScreenResultGrid(
    locationList: List<SearchDBEntity>,
    selectedPos: Int,
    onSelectPosChange: (Int) -> Unit,
) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        itemsIndexed(locationList) { index, entity ->
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .background(
                        color = MaterialTheme.colors.inactiveBackground,
                        shape = MaterialTheme.shapes.medium
                    )
                    .activeBackground(index == selectedPos)
                    .padding(16.dp, 8.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .clickable { onSelectPosChange(index) }
            ) {
                Text(
                    text = entity.name,
                    color = MaterialTheme.colors.primary,
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = entity.region,
                    color = MaterialTheme.colors.primary,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = entity.country,
                    color = MaterialTheme.colors.primary,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    AppTheme {
        SearchScreen(
            searchQuery = "London",
            locationList = previewSearchScreenLocationList(),
            selectedPos = 0,
            onSearchQueryChange = {},
            onSelectPosChange = {},
        )
    }
}