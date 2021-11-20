package com.example.myhealth.presentation

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myhealth.R
import com.example.myhealth.presentation.Gender
import kotlin.math.log10
import kotlin.math.pow


object TrueFalse {
    const val si = "Si"
    const val no = "No"
    const val si2 = "Si"
}


@Composable
fun Diabetes(
    navegarResultDiab: (String) -> Unit,
    navegarImc: () -> Unit
) {

    var edad by remember { mutableStateOf("") }
    var imc by remember { mutableStateOf("") }
    var cintura by remember { mutableStateOf("") }
    var puntos by remember { mutableStateOf(0)}
    var resultDiab by remember { mutableStateOf("")}
    val selectedGender = remember { mutableStateOf("") }
    val selectedQ1 = remember { mutableStateOf("") }
    val selectedQ2 = remember { mutableStateOf("") }
    val selectedQ3 = remember { mutableStateOf("") }
    val selectedQ4 = remember { mutableStateOf("") }
    val selectedQ5 = remember { mutableStateOf("") }

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
            text = "Riesgo de desarrollar Diabetes",
            style = TextStyle(color = Color.Black, fontSize = 42.sp, fontWeight = FontWeight.Black)
        )

        TextField(
            value = edad,
            onValueChange = {if(it.length <= 3) edad = it},
            label = { Text(text = "Edad") },
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

        Row{
            RadioButton(
                selected = selectedGender.value == Gender.male,
                onClick = { selectedGender.value = Gender.male },
                colors = RadioButtonDefaults.colors(Color.Green)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(Gender.male)
            Spacer(modifier = Modifier.size(16.dp))
            RadioButton(
                selected = selectedGender.value == Gender.female,
                onClick = { selectedGender.value = Gender.female },
                colors = RadioButtonDefaults.colors(Color.Green)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(Gender.female)
        }

        Row{
            TextField(
                value = imc,
                onValueChange = {if(it.length <= 5) imc = it},
                label = { Text(text = "IMC") },
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
            Spacer(modifier = Modifier.size(14.dp))
            Button(
                shape = RoundedCornerShape(25),
                onClick = {
                    navegarImc()
                }) {

                Text("Calcular mi IMC",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 12.sp
                    ))
            }
        }

        TextField(
            value = cintura,
            onValueChange = {if(it.length <= 5) cintura = it},
            label = { Text(text = "Cintura (cm)") },
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

        Row{

            Text(text = "¿Realiza ejercicio habitualmente?")
            Spacer(modifier = Modifier.size(8.dp))

            RadioButton(
                selected = selectedQ1.value == TrueFalse.si,
                onClick = { selectedQ1.value = TrueFalse.si },
                colors = RadioButtonDefaults.colors(Color.Green)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(TrueFalse.si)
            Spacer(modifier = Modifier.size(16.dp))
            RadioButton(
                selected = selectedQ1.value == TrueFalse.no,
                onClick = { selectedQ1.value = TrueFalse.no },
                colors = RadioButtonDefaults.colors(Color.Green)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(TrueFalse.no)
        }

        Row{

            Text(text = "¿Come a diario frutas o verduras?")
            Spacer(modifier = Modifier.size(8.dp))

            RadioButton(
                selected = selectedQ2.value == TrueFalse.si,
                onClick = { selectedQ2.value = TrueFalse.si },
                colors = RadioButtonDefaults.colors(Color.Green)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(TrueFalse.si)
            Spacer(modifier = Modifier.size(16.dp))
            RadioButton(
                selected = selectedQ2.value == TrueFalse.no,
                onClick = { selectedQ2.value = TrueFalse.no },
                colors = RadioButtonDefaults.colors(Color.Green)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(TrueFalse.no)
        }

        Row{

            Text(text = "¿Toma medicación par")
            Spacer(modifier = Modifier.size(8.dp))

            RadioButton(
                selected = selectedQ3.value == TrueFalse.si,
                onClick = { selectedQ3.value = TrueFalse.si },
                colors = RadioButtonDefaults.colors(Color.Green)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(TrueFalse.si)
            Spacer(modifier = Modifier.size(16.dp))
            RadioButton(
                selected = selectedQ3.value == TrueFalse.no,
                onClick = { selectedQ3.value = TrueFalse.no },
                colors = RadioButtonDefaults.colors(Color.Green)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(TrueFalse.no)
        }

        Row{

            Text(text = "¿Le han encontrado alg")
            Spacer(modifier = Modifier.size(8.dp))

            RadioButton(
                selected = selectedQ4.value == TrueFalse.si,
                onClick = { selectedQ4.value = TrueFalse.si },
                colors = RadioButtonDefaults.colors(Color.Green)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(TrueFalse.si)
            Spacer(modifier = Modifier.size(16.dp))
            RadioButton(
                selected = selectedQ4.value == TrueFalse.no,
                onClick = { selectedQ4.value = TrueFalse.no },
                colors = RadioButtonDefaults.colors(Color.Green)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(TrueFalse.no)
        }

        Text(text = "¿Algún familiar está dia")

        Row{
            RadioButton(
                selected = selectedQ5.value == TrueFalse.no,
                onClick = { selectedQ5.value = TrueFalse.no },
                colors = RadioButtonDefaults.colors(Color.Green)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(TrueFalse.no)
            Spacer(modifier = Modifier.size(16.dp))

            RadioButton(
                selected = selectedQ5.value == TrueFalse.si,
                onClick = { selectedQ5.value = TrueFalse.si },
                colors = RadioButtonDefaults.colors(Color.Green)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(TrueFalse.si + "(abuelos, tios, primos)")
            Spacer(modifier = Modifier.size(16.dp))

            RadioButton(
                selected = selectedQ5.value == TrueFalse.si2,
                onClick = { selectedQ5.value = TrueFalse.si2 },
                colors = RadioButtonDefaults.colors(Color.Green)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(TrueFalse.si2 + "(padres, hermanos, hijos)")
        }


        Spacer(modifier = Modifier.size(16.dp))

        Button(
            shape = RoundedCornerShape(50),
            onClick = {
                puntos = 0

                when(edad.toInt()){
                    in 0..44 -> { puntos += 0 }
                    in 45..54 -> { puntos += 2 }
                    in 55..64 -> { puntos += 3 }
                    in 65..150 -> { puntos += 4 }
                }

                when(imc.toDouble()){
                    in 0.0..24.0 -> { puntos += 0 }
                    in 25.0..30.0 -> { puntos += 1 }
                    in 31.0..150.0 -> { puntos += 3 }
                }

                if(selectedGender.value == Gender.male){
                    when(cintura.toDouble()){
                        in 0.0..93.9 -> { puntos += 0 }
                        in 94.0..102.0 -> { puntos += 1 }
                        in 102.1..150.0 -> { puntos += 3 }
                    }
                }else{
                    when(cintura.toDouble()){
                        in 0.0..79.0 -> { puntos += 0 }
                        in 80.0..88.0 -> { puntos += 1 }
                        in 88.1..150.0 -> { puntos += 3 }
                    }
                }

                if (selectedQ1.value == TrueFalse.no) { puntos += 2 }
                if (selectedQ2.value == TrueFalse.no) { puntos += 2 }
                if (selectedQ3.value == TrueFalse.si) { puntos += 2 }
                if (selectedQ4.value == TrueFalse.si) { puntos += 2 }
                if (selectedQ5.value == TrueFalse.si2) { puntos += 5 }
                if (selectedQ5.value == TrueFalse.si) { puntos += 3 }

                resultDiab = puntos.toString()
                navegarResultDiab(resultDiab)

            }
        ) {
            Text("CALCULAR")
        }


    }
}