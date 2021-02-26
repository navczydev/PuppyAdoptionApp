/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography

/**
 * @author Nav Singh
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
@Composable
fun MyApp() {
    val navController = rememberNavController()
    // Surface(color = MaterialTheme.colors.background) {
    NavHost(navController, startDestination = "PuppyList") {
        composable("PuppyList") { PuppiesListPage(navController) }
        composable(
            "PuppyDetails/{dogId}",
            arguments = listOf(navArgument("dogId") { type = NavType.IntType })
        ) { backStackEntry ->
            PuppyDetailsPage(backStackEntry.arguments?.getInt("dogId") ?: 0)
        }
    }
    // }
}

@Composable
fun PuppyDetailsPage(dogId: Int) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(
                when (dogId % 2 == 0) {
                    true -> R.drawable.dog2
                    else -> R.drawable.dog_clip
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp),
            contentScale = ContentScale.Fit
        )

        with(Pet.listOfPets()[dogId]) {
            Text(text = "Name: $name", style = typography.h4)
            Text(text = "Breed: $breed", style = typography.body1)
            Text(text = "Age: $age", style = typography.caption)
        }
    }
}

@Composable
fun PuppiesListPage(navController: NavController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Puppy adoption app",
            style = typography.h4,
            textAlign = TextAlign.Center,
        )
        LazyColumn(
            Modifier.padding(16.dp)
        ) {
            items(Pet.listOfPets().size) {
                PuppyItem(pet = Pet.listOfPets()[it], navController = navController)
            }
        }
    }
}

@Composable
fun PuppyItem(pet: Pet, navController: NavController) {
    Row(
        Modifier
            .clickable(
                enabled = true,
                onClick = {
                    navController.navigate("PuppyDetails/${pet.id}") {
                    }
                    Log.d("TAG", "Puppy: ${pet.age}")
                }
            )
            .border(2.dp, color = Color.Green, shape = CircleShape)
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(
                when (pet.id % 2 == 0) {
                    true -> R.drawable.dog2
                    else -> R.drawable.dog_clip
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .width(48.dp)
                .height(48.dp),
            contentScale = ContentScale.Fit
        )
        Spacer(
            modifier = Modifier
                .height(48.dp)
                .width(32.dp)
        )
        Column {
            Text(
                text = "Name: ${pet.name}",
                style = typography.h4
            )
            Text(
                text = "Breed: ${pet.breed}",
                style = typography.body1.copy(color = Color.Red)
            )
            Text(
                text = "Age: ${pet.age}",
                style = typography.body2.copy(color = Color.Blue)
            )
        }
    }
    Spacer(
        modifier = Modifier
            .height(4.dp)
            .fillMaxWidth()
    )
}

@Preview
@Composable
fun PuppyItemPreview() {
    val navController = rememberNavController()
    PuppyItem(
        pet = Pet(
            id = 1,
            name = "Dog",
            breed = "Bully",
            age = "40 days"
        ),
        navController = navController
    )
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
