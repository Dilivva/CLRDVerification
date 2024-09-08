@file:OptIn(ExperimentalResourceApi::class)
package theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.FontResource
import org.jetbrains.compose.resources.InternalResourceApi

@ExperimentalResourceApi
private object Font {
    val bold: FontResource by lazy { init_font("bold") }

    val medium: FontResource by lazy { init_font("medium") }

    val regular: FontResource by lazy { init_font("regular") }
}

@OptIn(InternalResourceApi::class)
@ExperimentalResourceApi
private fun init_font(name: String): FontResource = FontResource(
    "font:$name",
    setOf(
        org.jetbrains.compose.resources.ResourceItem(setOf(), "font/$name.ttf"),
    )
)

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Inter(): FontFamily{
    return FontFamily(
        Font(Font.regular, weight = FontWeight.Normal),
        Font(Font.bold, weight = FontWeight.Bold),
        Font(Font.medium, weight = FontWeight.Medium)
    )
}

val HubTypography = @Composable {
    Typography(
        defaultFontFamily = Inter()
    )
}