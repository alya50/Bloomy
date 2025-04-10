import androidx.compose.runtime.Composable

@Composable
actual fun EmptyContextMenu(
    content: @Composable () -> Unit
): Unit {
    content()
}