package com.example.nandoapp.ui.screens


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    Text(
       text = "Hola Fernando",
        fontSize = 24.sp,
        fontWeight = FontWeight.ExtraBold,
        color = Color(0xFF000000),
        modifier = Modifier.padding(20.dp, 20.dp)
    )
}