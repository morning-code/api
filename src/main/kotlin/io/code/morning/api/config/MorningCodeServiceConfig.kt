package io.code.morning.api.config

import io.code.morning.api.config.properties.BlogServiceProperties
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "morningcode.services")
class MorningCodeServiceConfig {

  lateinit var blogServiceUrl: String
}