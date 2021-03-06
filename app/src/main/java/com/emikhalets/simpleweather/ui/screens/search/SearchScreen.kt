package com.emikhalets.simpleweather.ui.screens.search

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.emikhalets.simpleweather.App.Companion.prefs
import com.emikhalets.simpleweather.R
import com.emikhalets.simpleweather.data.database.SearchDBEntity
import com.emikhalets.simpleweather.ui.theme.AppTheme
import com.emikhalets.simpleweather.ui.theme.activeBackground
import com.emikhalets.simpleweather.ui.theme.inactiveBackground
import com.emikhalets.simpleweather.ui.theme.textFieldBackground
import com.emikhalets.simpleweather.utils.LocationHelper
import com.emikhalets.simpleweather.utils.extensions.appSurface
import com.emikhalets.simpleweather.utils.extensions.coords
import com.emikhalets.simpleweather.utils.extensions.previewSearchScreenLocationList
import com.emikhalets.simpleweather.utils.extensions.showSnackBar
import com.emikhalets.simpleweather.utils.locationPermission
import com.emikhalets.simpleweather.utils.locationSettings
import com.emikhalets.simpleweather.utils.requestLocationPermissions
import com.emikhalets.simpleweather.utils.requestLocationSettings

// TODO:
// FIXME: odd last location count empty place background
@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    scaffoldState: ScaffoldState,
    locationHelper: LocationHelper?,
) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedPos by remember { mutableStateOf(-1) }
    var searchingState by remember { mutableStateOf(false) }

    val state = viewModel.state
    val context = LocalContext.current

    fun currentLocation() = locationHelper?.getCurrentLocation(
        onSuccess = { location ->
            prefs?.putCurrentLocation(location.coords)
            viewModel.addLocation(location.latitude, location.longitude)
            selectedPos = 0
        },
        onFailure = { scaffoldState.showSnackBar(context, R.string.location_current_error) }
    )

    val locationSettings = locationSettings(
        onEnabled = { currentLocation() },
        onDisabled = { scaffoldState.showSnackBar(context, R.string.location_need_turn_on_gps) }
    )

    val locationPermission = locationPermission(
        onEnabled = {
            LocationHelper.checkLocationSettings(
                context = context,
                onEnabled = { currentLocation() },
                onDisabled = { intent -> locationSettings.requestLocationSettings(intent) }
            )
        },
        onDisabled = { scaffoldState.showSnackBar(context, R.string.location_no_permissions) }
    )

    LaunchedEffect("") { viewModel.getLocations() }
    LaunchedEffect(state.error) { scaffoldState.showSnackBar(state.error.asString(context)) }

    SearchScreen(
        searchQuery = searchQuery,
        locationList = state.locations,
        selectedPos = selectedPos,
        onSearchQueryChange = { newQuery ->
            selectedPos = -1
            searchQuery = newQuery
            searchingState = searchQuery.isNotEmpty()
            viewModel.getLocations(searchQuery)
        },
        onSelectPosChange = { newPos ->
            if (selectedPos == newPos) {
                if (searchingState) {
                    viewModel.addLocation(selectedPos)
                    searchingState = false
                } else {
                    viewModel.saveLocationPref(selectedPos)
                }
            }
            selectedPos = newPos
        },
        onLocationClick = {
            locationPermission.requestLocationPermissions()
        }
    )
}

@Composable
fun SearchScreen(
    searchQuery: String,
    locationList: List<SearchDBEntity>,
    selectedPos: Int,
    onSearchQueryChange: (String) -> Unit,
    onSelectPosChange: (Int) -> Unit,
    onLocationClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .appSurface()
            .verticalScroll(rememberScrollState())
    ) {
        SearchScreenHeader(
            searchQuery = searchQuery,
            onSearchQueryChange = onSearchQueryChange,
            onLocationClick = onLocationClick
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
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
    ) {
        for (i in locationList.indices step 2) {
            Row(
                modifier = Modifier.height(IntrinsicSize.Min)
            ) {
                SearchScreenLocationItem(
                    index = i,
                    entity = locationList[i],
                    isActive = selectedPos == i,
                    onSelectPosChange = onSelectPosChange,
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f)
                )
                if (locationList.size % 2 == 0) {
                    SearchScreenLocationItem(
                        index = i + 1,
                        entity = locationList[i + 1],
                        isActive = selectedPos == i + 1,
                        onSelectPosChange = onSelectPosChange,
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    )
                } else {
                    SearchScreenLocationItem(
                        index = -1,
                        entity = SearchDBEntity(),
                        isActive = false,
                        onSelectPosChange = {},
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun SearchScreenLocationItem(
    index: Int,
    entity: SearchDBEntity,
    isActive: Boolean,
    onSelectPosChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val indicationSource = remember { MutableInteractionSource() }

    val animatedColor by animateColorAsState(
        targetValue = if (isActive) MaterialTheme.colors.activeBackground
        else MaterialTheme.colors.inactiveBackground,
        animationSpec = tween(durationMillis = 300)
    )

    Column(
        modifier = modifier
            .padding(8.dp)
            .background(
                color = animatedColor,
                shape = MaterialTheme.shapes.medium
            )
            .padding(16.dp, 8.dp)
            .clip(MaterialTheme.shapes.medium)
            .clickable(
                interactionSource = indicationSource,
                indication = null
            ) { onSelectPosChange(index) }
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
            onLocationClick = {},
        )
    }
}