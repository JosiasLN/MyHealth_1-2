package com.example.myhealth.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myhealth.navigation.Destinations.*
import com.example.myhealth.presentation.*
import com.example.myhealth.presentation.screens.ResultImc
import com.example.myhealth.presentation.screens.ResultPgc

@Composable
fun NavigationHost(
    navController: NavHostController
) {

    //Aqui se define las diferentes pantallas que se estaran utilizando en la navegacion
    //startDestination es la pantalla en la cual se inicia
    NavHost(navController = navController, startDestination = IMC.route){

        //Aqui se crea la pantalla IMC
        composable(IMC.route){
            Imc(
                navegarResultImc = {resultImc ->
                    navController.navigate(ResultImc.createRouteImc(resultImc))
                }
            )
        }
        //Se crea la pantalla del resultado de IMC
        composable(
            ResultImc.route,
            arguments = listOf(navArgument("resultImc"){ defaultValue = "Vacio" })
        ){navBackStackEntry ->
            var resultImc = navBackStackEntry.arguments?.getString("resultImc")
            requireNotNull(resultImc)
            ResultImc(resultImc)
        }



        //Aqui se crea la pantalla PGC
        composable(PGC.route){
            Pgc(
                navegarResultPgc = {resultPgc ->
                    navController.navigate(ResultPgc.createRoutePgc(resultPgc))
                }
            )
        }

        //Se crea la pantalla del resultado para el porcentage de Grasa Corporal
        composable(
            ResultPgc.route,
            arguments = listOf(navArgument("resultPgc"){ defaultValue = "Vacio" })
        ){navBackStackEntry ->
            var resultPgc = navBackStackEntry.arguments?.getString("resultPgc")
            requireNotNull(resultPgc)
            ResultPgc(resultPgc)
        }



        //Se crea la pantalla de Diabetes
        composable(Diabetes.route){
            Diabetes()
        }



        //Se crea la pantalla de Login
        composable(Login.route){
            //Login()

        }

    }
}