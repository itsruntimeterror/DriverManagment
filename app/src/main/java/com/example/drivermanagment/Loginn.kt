package com.example.drivermanagment

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp


@Composable
fun LogInScreen(onLoginClicked: () -> Unit) {
    var text by remember { mutableStateOf(TextFieldValue("")) }


    Column (verticalArrangement = Arrangement.Center , horizontalAlignment = Alignment.CenterHorizontally){
        TextField( label = { Text(text = "Enter Your Email Please") },
            value = text,
            onValueChange = { newText ->
                text = newText
            } , modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(15.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        TextField( label = { Text(text = "Enter Your Password Please") },
            value = text,
            onValueChange = { newText ->
                text = newText
            } , modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .padding(15.dp)
        )

        Button(onClick = onLoginClicked) {
            Text(text = "Login")
        }
    }

}
