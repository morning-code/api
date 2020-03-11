package io.code.morning.api.application.resource

import io.code.morning.api.domain.dto.BlogDto

data class IndexResource(
        val message: String,
        val blogs: List<BlogDto>
)
