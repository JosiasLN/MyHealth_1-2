package com.example.myhealth.presentation.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultImc(
    result: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Su IMC es de",
            style = TextStyle(color = Color.Black, fontSize = 42.sp, fontWeight = FontWeight.Black)
        )
        Text(
            text = result,
            style = TextStyle(color = Color.Green, fontSize = 42.sp, fontWeight = FontWeight.Black)
        )

        Text(
            text = "SITUACIÓN",
            style = TextStyle(color = Color.Black, fontSize = 42.sp, fontWeight = FontWeight.Black)
        )

        mostrarSituacion(result.toDouble())


        //Imagen del peso

    }
}

@SuppressLint("ComposableNaming")
@Composable
fun mostrarSituacion(situacion: Double){
    when(situacion){
        in 0.0..18.5 -> {
            Text(
                text = "Bajo Peso",
                style = TextStyle(color = Color.Yellow, fontSize = 42.sp, fontWeight = FontWeight.Black)
            )
            //imgResult.value = R.drawable.peso_bajo

        }
        in 18.5..24.9 -> {
            Text(
                text = "Peso Normal",
                style = TextStyle(color = Color.Green, fontSize = 42.sp, fontWeight = FontWeight.Black)
            )
            //imgResult.value = R.drawable.peso_ideal

        }
        in 25.0..29.9 -> {
            Text(
                text = "Sobrepeso",
                style = TextStyle(color = Color.Yellow, fontSize = 42.sp, fontWeight = FontWeight.Black)
            )
            //imgResult.value = R.drawable.sobrepeso

        }
        in 30.0..34.9 -> {
            Text(
                text = "Obesidad",
                style = TextStyle(color = Color.Red, fontSize = 42.sp, fontWeight = FontWeight.Black)
            )
            //imgResult.value = R.drawable.sobrepeso
        }
        in 35.0..39.9 -> {
            Text(
                text = "Obesidad Severa",
                style = TextStyle(color = Color.Red, fontSize = 42.sp, fontWeight = FontWeight.Black)
            )
            //imgResult.value = R.drawable.obesidad_severa
        }
        in 40.0..200.0 -> {
            Text(
                text = "Obesidad Morbida",
                style = TextStyle(color = Color.Red, fontSize = 42.sp, fontWeight = FontWeight.Black)
            )
            //imgResult.value = R.drawable.obesidad_morbida
        }
        else -> {

        }
    }
}