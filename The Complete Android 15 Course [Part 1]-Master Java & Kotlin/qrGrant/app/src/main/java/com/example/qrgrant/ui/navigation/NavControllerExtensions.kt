package com.example.qrgrant.ui.navigation

import android.os.Handler
import android.os.Looper
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

fun NavController.currentRoute(): String? =
    currentBackStackEntry?.destination?.route

fun NavController.previousRoute(): String? =
    previousBackStackEntry?.destination?.route

/** Navigate to a route normally */
fun NavController.next(route: String) {
    navigate(route) {
        launchSingleTop = true
        restoreState = true
    }
}

private var isBacking = false

fun NavController.back(onBackAtRoot: (() -> Unit)? = null) {
    if (isBacking) return
    isBacking = true

    val success = this.navigateUp()
    if (!success) {
        onBackAtRoot?.invoke()
    }

    Handler(Looper.getMainLooper()).postDelayed({
        isBacking = false
    }, 300)
}

/** Remove current screen and navigate to new route (offUntil) */
fun NavController.offUntil(route: String) {
    val current = currentBackStackEntry?.destination?.route ?: return
    navigate(route) {
        popUpTo(current) { inclusive = true } // xóa màn hiện tại
        launchSingleTop = true
    }
}

/** Clear all back stack and navigate to new route (offAll) */
fun NavController.offAll(route: String) {
    navigate(route) {
        popUpTo(0) { inclusive = true }
        launchSingleTop = true
    }
}

/** Optional Composable helper for back handling */
@Composable
fun HandleBack(navController: NavController, onBackAtRoot: (() -> Unit)? = null) {
    BackHandler {
        navController.back(onBackAtRoot)
    }
}
