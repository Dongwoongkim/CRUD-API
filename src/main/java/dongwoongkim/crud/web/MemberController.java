package dongwoongkim.crud.web;

import dongwoongkim.crud.domain.Member;
import dongwoongkim.crud.dto.member.MemberEditRequestDto;
import dongwoongkim.crud.dto.member.MemberRequestDto;
import dongwoongkim.crud.dto.member.MemberResponseDto;
import dongwoongkim.crud.response.Response;
import dongwoongkim.crud.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    // 저장
    @PostMapping("/save")
    public Response save(@Valid @RequestBody MemberRequestDto memberRequestDto) {
        MemberResponseDto memberResponseDto = memberService.save(memberRequestDto);
        return Response.success(memberResponseDto);
    }

    // 수정
    @PatchMapping("/patch/{id}")
    public Response update(@PathVariable Long id, @Valid @RequestBody MemberEditRequestDto memberEditRequestDto) {
        MemberResponseDto memberResponseDto = memberService.update(id, memberEditRequestDto);
        return Response.success(memberResponseDto);
    }

}
