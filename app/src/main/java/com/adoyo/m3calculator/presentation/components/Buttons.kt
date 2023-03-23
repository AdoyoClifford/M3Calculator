package com.adoyo.m3calculator.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import com.adoyo.m3calculator.ui.theme.M3CalculatorTheme

@Composable
fun Buttons() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface)
            .clip(CircleShape), contentAlignment = Alignment.Center
    ) {
        Text(text = "1")
    }
}

@Preview
@Composable
fun ButtonPreview() {
    M3CalculatorTheme {
        Buttons()
    }

}