package io.code.morning.api.infrastructure.repository

import io.code.morning.api.domain.entity.BlogEntity
import io.code.morning.api.domain.entity.BlogId

interface BlogRepository {

  fun list(page: Int, size: Int): List<BlogEntity>
  fun findById(id: BlogId): BlogEntity
}