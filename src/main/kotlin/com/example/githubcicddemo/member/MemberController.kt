package com.example.githubcicddemo.member

import com.example.githubcicddemo.member.dto.MemberDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/member")
class MemberController(
    val memberService: MemberService,
) {

    @PostMapping
    fun createMember(@RequestBody request: MemberDto.CreateMember) {
        memberService.createMember(request)
    }

    @GetMapping
    fun getAllMembers(): List<MemberDto.MemberResponse> {
        return memberService.getAllMembers()
    }

    @GetMapping("/{id}")
    fun getMember(@PathVariable id: Long): MemberDto.MemberResponse {
        return memberService.getMember(id)
    }
}
