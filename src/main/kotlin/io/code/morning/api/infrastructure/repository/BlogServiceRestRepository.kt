package io.code.morning.api.infrastructure.repository

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.result.Result.Failure
import com.github.kittinunf.result.Result.Success
import io.code.morning.api.config.MorningCodeServiceConfig
import io.code.morning.api.domain.entity.BlogEntity
import io.code.morning.api.domain.entity.BlogId
import org.springframework.stereotype.Repository

@Repository
class BlogServiceRestRepository(
    private val morningCodeServiceConfig: MorningCodeServiceConfig
) : BlogRepository {

  override fun findById(id: BlogId): BlogEntity {
    val mapper = jacksonObjectMapper()
    val url = "${morningCodeServiceConfig.blogServiceUrl}/v1/blog/${id.id}"

    val (_, _, response) = Fuel.get(url).responseString()

    when (response) {
      is Failure -> {
        throw response.getException()
      }
      is Success -> {
        return mapper.readValue(response.get())
      }
    }
  }

  override fun list(): List<BlogEntity> {
    val blogs = listOf(BlogEntity(
        title = "title",
        summary = "summary",
        detail = "detail"
    ))

    return blogs.map { it }
  }


}