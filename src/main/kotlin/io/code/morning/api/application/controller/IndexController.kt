package io.code.morning.api.application.controller

import io.code.morning.api.application.resource.IndexResource
import io.code.morning.api.domain.service.IndexService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IndexController(
        private val indexService: IndexService
) {

    @GetMapping("/")
    fun get(): ResponseEntity<IndexResource> {

        val resource = IndexResource(
                message = "Hello Morning Code API.",
                blogs = indexService.makePageResponse()
        )

        return ResponseEntity(resource, HttpStatus.OK)
    }
}