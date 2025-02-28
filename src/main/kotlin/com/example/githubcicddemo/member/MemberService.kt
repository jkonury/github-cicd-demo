package com.example.githubcicddemo.member

import com.example.githubcicddemo.member.dto.MemberDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class MemberService(
    val memberRepository: MemberRepository,
) {

    @Transactional
    fun createMember(request: MemberDto.CreateMember) {
        memberRepository.save(request.toEntity())
    }

    fun getAllMembers(): List<MemberDto.MemberResponse> {
        return memberRepository.findAll()
            .map {
                MemberDto.MemberResponse(
                    id = it.id,
                    name = it.name,
                    createdAt = it.createdAt,
                    updatedAt = it.updatedAt
                )
            }
    }

    fun getMember(id: Long): MemberDto.MemberResponse {
        val member = memberRepository.findByIdOrNull(id) ?: throw NoSuchElementException("Member not found : $id")

        return with(member) {
            MemberDto.MemberResponse(
                id = id,
                name = name,
                createdAt = createdAt,
                updatedAt = updatedAt
            )
        }
    }
}
