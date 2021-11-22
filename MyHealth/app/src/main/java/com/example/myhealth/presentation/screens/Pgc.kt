package com.example.myhealth.presentation

import android.util.Log
import android.widget.Toast
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import kotlin.math.log10

//Calcular resultado
object Gender {
    const val male = "Hombre"
    const val female = "Mujer"
}

@Composable
fun Pgc(
    navegarResultPgc: (String) -> Unit
) {

    var cintura by remember { mutableStateOf("") }
    var cuello by remember { mutableStateOf("") }
    var altura by remember { mutableStateOf("") }
    var selectedGender = remember { mutableStateOf("")}
    var resultPgc by remember { mutableStateOf(0.0) }

    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ){
        ConstraintLayout {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(state = scrollState),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = "Porcentage de Grasa Corporal",
                    style = TextStyle(color = Color.Black, fontSize = 42.sp, fontWeight = FontWeight.Black)

                )

                TextField(
                    value = cintura,
                    onValueChange = {if(it.length <= 4) cintura = it},
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

                TextField(
                    value = cuello,
                    onValueChange = {if(it.length <= 4) cuello = it},
                    label = { Text(text = "Cuello (cm)") },
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
                    value = altura,
                    onValueChange = {if(it.length <= 3) altura = it},
                    label = { Text(text = "Estatura (cm)") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions (
                        onNext = {
                            Log.i("TAG", "Click")
                            focusManager.clearFocus()
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
                Spacer(modifier = Modifier.size(18.dp))

                Button(
                    shape = RoundedCornerShape(50),
                    onClick = {
                        if (cintura.isNotEmpty() && cuello.isNotEmpty() && altura.isNotEmpty() && selectedGender.value.isNotEmpty()) {
                            if(selectedGender.value == Gender.male){
                                var x = cintura.toDouble() - cuello.toDouble()

                                resultPgc = 495 / (1.0324 - (0.19077 * (log10(x))) + 0.15456 * (log10(altura.toDouble())))
                                resultPgc.toString()
                                navegarResultPgc(String.format("%.2f", resultPgc))
                            }else{
                                resultPgc.toString()
                                navegarResultPgc(String.format("%.2f", resultPgc))

                            }
                        }
                    }
                ) {
                    Text("CALCULAR")
                }
            }
        }
    }

}