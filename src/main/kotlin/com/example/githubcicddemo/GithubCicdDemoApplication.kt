package com.example.githubcicddemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class GithubCicdDemoApplication

fun main(args: Array<String>) {
    runApplication<GithubCicdDemoApplication>(*args)
}
