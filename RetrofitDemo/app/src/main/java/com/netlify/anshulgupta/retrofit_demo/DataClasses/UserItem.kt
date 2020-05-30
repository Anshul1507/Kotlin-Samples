package com.netlify.anshulgupta.retrofit_demo.DataClasses

import com.squareup.moshi.Json


data class UserItem(
    val address: Address,
    val company: Company,
    @Json(name = "email")val email_address: String,
    val id: Int,
    val name: String,
    val phone: String,
    val username: String,
    val website: String
)