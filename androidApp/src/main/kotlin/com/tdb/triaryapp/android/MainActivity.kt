package com.tdb.triaryapp.android

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
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
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.tdb.triaryapp.RepositoryFactory
import com.tdb.triaryapp.android.power.PowerTrainingDetailsScreen
import com.tdb.triaryapp.android.power.PowerTrainingList
import com.tdb.triaryapp.android.power.PowerTrainingSettingsScreen
import com.tdb.triaryapp.android.power.dates.PTDatesScreen
import com.tdb.triaryapp.android.power.exercises.PTExercisesScreen
import com.tdb.triaryapp.android.power.packs.PTPacksScreen
import com.tdb.triaryapp.android.power.viewmodel.PowerTrainingListViewModel
import com.tdb.triaryapp.android.power.viewmodel.PowerTrainingListViewModelFactory
import com.tdb.triaryapp.android.power.viewmodel.PowerTrainingViewModel
import com.tdb.triaryapp.android.power.viewmodel.PowerTrainingViewModelFactory
import com.tdb.triaryapp.android.theme.TriaryAppTheme
import com.tdb.triaryapp.android.theme.components.FabEnterTransition
import com.tdb.triaryapp.android.theme.components.FabExitTransition

class MainActivity : ComponentActivity() {

    private val repository = RepositoryFactory.createTrainingRepository()
    private lateinit var trainingViewModel: Lazy<PowerTrainingViewModel>
    private lateinit var trainingListViewModel: Lazy<PowerTrainingListViewModel>

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        this.trainingViewModel = viewModels { PowerTrainingViewModelFactory.getFactory(repository) }
        this.trainingListViewModel = viewModels { PowerTrainingListViewModelFactory.getFactory(repository) }

        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            TriaryAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = RouteConstants.Main.MAIN) {
                        composable(RouteConstants.Main.MAIN) { MainScreen(navController) }
                        tabsRoute(navController, trainingViewModel.value, trainingListViewModel.value)
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
private fun NavGraphBuilder.tabsRoute(navController: NavController,
                                      trainingViewModel: PowerTrainingViewModel,
                                      trainingListViewModel: PowerTrainingListViewModel
) {
    navigation(startDestination = RouteConstants.Local.Tabs.TABS,
               route = RouteConstants.Local.Tabs::class.java.name) {
        composable(RouteConstants.Local.Tabs.TABS) {
            TabsScreen(navController, trainingListViewModel)
        }
        composable(RouteConstants.Local.Tabs.Create.CREATE_POWER,
                   enterTransition = { this.FabEnterTransition() },
                   exitTransition = { this.FabExitTransition() }) {
            PowerTrainingSettingsScreen(navController, null, trainingViewModel)
        }
        composable(RouteConstants.Local.Tabs.Edit.EDIT_POWER_ROUTE) {
            PowerTrainingSettingsScreen(navController, it.arguments?.getString("trainingId"), trainingViewModel)
        }
        powerTrainingRoute(navController)
    }
}

private fun NavGraphBuilder.powerTrainingRoute(navController: NavController) {
    navigation(startDestination = RouteConstants.Local.Tabs.PowerTraining.POWER_TRAINING_ROUTE,
               route = RouteConstants.Local.Tabs.PowerTraining::class.java.name) {
        composable(RouteConstants.Local.Tabs.PowerTraining.POWER_TRAINING_ROUTE) {
            PowerTrainingDetailsScreen(navController, it.arguments?.getString("trainingId")!!)
        }
        composable(RouteConstants.Local.Tabs.PowerTraining.Exercises.EXERCISES_ROUTE) {
            PTExercisesScreen(trainingId = it.arguments?.getString("trainingId")!!, navController = navController)
        }
        composable(RouteConstants.Local.Tabs.PowerTraining.Packs.PACKS_ROUTE) {
            PTPacksScreen(trainingId = it.arguments?.getString("trainingId")!!, navController = navController)
        }
        composable(RouteConstants.Local.Tabs.PowerTraining.Dates.DATES_ROUTE) {
            PTDatesScreen(trainingId = it.arguments?.getString("trainingId")!!, navController = navController)
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
            navController.navigate(RouteConstants.Local.Tabs::class.java.name)
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

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun TabsScreen(navController: NavController, powerTrainingListViewModel: PowerTrainingListViewModel) {
    Scaffold(topBar = { AppBar(navController) },
             content = { Tabs(powerTrainingListViewModel, navController, it) },
             floatingActionButton = {
                 ExtendedFloatingActionButton(
                     onClick = { navController.navigate(RouteConstants.Local.Tabs.Create.CREATE_POWER) },
                     content = { Text(text = stringResource(R.string.fab_add)) }
                 )
             })
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
// https://www.freecodecamp.org/news/tabs-in-jetpack-compose/ - как сделать переключение свайпом
fun Tabs(
        powerTrainingListViewModel: PowerTrainingListViewModel,
        navController: NavController,
        paddingValues: PaddingValues
) {
    var tabIndex by remember { mutableIntStateOf(0) }
    val tabs = listOf(R.string.tab_screen_power, R.string.tab_screen_cardio)

    Column(modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)) {
        TabRow(selectedTabIndex = tabIndex) {
            tabs.forEachIndexed { index, tab ->
                Tab(
                    text = { Text(text = stringResource(tab)) },
                    selected = tabIndex == index,
                    onClick = { tabIndex = index }
                )
            }
        }
        when (tabIndex) {
            0 -> PowerTrainingList(navController, powerTrainingListViewModel)
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