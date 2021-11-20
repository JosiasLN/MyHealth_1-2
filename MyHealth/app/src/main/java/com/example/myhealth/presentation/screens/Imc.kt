package com.example.myhealth.presentation

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.pow
import androidx.compose.material.TextField as TextField


@Composable
fun Imc(
    navegarResultImc: (String) -> Unit
) {

    var estatura by remember{ mutableStateOf("") }
    var peso by remember{ mutableStateOf("") }
    var resultImc by remember { mutableStateOf(0.0) }

    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Indice de Masa Corporal",
            style = TextStyle(color = Color.Black, fontSize = 42.sp, fontWeight = FontWeight.Black)
        )

        TextField(
            value = estatura,
            onValueChange = {if(it.length <= 3) estatura = it},
            label = { Text(text = "Estatura (cm)") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions (
                onNext = {
                    Log.i("TAG", "Click")
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )

        TextField(
            value = peso,
            onValueChange = {if(it.length <= 4) peso = it},
            label = { Text(text = "Peso (kg)") },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions (
                onDone = {
                    Log.i("TAG", "Click")
                    focusManager.clearFocus()
                }
            )
        )

        Spacer(modifier = Modifier.size(8.dp))
        Button(
            shape = RoundedCornerShape(50),
            onClick = {
                if(estatura.isNotEmpty() && peso.isNotEmpty()){
                    resultImc = (peso.toDouble()) / (estatura.toDouble()/100).pow(2)
                    resultImc.toString()
                    navegarResultImc(String.format("%.2f",resultImc))
                }

            }) {
            Text("CALCULAR")
        }
    }
}