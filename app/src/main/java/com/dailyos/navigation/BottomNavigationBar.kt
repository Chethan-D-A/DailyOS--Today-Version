package com.dailyos.ui.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.dailyos.navigation.Screen

@Composable
fun BottomNavigationBar(

    currentScreen: Screen,

    onScreenSelected: (Screen) -> Unit

) {

    NavigationBar {

        NavigationBarItem(

            selected = currentScreen == Screen.TODAY,

            onClick = {

                onScreenSelected(
                    Screen.TODAY
                )

            },

            label = {

                Text("Today")

            },

            icon = {}

        )

        NavigationBarItem(

            selected = currentScreen == Screen.PREFERENCES,

            onClick = {

                onScreenSelected(
                    Screen.PREFERENCES
                )

            },

            label = {

                Text("Preferences")

            },

            icon = {}

        )

    }

}