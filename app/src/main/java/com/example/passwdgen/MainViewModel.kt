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

    fun generatePasswd(length: Int, options: List<Option>) {
        var feasibleChars = mutableListOf<Char>()
        var result = ""

        options.forEach {
            if (it.title == Option.UP.title && it.isActive) {
                feasibleChars.addAll(('A'..'Z').toList())
                println("Contains UP.")
            }

            if (it.title == Option.LOW.title && it.isActive) {
                feasibleChars.addAll(('a'..'z').toList())
                println("Contains LOW.")
            }

            if (it.title == Option.NUM.title && it.isActive) {
                feasibleChars.addAll(('0'..'9').toList())
                println("Contains NUM.")
            }

            if (it.title == Option.SPEC.title && it.isActive) {
                val specs = listOf<Char>('!', '"', '§', '$', '%', '&', '/', '(', ')', '=',
                    '?', '+', '*', '#', '-', '_', ',', ';', '<', '>')
                feasibleChars.addAll(specs)
                println("Contains SPEC.")
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