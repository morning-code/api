package io.code.morning.api.usecase

import io.code.morning.api.domain.entity.BlogEntity
import io.code.morning.api.domain.entity.BlogId
import reactor.core.publisher.Mono

interface BlogUsecase {
  fun list(page: Int, size:Int): Mono<List<BlogEntity>>
  fun findById(blogId: BlogId): Mono<BlogEntity>
}