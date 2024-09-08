package design

import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap


@Composable
fun Loading(
    modifier: Modifier = Modifier
){
    Box(modifier = modifier) {
        CircularProgressIndicator(
            strokeCap = StrokeCap.Round,
            modifier = Modifier
                .align(Alignment.Center)
        )
    }

}