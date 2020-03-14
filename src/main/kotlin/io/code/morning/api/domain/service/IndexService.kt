package io.code.morning.api.domain.service

import io.code.morning.api.domain.dto.BlogDto
import io.code.morning.api.domain.repository.BlogRepository
import org.springframework.stereotype.Service

@Service
class IndexService(
        private val blogRepository: BlogRepository
) {

    fun makePageResponse(): List<BlogDto> {

        val blogs = blogRepository.list()

        return blogs
    }
}