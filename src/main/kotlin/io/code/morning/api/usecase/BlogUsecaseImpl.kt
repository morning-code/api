package io.code.morning.api.usecase

import io.code.morning.api.domain.entity.BlogEntity
import io.code.morning.api.domain.entity.BlogId
import io.code.morning.api.infrastructure.repository.BlogRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class BlogUsecaseImpl(
    private val blogRepository: BlogRepository
) : BlogUsecase {

  override fun list(page: Int, size: Int): Mono<List<BlogEntity>> {
    return Mono.just(blogRepository.list(page, size))
  }

  override fun findById(blogId: BlogId): Mono<BlogEntity> =
      blogId.let {
        Mono.just(blogRepository.findById(id = it))
      }
}