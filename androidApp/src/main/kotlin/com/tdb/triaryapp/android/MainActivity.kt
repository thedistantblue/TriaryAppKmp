package com.tdb.triaryapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") { MainScreen(navController) }
                        composable("local/tabs") { TabsScreen(navController) }
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            navController.navigate("local/tabs")
        }) {
            Text(text = stringResource(R.string.login_screen_local_use))
        }
        Button(onClick = {
            navController.navigate("online/tabs")
        }) {
            Text(text = stringResource(R.string.login_screen_online_use))
        }
    }
}

@Composable
fun TabsScreen(navController: NavController) {
    Scaffold(topBar = { AppBar(navController) },
             content = { Tabs(navController, it) })
}

@Composable
fun Tabs(navController: NavController, paddingValues: PaddingValues) {
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf(R.string.tab_screen_power, R.string.tab_screen_cardio)

    Column(modifier = Modifier.fillMaxWidth().padding(paddingValues)) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    text = { Text(text = stringResource(tab)) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.tab_screen_app_bar), color = Color.White)
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack()
            }) {
                Icon(Icons.Outlined.ArrowBack, "backIcon")
            }
        }
    )
}