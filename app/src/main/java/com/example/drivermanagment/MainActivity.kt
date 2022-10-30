package com.example.drivermanagment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.drivermanagment.ui.theme.DriverManagmentTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = { SimpleAppBar() },
                backgroundColor = colorResource(id = R.color.teal_200)
            ) { padding -> // We need to pass scaffold's inner padding to content. That's why we use Box.
                Box(modifier = Modifier.padding(padding)) {
                    Navigation()
                }
            }
        }
    }
}


@Composable
fun SimpleAppBar(){
    Column {
        TopAppBar(
            title = { Text(text = "Drivers") }

        )
    }



}



@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "loginscreen") {
        composable("main") {
            MainScreen(navController = navController)
        }

        composable("loginscreen"){
            LogInScreen({ navController.navigate("main") })
        }
        composable(
            "details/{driverName}",
            arguments = listOf(navArgument("driverName") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("driverName")?.let { driverName ->
                DetailsScreen(driverName = driverName)
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavController) {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    Column {
        SearchView(textState)
        DriversList(navController = navController, state = textState)
    }
}


