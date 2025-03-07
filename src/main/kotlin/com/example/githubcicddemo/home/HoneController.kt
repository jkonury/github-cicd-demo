package com.example.githubcicddemo.home

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.net.InetAddress

@RestController
class HoneController {
    val log = KotlinLogging.logger {}

    @GetMapping("/")
    fun home(): Map<String, String> {
        val hostname = InetAddress.getLocalHost().hostName
        val ip = InetAddress.getLocalHost().hostAddress

        log.info { "hostname: $hostname, ip: $ip" }

        return mapOf(
            "hostname" to hostname,
            "ip" to ip,
        )
    }
}
