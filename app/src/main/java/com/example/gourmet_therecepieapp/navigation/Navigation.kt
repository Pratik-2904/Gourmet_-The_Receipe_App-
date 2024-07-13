package com.example.gourmet_therecepieapp.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gourmet_therecepieapp.Category
import com.example.gourmet_therecepieapp.MainViewModel
import com.example.gourmet_therecepieapp.screens.CategoryDetailsScreen
import com.example.gourmet_therecepieapp.screens.RecipeScreen

class Navigation {

}

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun RecipeApp(
    navController: NavHostController
) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.ReceipeScreen.route) {
        composable(route = Screen.ReceipeScreen.route) {
            RecipeScreen(
                viewstate = viewState,
                navigateToDetail = {
                    /* This partis responsible for passing data to detail screen from this screen
                     */
                    navController.currentBackStackEntry?.savedStateHandle?.set("cat", it)
                    navController.navigate(Screen.DetailScreen.route)
                }

            )
        }

        composable(route = Screen.DetailScreen.route) {
            val category =
                navController.previousBackStackEntry?.savedStateHandle?.get<Category>(key = "cat")
                    ?: Category("", "", "", "")
            CategoryDetailsScreen(category = category, onClick = {
                navController.navigateUp()
            })

        }
    }
}


