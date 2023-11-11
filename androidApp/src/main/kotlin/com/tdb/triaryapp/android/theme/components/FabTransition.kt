package com.tdb.triaryapp.android.theme.components

import androidx.compose.animation.*
import androidx.navigation.NavBackStackEntry

@JvmSuppressWildcards
fun AnimatedContentTransitionScope<NavBackStackEntry>.FabEnterTransition(): EnterTransition {
    return scaleIn() + fadeIn() + slideInVertically(initialOffsetY = {
        it / 2
    })
}

@JvmSuppressWildcards
fun AnimatedContentTransitionScope<NavBackStackEntry>.FabExitTransition(): ExitTransition {
    return scaleOut() + fadeOut() + slideOutVertically(targetOffsetY = {
        it / 2
    })
}