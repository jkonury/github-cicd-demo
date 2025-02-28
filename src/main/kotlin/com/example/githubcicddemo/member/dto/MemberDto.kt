package com.example.githubcicddemo.member.dto

import com.example.githubcicddemo.member.Member
import java.time.LocalDateTime

object MemberDto {

    data class CreateMember(
        val name: String
    ) {
        fun toEntity(): Member {
            return Member(name = name)
        }
    }

    data class MemberResponse(
        val id: Long,
        val name: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime
    )
}
