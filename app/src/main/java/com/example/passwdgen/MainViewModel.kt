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

    fun generatePasswd(length: Int, options: List<Boolean>) { // https://www.youtube.com/watch?v=aH9_wsw-KLI&list=PLLJ3IUzdiq18exhhrM7FW_zzcY1gnuvZM&index=3
        var result = ""

        if (options[0]) {
            println("Contains UP.")
        }
        if (options[1]) {
            println("Contains LOW.")
        }
        if (options[2]) {
            println("Contains UP.")
        }
        if (options[3]) {
            println("Contains UP.")
        }

        while (true) {
            result += Random.nextInt(from = 0, until = 9).toString()

            if (result.length == length) {
                break
            }
        }

        _passwd.value = result
    }
}