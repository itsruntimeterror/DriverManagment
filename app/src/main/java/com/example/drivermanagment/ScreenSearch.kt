package com.example.drivermanagment

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlin.collections.ArrayList


@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth(),
        textStyle = TextStyle(color = Color.White, fontSize = 18.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "",
                modifier = Modifier
                    .padding(15.dp)
                    .size(24.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value =
                            TextFieldValue("")
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            cursorColor = Color.White,
            leadingIconColor = Color.White,
            trailingIconColor = Color.White,
            backgroundColor = colorResource(id = R.color.purple_500),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Preview(showBackground = true)
@Composable
fun SearchViewPreview() {
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    SearchView(textState)
}

@Composable
fun DriverListItem(driverText: String, onItemClick: (String) -> Unit) {
    Row(
        modifier = Modifier
            .clickable(onClick = { onItemClick(driverText) })
            .background(colorResource(id = R.color.purple_700))
            .height(57.dp)
            .fillMaxWidth()
            .padding(PaddingValues(8.dp, 16.dp))
    ) {
        Text(text = driverText, fontSize = 18.sp, color = Color.White)
    }
}

@Preview(showBackground = true)
@Composable
fun CountryListItemPreview() {
    DriverListItem(driverText = "Hamid ", onItemClick = { })
}

@Composable
fun DriversList(navController: NavController, state: MutableState<TextFieldValue>) {
    val drivers = getListOfDrivers()
    var filteredDrivers: ArrayList<String>
    LazyColumn(modifier = Modifier.fillMaxWidth()) {
        val searchedText = state.value.text
        filteredDrivers = if (searchedText.isEmpty()) {
            drivers
        } else {
            val resultList = ArrayList<String>()
            for (driver in drivers) {
                if (driver.lowercase()
                        .contains(searchedText.lowercase())
                ) {
                    resultList.add(driver)
                }
            }
            resultList
        }
        items(filteredDrivers) { filteredDriver ->
            DriverListItem(
                driverText = filteredDriver,
                onItemClick = { selectedDriver ->
                    navController.navigate("details/$selectedDriver") {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo("main") {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}
fun getListOfDrivers(): ArrayList<String> {
    val driversNames  = arrayListOf<String>("hamid ", "hacine", "Hakim" , "Omar " , "bolmdayes" , "ayoub" ,
        "lala" , " noioij" , " afdaf" , "eF" , "Choasfdsvuaib" ,
        "mhbdcsdF" , " BNVDBSK" , " mnl" , ",jkagsf" , "jyate",
        "EEWF" , " kdjhfu" , " jhsgd" , "hjgdfy" , "bcau",
        "ngsau" , " nmbsy" , " nmbcu" , "adfuy" , "kuy" ,
        "jkhd" , " kuyef" , " efkhu" , "wfdku" , "kjhf" , "uyqe" , " bbjshu" , " bcsg" , "jhdg" , "jhscu",
        "hsgdcyqa" , " chgd" , " sdchg" , "sdhgusygj" , "mbshcg",  "mabcshg" , " mhbschg" , " mnabc" , "nbasch" , "mnhav"
    , "nbasch" , " mhbd" , " bdc" , "jbh" , "mjhbd", "mhbadh" , " mnad" , " jhagd" , "hadgy" , "hadh")

    return driversNames
}