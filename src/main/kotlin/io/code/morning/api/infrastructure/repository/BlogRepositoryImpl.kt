package io.code.morning.api.infrastructure.repository

import io.code.morning.api.domain.dto.BlogDto
import io.code.morning.api.domain.repository.BlogRepository
import io.code.morning.api.infrastructure.entity.BlogEntity
import org.springframework.stereotype.Repository

@Repository
class BlogRepositoryImpl : BlogRepository {

    override fun list(): List<BlogDto> {
        val blogs = listOf(BlogEntity(
                title = "title",
                summary = "summary",
                detail = "detail"
        ))

        return blogs.map { it.toDto() }
    }
}