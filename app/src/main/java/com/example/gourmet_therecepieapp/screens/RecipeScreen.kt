package com.example.gourmet_therecepieapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.gourmet_therecepieapp.Category
import com.example.gourmet_therecepieapp.MainViewModel
import com.example.gourmet_therecepieapp.R

@Composable
fun RecipeScreen(
    modifier: Modifier = Modifier,
    viewstate: MainViewModel.RecipeState,
    navigateToDetail: (Category) -> Unit
) {
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState
    Surface(
        color = MaterialTheme.colorScheme.background,
        modifier = Modifier
            .fillMaxSize()
//            .padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp),

    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            when {
                viewState.isLoading -> {
                    CircularProgressIndicator(modifier.align(Alignment.Center))
                }

                viewState.error != null -> {
                    Text(
                        text = "Error Occurred",
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )

                }

                else -> {
                    //Display Message
                    CategoryScreen(categories = viewState.list, navigateToDetail)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryScreen(
    categories: List<Category>,
    navigateToDetail: (Category) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Image(
                        modifier = Modifier
                            .size(40.dp)
                            .padding(start = 10.dp),
                        painter = painterResource(id = R.drawable.recipe),
                        contentDescription = " App Logo",
                        contentScale = ContentScale.Fit
                    )
                },
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Text(
                            modifier = Modifier.padding(start = 20.dp),
                            text = "Gourmet Recipe",
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onBackground
                            )
                        )
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = " Search "
                            )
                        }
                    }
                },
                modifier = Modifier
                    .border(
                        1.dp,
                        color = MaterialTheme.colorScheme.onSurface,
                        shape = RoundedCornerShape(1.dp)
                    )
                    .heightIn(10.dp)
            )
        },
        containerColor = Color.White,
        bottomBar = {
            Row {

            }
        }
    ) {
        LazyVerticalGrid(
            GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(MaterialTheme.colorScheme.background)
        ) {
            items(categories) { category ->
                CategoryItem(category = category, navigateToDetail)
            }
        }
    }
}

@Composable
fun CategoryItem(
    category: Category,
    navigateToDetail: (Category) -> Unit
) {
    //Items with rounded corner shape
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxSize()
            .border(1.dp, MaterialTheme.colorScheme.onSurface, shape = MaterialTheme.shapes.medium)
            .wrapContentSize()
            .background(
                MaterialTheme.colorScheme.primaryContainer,
                shape = MaterialTheme.shapes.medium
            )
            .clickable {
                navigateToDetail(category)
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = category.strCategory,
            modifier = Modifier
                .fillMaxSize()
                .aspectRatio(1f)
                .background(MaterialTheme.colorScheme.primaryContainer)
        )
        Text(
            text = category.strCategory,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp, bottom = 4.dp)
        )
    }
}
