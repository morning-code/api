package io.code.morning.api.controller.resource

import io.code.morning.api.domain.entity.BlogEntity

data class IndexResource(
        val message: String,
        val blogs: List<BlogEntity>
)
