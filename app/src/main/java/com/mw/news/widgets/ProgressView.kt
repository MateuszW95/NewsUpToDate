package com.mw.news.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mw.news.base.State
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ProgressView(showProgress: Boolean, modifier: Modifier, content: @Composable () -> Unit
){
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        content()
        if(showProgress) {
            Surface(
                color = Color.Black.copy(alpha = 0.6f),
                modifier = Modifier.fillMaxSize()
            ) {}
            CircularProgressIndicator(
                modifier = Modifier.padding(8.dp),
                color = Color.Green,
                strokeWidth = 2.dp
            )
        }
    }
}
