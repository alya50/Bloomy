package org.bloomy.project.navigation.main.domain.models

sealed interface DrawerLogicActions {
    data object closeDrawer: DrawerLogicActions
    data object onMainContentClickWhenDrawerOpen: DrawerLogicActions
    data object openDrawer: DrawerLogicActions
    data object onDragStart: DrawerLogicActions
    data object onDragEnd: DrawerLogicActions
    data object onDragCancel: DrawerLogicActions
    data class onDrag(val dragAmount: Float): DrawerLogicActions
}