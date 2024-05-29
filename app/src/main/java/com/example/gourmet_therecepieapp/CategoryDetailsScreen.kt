package com.example.gourmet_therecepieapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun CategoryDetailsScreen(category: Category) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        ,horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = category.strCategory,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(top = 32.dp),
            style = TextStyle(fontWeight = FontWeight.Bold),
            fontSize = 32.sp,
                )
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = category.strCategory,
            modifier = Modifier
                .wrapContentSize()
                .aspectRatio(1f)
                .border(0.5.dp, MaterialTheme.colorScheme.primary,shape = MaterialTheme.shapes.medium)
                .padding(10.dp)
        )
        Text(
            text = category.strCategoryDescription,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .padding(top = 16.dp)
                .verticalScroll(state = rememberScrollState()
                )
        )

    }
}