package com.techlads.composetv.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import com.github.alexzhirkevich.customqrgenerator.QrData
import com.github.alexzhirkevich.customqrgenerator.vector.QrCodeDrawable
import com.github.alexzhirkevich.customqrgenerator.vector.QrVectorOptions
import com.github.alexzhirkevich.customqrgenerator.vector.style.*
import com.techlads.composetv.theme.AppTheme

@Composable
fun DeviceTokenAuthenticationContent(
    token: String,
    url: String,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onLoginClick: (token: String) -> Unit
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier.padding(horizontal = 150.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        val globalText = "Login through web using $url and provide code or just Scan QR"

        val start = globalText.indexOf(url)
        val spanStyles = listOf(
            AnnotatedString.Range(
                SpanStyle(fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal),
                start = start,
                end = start + url.length
            )
        )
        Text(
            text = AnnotatedString(text = globalText, spanStyles = spanStyles),
            style = TextStyle(
                fontSize = 30.sp, fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Thin
            )
        )

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val white = QrVectorColor.Solid(
                android.graphics.Color.LTGRAY
            )
            val qrCodeView = QrCodeDrawable(
                context,
                QrData.Url(url), QrVectorOptions.Builder().setColors(
                    colors = QrVectorColors(
                        ball = white,
                        frame = white,
                        dark = white
                    )
                ).build()
            )

            Text(
                text = token,
                style = TextStyle(
                    fontSize = 100.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Thin
                )
            )

            Spacer(modifier = Modifier.width(60.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val color = Color.DarkGray
                Divider(
                    color = color,
                    modifier = Modifier
                        .height(65.dp)
                        .width(1.dp)
                )


                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "OR",
                    style = TextStyle(
                        fontSize = 35.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraLight,
                        color = Color.LightGray
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                Divider(
                    color = color,
                    modifier = Modifier
                        .height(65.dp)
                        .width(1.dp)
                )
            }

            Spacer(modifier = Modifier.width(70.dp))

            Image(
                bitmap = qrCodeView.toBitmap(400, 400).asImageBitmap(),
                contentDescription = "QR Code for URL $url"
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Box(
            modifier = Modifier
                .width(300.dp)
                .padding(40.dp, 0.dp, 40.dp, 0.dp)
        ) {
            Button(
                onClick = { onLoginClick(token) },
                shape = AppTheme.midShape,
                colors = ButtonDefaults.buttonColors(Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .onFocusChanged { state ->
                        if (state.isFocused) {
                            println("login")
                        }
                    }
                    .focusable(true, interactionSource)
            ) {
                Text(
                    text = "WITH KEYBOARD", style = TextStyle(
                        color = Color.Black,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light
                    )
                )
            }
        }
    }
}