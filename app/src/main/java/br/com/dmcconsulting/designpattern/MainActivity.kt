package br.com.dmcconsulting.designpattern

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import br.com.dmcconsulting.designpattern.features.home.presentation.HomeScreen
import br.com.dmcconsulting.designpattern.ui.theme.DesignPatternTheme
import br.com.dmcconsulting.notificationmanager.presentation.NotificationPermissionEffect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            DesignPatternTheme {
                NotificationPermissionEffect()
                HomeScreen()
            }
        }
    }
}
