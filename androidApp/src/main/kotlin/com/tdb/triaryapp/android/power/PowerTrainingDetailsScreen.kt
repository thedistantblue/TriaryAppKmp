package com.tdb.triaryapp.android.power

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.tdb.triaryapp.android.AppBar
import com.tdb.triaryapp.android.R
import com.tdb.triaryapp.android.RouteConstants
import com.tdb.triaryapp.android.theme.components.TextRowWithRightArrow

@Composable
fun PowerTrainingDetailsScreen(navController: NavController, trainingId: String) {
    Scaffold(topBar = { AppBar(navController) },
             content = {
                 Column(modifier = Modifier
                         .fillMaxWidth()
                         .padding(it)) {
                     TextRowWithRightArrow(stringResource(R.string.training_detail_exercises)) {
                         navController.navigate(RouteConstants.Local.Tabs.PowerTraining.Exercises.EXERCISES + trainingId)
                     }
                     TextRowWithRightArrow(stringResource(R.string.training_detail_exercise_packs)) {
                         navController.navigate(RouteConstants.Local.Tabs.PowerTraining.Packs.PACKS + trainingId)
                     }
                     TextRowWithRightArrow(stringResource(R.string.training_detail_dates)) {
                        navController.navigate(RouteConstants.Local.Tabs.PowerTraining.Dates.DATES + trainingId)
                     }
                 }
             }
    )
}