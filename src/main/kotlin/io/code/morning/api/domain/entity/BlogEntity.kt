package io.code.morning.api.domain.entity

data class BlogEntity(
    val id: BlogId,
    val category: String? = null,
    val title: String? = null,
    val summary: String? = null,
    val detail: String? = null
)