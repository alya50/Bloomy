package org.bloomy.project.navigation.main.domain.models

data class DrawerLogicState(
    val isDrawerOpen: Boolean = false,
    val drawerOffsetX: Float = 0f,
    val isDragging: Boolean = false,
    val dragStartX: Float = 0f,
    val minOffsetX: Float? = null,
    val drawerWidth: Float = 0f,
    val drawerContentZIndex: Float = 1f,
)