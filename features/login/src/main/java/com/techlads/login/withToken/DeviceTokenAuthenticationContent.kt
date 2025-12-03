package com.techlads.login.withToken

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.drawable.toBitmap
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.github.alexzhirkevich.customqrgenerator.QrData
import com.github.alexzhirkevich.customqrgenerator.vector.QrCodeDrawable
import com.github.alexzhirkevich.customqrgenerator.vector.QrVectorOptions
import com.github.alexzhirkevich.customqrgenerator.vector.style.QrVectorColor
import com.github.alexzhirkevich.customqrgenerator.vector.style.QrVectorColors
import com.techlads.uicomponents.widgets.TvButton

@Composable
fun DeviceTokenAuthenticationContent(
    token: String,
    url: String,
    skip: () -> Unit,
    onLoginClick: (token: String) -> Unit,
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
                SpanStyle(
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Normal,
                ),
                start = start,
                end = start + url.length,
            ),
        )
        Text(
            text = AnnotatedString(text = globalText, spanStyles = spanStyles),
            style = TextStyle(
                fontSize = 30.sp,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Thin,
            ),
        )

        Spacer(modifier = Modifier.height(40.dp))

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val white = QrVectorColor.Solid(
                android.graphics.Color.LTGRAY,
            )
            val qrCodeView = QrCodeDrawable(
                context,
                QrData.Url(url),
                QrVectorOptions.Builder().setColors(
                    colors = QrVectorColors(
                        ball = white,
                        frame = white,
                        dark = white,
                    ),
                ).build(),
            )

            Text(
                text = token,
                style = TextStyle(
                    fontSize = 100.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Thin,
                ),
            )

            Spacer(modifier = Modifier.width(60.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val color = Color.DarkGray
                Divider(
                    color = color,
                    modifier = Modifier
                        .height(65.dp)
                        .width(1.dp),
                )

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "OR",
                    style = TextStyle(
                        fontSize = 35.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.ExtraLight,
                        color = Color.LightGray,
                    ),
                )

                Spacer(modifier = Modifier.height(10.dp))

                Divider(
                    color = color,
                    modifier = Modifier
                        .height(65.dp)
                        .width(1.dp),
                )
            }

            Spacer(modifier = Modifier.width(70.dp))

            Image(
                bitmap = qrCodeView.toBitmap(400, 400).asImageBitmap(),
                contentDescription = "QR Code for URL $url",
            )
        }

        Spacer(modifier = Modifier.height(50.dp))

        Row(
            modifier = Modifier
                .padding(40.dp, 0.dp, 40.dp, 0.dp),
        ) {
            TvButton(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 20.dp, end = 20.dp),
                onClick = { onLoginClick(token) },
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = "Login with Email",
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center,
                    ),
                )
            }

            TvButton(
                modifier = Modifier
                    .weight(1f)
//                    .testTag(SKIP_TAG)
                    .padding(start = 20.dp, end = 20.dp),
                colors = ButtonDefaults.colors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    contentColor = MaterialTheme.colorScheme.onSurface,
                ),
                onClick = { skip() },
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth().clickable {
                        skip()
                    },
                    text = "Skip",
                    style = TextStyle(
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center,
                    ),
                )
            }
        }
    }
}


@Preview
@Composable
fun DeviceTokenAuthenticationContentPreview() {
    MaterialTheme {
        DeviceTokenAuthenticationContent(token = "OTF2", "www.google.com", skip = {}, onLoginClick = {})
    }
}
