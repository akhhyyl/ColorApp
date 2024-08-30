package com.artbonix.colorapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sync
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.room.jarjarred.org.antlr.v4.codegen.model.Sync
import java.util.*

@Composable
fun ColorScreen(viewModel: ColorViewModel) {
    val colors by viewModel.allColors.observeAsState(initial = emptyList())
    val pendingCount by viewModel.pendingCount.observeAsState(initial = 0)

    Column {
        TopAppBar(
            title = { Text("Color App") },
            actions = {
                IconButton(onClick = { viewModel.syncData() }) {
                    Icon(imageVector = Icons.Default.Sync, contentDescription = null)
                    Text(text = pendingCount.toString())
                }
            }
        )
        LazyColumn {
            items(colors) { colorItem ->
                Card(
                    backgroundColor = Color(android.graphics.Color.parseColor(colorItem.color)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = colorItem.color, style = MaterialTheme.typography.h6)
                        Text(text = "Created at ${Date(colorItem.timestamp)}")
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = { viewModel.addRandomColor() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Add Color")
        }
    }
}
