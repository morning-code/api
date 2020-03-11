package io.code.morning.api.infrastructure.entity

import io.code.morning.api.domain.dto.BlogDto

data class BlogEntity(
        val title: String,
        val summary: String,
        val detail: String
) {
    fun toDto(): BlogDto {
        return BlogDto(
                title = this.title,
                summary = this.summary,
                detail = this.detail
        )
    }
}
