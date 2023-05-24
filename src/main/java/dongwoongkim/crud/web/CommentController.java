package dongwoongkim.crud.web;

import dongwoongkim.crud.dto.board.BoardResponseDto;
import dongwoongkim.crud.dto.comment.CommentRequestDto;
import dongwoongkim.crud.dto.comment.CommentResponseDto;
import dongwoongkim.crud.dto.member.MemberRequestDto;
import dongwoongkim.crud.dto.member.MemberResponseDto;
import dongwoongkim.crud.response.Response;
import dongwoongkim.crud.service.BoardService;
import dongwoongkim.crud.service.CommentService;
import dongwoongkim.crud.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class CommentController {

    private final BoardService boardService;
    private final CommentService commentService;
    private final MemberService memberService;

    // 조회
    @GetMapping("/{boardId}/comment")
    public Response read(@PathVariable Long boardId) {
        List<CommentResponseDto> commentResponseDtos = commentService.findAll(boardId);
        return Response.success(commentResponseDtos);
    }

    // 등록
    @PostMapping("/{boardId}/comment")
    public Response save(@PathVariable Long boardId, @Valid @RequestBody CommentRequestDto commentRequestDto,
                         @RequestParam String nickName) {
        BoardResponseDto boardResponseDto = boardService.findById(boardId);
        MemberResponseDto memberResponseDto = memberService.findByNickname(nickName);
        CommentResponseDto commentResponseDto = commentService.save(commentRequestDto, boardId, nickName);
        return Response.success();
    }
}
