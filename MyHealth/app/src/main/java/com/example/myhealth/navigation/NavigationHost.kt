package com.example.myhealth.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.myhealth.model.LoginViewModel
import com.example.myhealth.model.RegisterViewModel
import com.example.myhealth.navigation.Destinations.*
import com.example.myhealth.presentation.*
import com.example.myhealth.presentation.screens.*

@Composable
fun NavigationHost(
    navController: NavHostController
) {

    //Aqui se define las diferentes pantallas que se estaran utilizando en la navegacion
    //startDestination es la pantalla en la cual se inicia
    NavHost(navController = navController, startDestination = Login.route){
        //Aqui se crea la pantalla Login
        composable(route = Login.route){
            val viewModel: LoginViewModel = hiltViewModel()

            if(viewModel.state.value.succesLogin ){
                LaunchedEffect(key1 = Unit){
                    navController.navigate(
                        Destinations.IMC.route
                    ){
                        popUpTo(Destinations.Login.route){
                            inclusive = true
                        }
                    }
                }
            } else {
                Login(
                    state = viewModel.state.value,
                    onLogin = viewModel::login,
                    onNavigationToRegister = {
                        navController.navigate(Register.route)
                    },
                    onDismissDialog = viewModel::hideErrorDialog
                )
            }
        }

        composable(Register.route){
            val viewModel: RegisterViewModel = hiltViewModel()
            Registration(
                state = viewModel.state.value,
                onRegister = viewModel::register,
                onBack = {
                    navController.popBackStack()
                },
                onDismissDialog = viewModel::hideErrorDialog
            )
        }


        //Aqui se crea la pantalla IMC
        composable(IMC.route){
            Imc(
                navegarResultImc = {resultImc ->
                    navController.navigate(ResultImc.createRouteImc(resultImc)){
                        popUpTo(Destinations.IMC.route){
                            inclusive = true
                        }
                    }
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
            Diabetes(
                navegarResultDiab = {resultDiab ->
                    navController.navigate(ResultDiab.createRouteDiab(resultDiab))
                },
                navegarImc = {navController.navigate(IMC.route)}
            )
        }
        //Se crea la pantalla del resultado de Diabetes
        composable(
            ResultDiab.route,
            arguments = listOf(navArgument("resultDiab"){ defaultValue = "" })
        ){navBackStackEntry ->
            var resultDiab = navBackStackEntry.arguments?.getString("resultDiab")
            requireNotNull(resultDiab)
            ResultDiab(resultDiab)
        }



        //Se crea la pantalla de Login
        composable(Perfil.route){
            Perfil()

        }

    }
}