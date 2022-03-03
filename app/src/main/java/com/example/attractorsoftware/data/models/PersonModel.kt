package com.example.attractorsoftware.data.models

data class PersonModel(
    val first_name: String,
    val second_name: String,
    val photo: String,
    val education: String,
    val company: List<CompaniesModel>
)