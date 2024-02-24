package br.com.dmcconsulting.designpattern.features.home.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.dmcconsulting.designpattern.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel()
) {
    val homeStateUI by homeViewModel.uiState.collectAsState()
    val items by homeStateUI.items.collectAsState(initial = emptyList())

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Workflow Discount")
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = {
                    Text(text = stringResource(id = R.string.add_item))
                },
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(id = R.string.add_item)
                    )
                },
                onClick = {
                    showBottomSheet = true
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            LazyColumn {
                items(
                    items = items,
                    key = { it.id }
                ) { item ->
                    ItemRow(
                        itemName = item.name,
                        onApplyDiscount = homeViewModel::applyDiscount
                    )
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        thickness = 1.dp
                    )
                }
            }

            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState
                ) {
                    BottomSheetContent(
                        onAddItem = { itemName ->
                            homeViewModel.addItem(itemName)
                        },
                        onDismiss = {
                            scope.launch {
                                sheetState.hide()
                            }.invokeOnCompletion {
                                if (!sheetState.isVisible) {
                                    showBottomSheet = false
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun BottomSheetContent(
    onAddItem: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val bottomPadding = WindowInsets
        .navigationBars
        .asPaddingValues()
        .calculateBottomPadding()

    var itemName by remember { mutableStateOf("") }
    val isEnableButton = remember {
        derivedStateOf {
            itemName.isNotBlank()
        }
    }

    Column(
        modifier = Modifier
            .padding(bottom = bottomPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = itemName,
            onValueChange = { itemName = it },
            label = { Text(stringResource(id = R.string.item_name)) },
            maxLines = 1,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .padding(bottom = 8.dp)
        )
        Button(
            onClick = {
                onAddItem(itemName)
                onDismiss()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            enabled = isEnableButton.value
        ) {
            Text(text = stringResource(id = R.string.add))
        }
    }
}

@Composable
fun ItemRow(
    itemName: String,
    onApplyDiscount: (Double) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = itemName,
            modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        ApplyDiscountButton(onApplyDiscount)
    }
}

@Composable
fun ApplyDiscountButton(onApplyDiscount: (Double) -> Unit) {
    var showDialog by remember { mutableStateOf(false) }

    Button(
        onClick = {
            showDialog = true
        },
    ) {
        Text(text = stringResource(id = R.string.apply_discount))
    }

    if (showDialog) {
        ShowDiscountDialog(
            showDialog = showDialog,
            onDiscountApplied = { discount ->
                onApplyDiscount(discount)
            },
            onDismiss = {
                showDialog = false
            }
        )
    }
}

@Composable
fun DiscountDialog(
    onDiscountApplied: (Double) -> Unit,
    onDismiss: () -> Unit
) {
    var discountValue by remember { mutableStateOf("") }

    val isEnableButton = remember {
        derivedStateOf {
            discountValue.isNotBlank()
        }
    }

    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = MaterialTheme.shapes.medium,
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = stringResource(id = R.string.enter_discount_value))
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = discountValue,
                    onValueChange = { discountValue = it },
                    label = { Text(stringResource(id = R.string.discount)) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        onDiscountApplied(discountValue.toDouble())
                        onDismiss()
                    },
                    enabled = isEnableButton.value,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(id = R.string.apply))
                }
            }
        }
    }
}

@Composable
fun ShowDiscountDialog(
    showDialog: Boolean,
    onDiscountApplied: (Double) -> Unit,
    onDismiss: () -> Unit
) {
    if (showDialog) {
        DiscountDialog(
            onDiscountApplied = onDiscountApplied,
            onDismiss = onDismiss
        )
    }
}