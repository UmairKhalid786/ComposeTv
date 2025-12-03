package com.techlads.composetv

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.tv.material3.Card
import androidx.tv.material3.CardDefaults
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.OutlinedButton
import androidx.tv.material3.OutlinedButtonDefaults
import androidx.tv.material3.Text

@Composable
fun CustomDialog(openDialogCustom: MutableState<Boolean>, onExitClick: () -> Unit) {
    Dialog(onDismissRequest = { openDialogCustom.value = false }) {
        CustomDialogUI(openDialogCustom = openDialogCustom) {
            onExitClick()
        }
    }
}

// Layout
@Composable
fun CustomDialogUI(
    modifier: Modifier = Modifier,
    openDialogCustom: MutableState<Boolean>,
    onExitClick: () -> Unit,
) {
    Card(
        shape = CardDefaults.shape(RoundedCornerShape(10.dp)),
        modifier = Modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
        onClick = {}
    ) {
        Column(modifier.background(MaterialTheme.colorScheme.surface)) {
            // .......................................................................
            Image(
                painter = painterResource(id = R.drawable.ic_info),
                contentDescription = null, // decorative
                contentScale = ContentScale.Fit,
                colorFilter = ColorFilter.tint(
                    color = MaterialTheme.colorScheme.onSurface,
                ),
                modifier = Modifier
                    .padding(top = 35.dp)
                    .height(70.dp)
                    .fillMaxWidth(),

            )

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Confirmation",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.labelLarge,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = "Do you really want to exit app ?",
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            // .......................................................................
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
                    .background(MaterialTheme.colorScheme.background.copy(0.2F)),
            ) {
                OutlinedButton (onClick = {
                    openDialogCustom.value = false
                }, modifier = Modifier.weight(1F), shape = OutlinedButtonDefaults.shape(RoundedCornerShape(0.dp))) {
                    Text(
                        "No",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                    )
                }

//                Divider(
//                    Modifier
//                        .height(50.dp)
//                        .width(1.dp),
//                )

                OutlinedButton(onClick = {
                    openDialogCustom.value = false
                    onExitClick()
                }, modifier = Modifier.weight(1F), shape =  OutlinedButtonDefaults.shape(RoundedCornerShape(0.dp))) {
                    Text(
                        "Yes",
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp),
                    )
                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(name = "Custom Dialog")
@Composable
fun MyDialogUIPreview() {
    CustomDialogUI(openDialogCustom = mutableStateOf(false)) {}
}
