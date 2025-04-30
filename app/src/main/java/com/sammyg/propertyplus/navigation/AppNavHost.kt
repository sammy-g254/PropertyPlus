package com.sammyg.propertyplus.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sammyg.propertyplus.ui.theme.screens.about.AboutScreen
import com.sammyg.propertyplus.ui.theme.screens.contact.ContactScreen
import com.sammyg.propertyplus.ui.theme.screens.home.HomeScreen
import com.sammyg.propertyplus.ui.theme.screens.login.LoginScreen
import com.sammyg.propertyplus.ui.theme.screens.property.AddPropertyScreen
import com.sammyg.propertyplus.ui.theme.screens.property.EditPropertyScreen
import com.sammyg.propertyplus.ui.theme.screens.property.PropertyListScreen
import com.sammyg.propertyplus.ui.theme.screens.service.ServiceScreen
import com.sammyg.propertyplus.ui.theme.screens.signup.SignupScreen

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = ROUT_LOGIN
) {

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(ROUT_HOME) {
            HomeScreen(navController)
        }
        composable(ROUT_ABOUT) {
            AboutScreen(navController)
        }
        composable(ROUT_CONTACT) {
            ContactScreen(navController)
        }
        composable(ROUT_SERVICE) {
            ServiceScreen(navController)
        }
        composable(ROUT_SIGNUP) {
            SignupScreen(navController)
        }
        composable(ROUT_LOGIN) {
            LoginScreen(navController)
        }
        composable(ROUT_ADD_PROPERTY) {
            AddPropertyScreen(navController = navController)
        }
        composable(ROUT_VIEW_PROPERTY) {
            PropertyListScreen(navController = navController)
        }
        composable(
            ROUT_EDIT_PROPERTY,
            arguments = listOf(navArgument("propertyId") { type = NavType.StringType })
        ) {
            val propertyId = it.arguments?.getString("propertyId") ?: ""
            EditPropertyScreen(navController, propertyId)
        }

    }
}