package com.example.testprototype

import java.io.Serializable

data class Gunpla(
    val title: String,
    val imageResourceId: Int,
    val desc: Int,
    val scale: Int,
    val series:String,
    val type: List<String>
): Serializable