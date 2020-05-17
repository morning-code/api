package io.code.morning.api.infrastructure.repository

import io.code.morning.api.domain.entity.BlogEntity
import io.code.morning.api.domain.entity.BlogId

interface BlogRepository {

  fun list(): List<BlogEntity>
  fun findById(id: BlogId): BlogEntity
}