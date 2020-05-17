package io.code.morning.api.controller.controller

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.code.morning.api.controller.resource.IndexResource
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit.jupiter.SpringExtension


@ExtendWith(SpringExtension::class)
@SpringBootTest
@AutoConfigureMockMvc
class IndexControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private val mapper = jacksonObjectMapper()

    /*
    @MockK
    private lateinit var indexService: IndexService
     */

    @Test
    fun `response data contains blog list`() {

        val expected = IndexResource(
                message = "Hello Morning Code API.",
                blogs = listOf(BlogDto(
                        title = "title",
                        summary = "summary",
                        detail = "detail"
                ))
        )

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk)
                .andExpect(content().json(mapper.writeValueAsString(expected)))
    }
}