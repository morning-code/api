package io.code.morning.api.usecase

import io.code.morning.api.domain.entity.BlogEntity
import io.code.morning.api.infrastructure.repository.BlogRepository
import org.springframework.stereotype.Service

@Service
class IndexUsecaseImpl(
        private val blogRepository: BlogRepository
) {

    fun makePageResponse(): List<BlogEntity> {

        val blogs = blogRepository.list(0, 20)

        return blogs
    }
}