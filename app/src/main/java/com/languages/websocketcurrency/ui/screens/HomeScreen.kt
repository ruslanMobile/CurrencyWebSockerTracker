package com.languages.websocketcurrency.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.runtime.setValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.languages.websocketcurrency.R

@Composable
fun HomeScreen() {
    val viewModel: HomeVM = hiltViewModel()
    val authState = viewModel.authState.collectAsStateWithLifecycle()
    val instrumentsState = viewModel.instrumentsState.collectAsStateWithLifecycle()
    HomeContent(
        authState.value
    )
}

@Composable
fun HomeContent(authStatus: AuthState) {
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(top = 50.dp)
        ) {
            Text(
                text = stringResource(id = R.string.label_auth_status, authStatus.message),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(3.dp)
                    .fillMaxWidth()
                    .background(Color.LightGray),
            )

            DropdownList(
                itemList = listOf("Ruslan", "Ivan", "Stepan"),
                selectedIndex = 2,
                modifier = Modifier,
                onItemClick = {})
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    HomeContent(AuthState.Authorized)
}

@Composable
fun DropdownList(
    itemList: List<String>,
    selectedIndex: Int,
    modifier: Modifier,
    onItemClick: (Int) -> Unit
) {

    var showDropdown by rememberSaveable { mutableStateOf(true) }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // button
        Box(
            modifier = modifier
                .background(Color.Red)
                .clickable { showDropdown = true },
//            .clickable { showDropdown = !showDropdown },
            contentAlignment = Alignment.Center
        ) {
            Text(text = itemList[selectedIndex], modifier = Modifier.padding(3.dp))
        }

        // dropdown list
        Box() {
            if (showDropdown) {
                Popup(
                    alignment = Alignment.TopCenter,
                    properties = PopupProperties(
                        excludeFromSystemGesture = true,
                    ),
                    // to dismiss on click outside
                    onDismissRequest = { showDropdown = false }
                ) {

                    Column(
                        modifier = modifier
                            .heightIn(max = 90.dp)
                            .verticalScroll(state = scrollState)
                            .border(width = 1.dp, color = Color.Gray),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        itemList.onEachIndexed { index, item ->
                            if (index != 0) {
                                Divider(thickness = 1.dp, color = Color.LightGray)
                            }
                            Box(
                                modifier = Modifier
                                    .background(Color.Green)
                                    .fillMaxWidth()
                                    .clickable {
                                        onItemClick(index)
                                        showDropdown = !showDropdown
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(text = item)
                            }
                        }

                    }
                }
            }
        }
    }

}
