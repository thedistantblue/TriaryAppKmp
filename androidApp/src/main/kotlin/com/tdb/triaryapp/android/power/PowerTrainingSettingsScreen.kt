package com.tdb.triaryapp.android.power

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.tdb.triaryapp.android.R
import com.tdb.triaryapp.android.power.viewmodel.PowerTrainingViewModel
import com.tdb.triaryapp.entity.Training
import com.tdb.triaryapp.android.theme.components.NameDescriptionScreen
import org.mongodb.kbson.ObjectId

@Composable
fun PowerTrainingSettingsScreen(
        navController: NavController,
        trainingId: String? = null,
        viewModel: PowerTrainingViewModel,
) {
    val context = LocalContext.current
    val uiState = viewModel.uiState.value

    var nameState by remember { mutableStateOf("") }
    var descriptionState by remember { mutableStateOf("") }

    if (trainingId == null) {
        NameDescriptionScreen(nameHint = R.string.training_name,
                              descriptionHint = R.string.training_description,
                              buttonHint = R.string.training_create,
                              name = nameState,
                              description = descriptionState,
                              onNameChanged = { nameState = it },
                              onDescriptionChanged = { descriptionState = it }
        ) { name, description ->
            val training = Training(name, description)
            viewModel.createTraining(training)
            returnAndShowToast(context, navController, R.string.training_created_toast)
        }
    } else {
        viewModel.getTraining(ObjectId.invoke(trainingId))
        NameDescriptionScreen(nameHint = R.string.training_name,
                              descriptionHint = R.string.training_description,
                              buttonHint = R.string.training_save,
                              name = uiState.name,
                              description = uiState.description,
                              onNameChanged = { viewModel.updateTrainingName(it) },
                              onDescriptionChanged = { viewModel.updateTrainingDescription(it) }
        ) { name, description ->
            val training = Training(ObjectId.invoke(trainingId), name, description)
            viewModel.saveTraining(training)
            returnAndShowToast(context, navController, R.string.training_saved_toast)
        }
    }
}

private fun returnAndShowToast(context: Context, navController: NavController, toastText: Int) {
    navController.popBackStack()
    Toast.makeText(context, toastText, Toast.LENGTH_SHORT).show()
}