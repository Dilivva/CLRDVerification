import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.CanvasBasedWindow
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.skiko.wasm.onWasmReady
import org.w3c.dom.url.URLSearchParams
import theme.CLRDTheme


@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    onWasmReady {
        CanvasBasedWindow(canvasElementId = "ComposeJsTarget") {
            CLRDTheme {

                var userId by remember { mutableStateOf<String?>(null) }

                LaunchedEffect(Unit){
                    val ele = document.getElementById("loading-indicator")
                    ele?.remove()
                }
                LaunchedEffect(Unit) {
                    val location = window.location
                    val urlSearchParams = URLSearchParams(location.search)
                    userId = urlSearchParams.get("user_id")

                }
                userId?.let {
                    App(it)
                }

            }
        }
    }

}