package com.example.myhealth.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.log
import kotlin.math.log10
import kotlin.math.pow

@Composable
fun Pgc(
    navegarResultPgc: (String) -> Unit
) {
    var cintura by remember { mutableStateOf("") }
    var cuello by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var resultPgc by remember { mutableStateOf(0.0) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "PGC",
            style = TextStyle(color = Color.Black, fontSize = 42.sp, fontWeight = FontWeight.Black)
        )

        TextField(
            value = cintura,
            onValueChange = {cintura = it},
            label = {Text("Cintura (cm)")})

        TextField(
            value = cuello,
            onValueChange = {cuello = it},
            label = {Text("Cuello (cm)")})

        TextField(
            value = altura,
            onValueChange = {altura = it},
            label = {Text("Estatura (cm)")})


        Button(onClick = {

            if(cintura.isNotEmpty() && cuello.isNotEmpty() && altura.isNotEmpty()){

                var x = cintura.toDouble()-cuello.toDouble()

                resultPgc = 495 / (1.0324-(0.19077*(log10(x)))+0.15456*(log10(altura.toDouble())) )

                navegarResultPgc(resultPgc.toString())
            }else{
                navegarResultPgc(resultPgc.toString())
            }

        }) {
            Text("Enviar")
        }
    }
}