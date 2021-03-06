package me.androidbox.todocompose.component

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import me.androidbox.todocompose.R

@Composable
fun DisplayAlertDialog(
    title: String,
    message: String,
    shouldOpenDialog: Boolean,
    closeDialog: () -> Unit,
    onYesClicked: () -> Unit
) {

    if(shouldOpenDialog) {
        AlertDialog(
            title = {
                Text(
                    text = title,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold)
            },
            text = {
                Text(
                    text = message,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal)
            },
            confirmButton = {
                Button(onClick = {
                    onYesClicked()
                    closeDialog()
                }) {
                    Text(text = stringResource(R.string.yes), fontSize = MaterialTheme.typography.button.fontSize)
                }
            },
            dismissButton = {
                OutlinedButton(onClick = {
                    closeDialog()
                }) {
                    Text(text = stringResource(R.string.no), fontSize = MaterialTheme.typography.button.fontSize)
                }
            },
            onDismissRequest = {
                closeDialog()
            })
    }
}
