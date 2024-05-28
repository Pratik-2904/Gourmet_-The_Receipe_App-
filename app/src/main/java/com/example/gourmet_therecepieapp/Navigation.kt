package com.example.gourmet_therecepieapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class Navigation {

}

@Composable
fun RecipeApp(
    navController: NavHostController
) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.categoriesState

    NavHost(navController = navController, startDestination = Screen.ReceipeScreen.route) {
        composable(route = Screen.ReceipeScreen.route) {
            RecipeScreen(viewstate = viewstate,navigateToDetail = {
                /* This parrtis responsible for passing data to detail screen from this screen

                 */
                navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
                navController.navigate(Screen.DetailScreen.route)
            }

            )
        }
        composable(route = Screen.DetailScreen.route){
            val category = navController.previousBackStackEntry?.savedStateHandle?.
            get<Category>(key = "cat") ?: Category("","","","")
            CategoryDetailsScreen(category = category)
            
        }
    }
}


