package io.code.morning.api.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "morningcode.services")
class MorningCodeServiceConfig {

  lateinit var blogServiceUrl: String
}