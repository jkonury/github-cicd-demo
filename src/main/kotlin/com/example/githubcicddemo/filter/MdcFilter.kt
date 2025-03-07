package com.example.githubcicddemo.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.UUID

@Component
class MdcFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val ip = request.getHeader("x-original-forwarded-for") ?: request.remoteAddr
        MDC.put("ip", ip)
        MDC.put("traceId", UUID.randomUUID().toString())
        filterChain.doFilter(request, response)
        MDC.clear()
    }
}
