package com.tdb.triaryapp.android.power

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tdb.triaryapp.android.RouteConstants
import com.tdb.triaryapp.android.power.viewmodel.PowerTrainingListViewModel
import com.tdb.triaryapp.entity.Training
import com.thedistantblue.triaryapp.theme.components.TriaryAppSwipeToDismissCard

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun PowerTrainingList(
        navController: NavController,
        viewModel: PowerTrainingListViewModel
) {
    val uiState = viewModel.uiState.value
    viewModel.getTrainings()

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(uiState.size) {
            TrainingListItem(uiState[it], navController, viewModel)
        }
    }
}

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TrainingListItem(
        training: Training,
        navController: NavController,
        viewModel: PowerTrainingListViewModel
) {
    val trainingId = training.id.toHexString()
    TriaryAppSwipeToDismissCard(
        onClickAction = { navController.navigate(RouteConstants.Local.Tabs.Edit.EDIT_POWER + trainingId) },
        onDismissedToEndAction = {},
        onDismissedToStartAction = {
            viewModel.deleteTraining(training)
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = training.name)
            Text(text = training.description)
        }
    }
}