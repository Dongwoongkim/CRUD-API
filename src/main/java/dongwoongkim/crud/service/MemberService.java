package dongwoongkim.crud.service;

import dongwoongkim.crud.domain.Member;
import dongwoongkim.crud.dto.member.MemberEditRequestDto;
import dongwoongkim.crud.dto.member.MemberRequestDto;
import dongwoongkim.crud.dto.member.MemberResponseDto;
import dongwoongkim.crud.exception.MemberNotFoundException;
import dongwoongkim.crud.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    // 저장
    @Transactional
    public MemberResponseDto save(MemberRequestDto memberRequestDto) {
        Member member = memberRepository.save(new Member(memberRequestDto));
        return MemberResponseDto.toDto(member);
    }

    // 수정
    @Transactional
    public MemberResponseDto update(Long id, MemberEditRequestDto memberEditRequestDto) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        member.updateMember(memberEditRequestDto);
        return MemberResponseDto.toDto(member);
    }

    public MemberResponseDto findById(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(MemberNotFoundException::new);
        return MemberResponseDto.toDto(member);
    }
}