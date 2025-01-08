package com.techlads.composetv.features.settings.screens.about

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.tv.material3.LocalContentColor
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import com.techlads.composetv.features.settings.data.SettingsMenuModel
import com.techlads.composetv.features.settings.screens.PreferencesContainer

@Composable
fun AboutScreen() {
    PreferencesContainer(preference = SettingsMenuModel("About", "about_me")) {
        Text(
            modifier = Modifier,
            text = "Hello! I'm Umair Khalid,\nA software developer with a passion for open source development. I have a strong background in programming languages and technologies, and I'm constantly learning and improving my skills. I believe in the power of open source development to create innovative solutions and make a positive impact on society. That's why I have contributed to several open source projects, which you can find on my GitHub profile at https://github.com/UmairKhalid786. I'm also active on LinkedIn, where you can learn more about my experience, education, and interests in software development: https://www.linkedin.com/in/umairkhalid786/. I'm always looking for new opportunities to collaborate with other developers and create meaningful solutions, so feel free to connect with me on LinkedIn or check out my projects on GitHub.",
            color = LocalContentColor.current.copy(alpha = 0.4f),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
private fun AboutScreenPrev() {
    AboutScreen()
}
