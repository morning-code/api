package io.code.morning.api.controller.resource

data class BlogPageResource(
    val id: String,
    val category: String,
    val title: String,
    val html: String
)
