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

/**
 * @author Nav Singh
 */
data class Pet(
    val id: Int = 0,
    val name: String,
    val breed: String,
    val age: String
) {
    companion object {
        fun listOfPets(): List<Pet> {
            val list = mutableListOf<Pet>()
            repeat(21) {
                list.add(
                    Pet(
                        it,
                        name = listOfPetNames[it],
                        breed = listOfBreeds[it],
                        age = "${it * 2} Days"
                    )
                )
            }
            return list
        }

        private val listOfPetNames = listOf(
            "Abby", "Abe", "Addie", "Abbott", "Alexis", "Ace", "Alice",
            "Aero", "Allie", "Aiden", "Alyssa", "AJ", "Amber", "Albert",
            "Angel", "Aspen", "Andy", "Athena", "Angus", "Autumn", "Apollo",
        )
        private val listOfBreeds = listOf(
            "Akbash", "Akita", "Alano Español", "Alapaha Blue Blood Bulldog",
            "Alaskan husky", "Alaskan Klee Kai", " Alaskan Malamute",
            "Alopekis", "Austrian Black and Tan Hound", "Austrian Pinscher",
            "Azawakh", "Bakharwal dog", "Banjara Hound", "Barbado da Terceira",
            "Bosnian Coarse-haired Hound", "Boston Terrier", "Bouvier des Ardennes", "Boxer",
            "Boykin Spaniel", "Bracco Italiano", "Braque d'Auvergne",
        )
    }
}
