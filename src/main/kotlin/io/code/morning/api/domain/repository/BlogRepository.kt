package io.code.morning.api.domain.repository

import io.code.morning.api.domain.dto.BlogDto

interface BlogRepository {

    fun list(): List<BlogDto>
}