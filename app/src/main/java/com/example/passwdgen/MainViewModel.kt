package com.example.passwdgen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainViewModel(): ViewModel() {
    private val _passwd
        = MutableStateFlow<String>("0")
    val passwd: StateFlow<String> = _passwd.asStateFlow()
    // Todo: Display the length of selected chars in the UI.
    // Todo: Add a copy-to-clipboard button.
    // Todo: Improve the algorithm (Each group of char HAS to be included, in case of selected).
    fun generatePasswd(length: Int, options: List<Option>) {
        var feasibleChars = mutableListOf<Char>()
        var result = ""

        options.forEach {
            if (it.title == Option.UP.title && it.isActive) {
                feasibleChars.addAll(('A'..'Z').toList())
            }

            if (it.title == Option.LOW.title && it.isActive) {
                feasibleChars.addAll(('a'..'z').toList())
            }

            if (it.title == Option.NUM.title && it.isActive) {
                feasibleChars.addAll(('0'..'9').toList())
            }

            if (it.title == Option.SPEC.title && it.isActive) {
                val specs = listOf<Char>('!', '"', '§', '$', '%', '&', '/', '(', ')', '=',
                    '?', '+', '*', '#', '-', '_', ',', ';', '<', '>')
                feasibleChars.addAll(specs)
            }
        }

        while (true) {
            result += feasibleChars.random()

            if (result.length == length) {
                break
            }
        }

        _passwd.value = result
    }
}