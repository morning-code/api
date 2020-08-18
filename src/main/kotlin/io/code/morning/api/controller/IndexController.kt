package io.code.morning.api.controller

import io.code.morning.api.controller.resource.BlogPageResource
import io.code.morning.api.controller.resource.IndexResource
import io.code.morning.api.usecase.BlogUsecase
import io.code.morning.api.usecase.IndexUsecaseImpl
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/")
class IndexController(
    private val indexService: IndexUsecaseImpl,
    private val blogUsecase: BlogUsecase
) {

  @Operation(summary = "root api")
  @ApiResponses(value = [
    ApiResponse(responseCode = "200", description = "Success"),
    ApiResponse(responseCode = "400", description = "Bad request"),
    ApiResponse(responseCode = "401", description = "Unauthorized"),
    ApiResponse(responseCode = "500", description = "Internal server error")
  ])
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = [""])
  suspend fun get(): ResponseEntity<IndexResource> {
    val blogs = blogUsecase.list(0, 10).awaitSingle()
    val resource = IndexResource(
        message = "MorningCode.io",
        blogs = blogs
    )
    return ResponseEntity(resource, HttpStatus.OK)
  }

  @Operation(summary = "ping")
  @ApiResponses(value = [
    ApiResponse(responseCode = "200", description = "Success"),
    ApiResponse(responseCode = "400", description = "Bad request"),
    ApiResponse(responseCode = "401", description = "Unauthorized"),
    ApiResponse(responseCode = "500", description = "Internal server error")
  ])
  @ResponseStatus(HttpStatus.CREATED)
  @GetMapping(value = ["/ping"])
  suspend fun ping(): String = Mono.just("pong").awaitSingle()
}