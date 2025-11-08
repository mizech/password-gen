package com.example.passwdgen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

enum class Option(var isActive: Boolean, var title: String) {
    UP(isActive = false, title = "Upper"),
    LOW(isActive = true, title = "Lower"),
    NUM(isActive = true, title = "Number"),
    SPEC(isActive = true, title = "Special")
}

@Composable
fun MainScreen() {
    val mainVM = viewModel<MainViewModel>()
    var isExpanded by remember {
        mutableStateOf(false)
    }

    val options = remember {
        mutableStateListOf<Boolean>(
            Option.UP.isActive,
            Option.LOW.isActive,
            Option.NUM.isActive,
            Option.SPEC.isActive)
    }

    var selectedLength = remember {
        mutableIntStateOf(8)
    }

    Scaffold { paddingValues ->
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "PasswdGen",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    paddingValues.calculateTopPadding()))
            Box(modifier = Modifier.padding(20.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Length: ")
                    IconButton(onClick = {
                        isExpanded = !isExpanded
                    }) {
                        Icon(Icons.Default.List, contentDescription = "")
                    }
                }
                DropdownMenu(expanded = isExpanded,
                    onDismissRequest = {
                        isExpanded = false
                    }) {
                    for (i in 4..20) {
                        DropdownMenuItem(text = {
                            Text(i.toString())
                        }, onClick = {
                            selectedLength.value = i
                            isExpanded = false
                        })
                    }
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                for (i in 0 until options.size) {
                    Text(text = options[i].toString())
                    Checkbox(checked = options[i], onCheckedChange = {
                        options[i] = !options[i]
                    })
                }
            }
            Text(text = mainVM.passwd.collectAsState().value,
                fontSize = 30.sp, fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 30.dp))
            Button(onClick = {
                mainVM.generatePasswd(length = selectedLength.value,
                    options = options.toList())
            }) {
                Text(text = "Generate",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold)
            }
        }
    }
}