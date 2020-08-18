package io.code.morning.api.controller

import io.code.morning.api.controller.resource.BlogPageResource
import io.code.morning.api.domain.entity.BlogId
import io.code.morning.api.usecase.BlogUsecase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/blog")
class BlogPageController(
    private val blogUsecase: BlogUsecase
) {

  @Operation(summary = "get blog list")
  @ApiResponses(value = [
    ApiResponse(responseCode = "200", description = "Success"),
    ApiResponse(responseCode = "400", description = "Bad request"),
    ApiResponse(responseCode = "401", description = "Unauthorized"),
    ApiResponse(responseCode = "500", description = "Internal server error")
  ])
  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = ["list"])
  suspend fun list(
      @RequestParam("page") page: Int,
      @RequestParam("size") size: Int
  ): ResponseEntity<List<BlogPageResource>> {

    val resource = blogUsecase.list(page, size).awaitSingle()
        .map {
          BlogPageResource(
              id = it.id!!.id,
              category = it.category!!,
              title = it.title!!,
              summary = it.summary!!,
              html = it.detail!!
          )
        }.toList()

    return ResponseEntity(resource, HttpStatus.OK)
  }

  @Operation(summary = "find blog by id")
  @ApiResponses(value = [
    ApiResponse(responseCode = "200", description = "Success"),
    ApiResponse(responseCode = "400", description = "Bad request"),
    ApiResponse(responseCode = "401", description = "Unauthorized"),
    ApiResponse(responseCode = "500", description = "Internal server error")
  ])
  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/{id}")
  suspend fun find(@PathVariable("id") id: String): ResponseEntity<BlogPageResource> {

    val resource = blogUsecase.findById(BlogId(id))
        .map {
          BlogPageResource(
              id = it.id!!.id,
              category = it.category!!,
              title = it.title!!,
              summary = it.summary!!,
              html = it.detail!!
          )
        }.awaitSingle()

    return ResponseEntity(resource, HttpStatus.OK)
  }
}