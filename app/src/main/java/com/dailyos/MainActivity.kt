package com.dailyos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material3.Scaffold
import com.dailyos.navigation.Screen
import com.dailyos.ui.navigation.BottomNavigationBar
import com.dailyos.ui.screens.preferences.PreferencesScreen
import com.dailyos.ui.screens.today.TodayScreen
import com.dailyos.ui.theme.DailyOSTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            DailyOSTheme {

                var currentScreen by remember {

                    mutableStateOf(
                        Screen.TODAY
                    )

                }

                Scaffold(

                    bottomBar = {

                        BottomNavigationBar(

                            currentScreen = currentScreen,

                            onScreenSelected = {

                                currentScreen = it

                            }

                        )

                    }

                ) { innerPadding ->

                    when (currentScreen) {

                        Screen.TODAY ->

                            TodayScreen(
                                innerPadding
                            )

                        Screen.PREFERENCES ->

                            PreferencesScreen(
                                innerPadding
                            )

                    }

                }
            }

        }

    }

}