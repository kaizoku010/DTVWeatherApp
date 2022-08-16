package com.hava.dvtweatherapp.data.network.apiCalls


data class Request(
    val language: String,
    val query: String,
    val type: String,
    val unit: String
)