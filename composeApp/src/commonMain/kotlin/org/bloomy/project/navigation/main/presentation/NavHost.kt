package org.bloomy.project.navigation.main.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.bloomy.project.navigation.main.domain.models.Route
import org.bloomy.project.screens.home.presentation.view.HomeScreenRoot

@Composable
fun MainNavigationHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.Home,
    ) {
        composable<Route.Home>  {
            HomeScreenRoot()
        }
    }
}