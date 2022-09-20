package com.example.hilttut.model

import com.squareup.moshi.Json
import java.io.Serializable

data class Pokemon (@field:Json(name="name") val name: String,
                    @field:Json(name="url") val url: String
                    ): Serializable