package dongwoongkim.crud.web;


import dongwoongkim.crud.dto.board.BoardEditRequestDto;
import dongwoongkim.crud.dto.board.BoardRequestDto;
import dongwoongkim.crud.dto.board.BoardResponseDto;
import dongwoongkim.crud.dto.member.MemberResponseDto;
import dongwoongkim.crud.response.Response;
import dongwoongkim.crud.service.BoardService;
import dongwoongkim.crud.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;

    // 전체 조회 ( 페이징 )
    @GetMapping
    public Response getBoards(@PageableDefault(sort = "id", size = 5, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<BoardResponseDto> pages = boardService.findAll(pageable);
        return Response.success(pages);
    }

    // 단건 조회
    @GetMapping("/{id}")
    public Response getBoard(@PathVariable Long id) {
        BoardResponseDto boardResponseDto = boardService.findById(id);
        return Response.success(boardResponseDto);
    }

    // 등록
    @PostMapping
    public Response save(@Valid @RequestBody BoardRequestDto boardRequestDto, @RequestParam String nickName) {
        MemberResponseDto memberResponseDto = memberService.findByNickname(nickName);
        BoardResponseDto boardResponseDto = boardService.save(boardRequestDto,nickName);
        return Response.success(boardResponseDto);
    }

    // 수정
    @PutMapping("/{id}")
    public Response edit(@PathVariable Long id, @Valid @RequestBody BoardEditRequestDto boardEditRequestDto) {
        BoardResponseDto boardResponseDto = boardService.update(id, boardEditRequestDto);
        return Response.success(boardResponseDto);
    }

    // 삭제
    @DeleteMapping("/{id}")
    public Response delete(@PathVariable Long id) {
        boardService.delete(id);
        return Response.success("삭제 완료");
    }

}
