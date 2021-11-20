package com.example.myhealth.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument


sealed class Destinations(
    val route: String,
    val arguments: List<NamedNavArgument>,
    val title: String,
    val icon: ImageVector
){
    //Pantalla donde se obienen los datos para el IMC
    object IMC: Destinations("IMC", emptyList(), "IMC", Icons.Filled.Home)

    //Pantalla de resultado de IMC
    object ResultImc: Destinations("ResultImc/?resultImc={resultImc}", emptyList(), "Resultado IMC", Icons.Filled.Home){
        fun createRouteImc(resultImc: String) = "ResultImc/?resultImc=$resultImc"
    }


    //Pantalla donde se obtienen los datos para el PGC
    object PGC: Destinations("PGC",emptyList(), "PGC", Icons.Filled.Settings)

    //Pantalla de resultado de PGC
    object ResultPgc: Destinations("ResultPgc/?newText={resultPgc}", emptyList(), "Resultado PGC", Icons.Filled.Settings) {
        fun createRoutePgc(resultPgc: String) = "ResultPgc/?resultPgc=$resultPgc"
    }


    //Pantalla donde se obtienen la probabilidad de tener diabetes
    object Diabetes: Destinations("Diabetes", emptyList(), "Diabetes", Icons.Filled.Save)

    //Pantalla de resultado de Diabetes
    object ResultDiab: Destinations("ResultDiab/?newText={resultDiab}", emptyList(), "Resultado Diab", Icons.Filled.Settings) {
        fun createRouteDiab(resultDiab: String) = "ResultDiab/?resultDiab=$resultDiab"

    }


    //Pantalla de Login
    object Login: Destinations("login", emptyList(), "Login", Icons.Filled.Security )

    //Pantalla de Registro
    object Register: Destinations("register", emptyList(), "Registro", Icons.Filled.Security )

    //Pantalla donde se hara el Login y ver datos del perfil
    object Perfil: Destinations("perfil", emptyList(), "Perfil", Icons.Filled.Favorite)

}
