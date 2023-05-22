package dongwoongkim.crud.web;


import dongwoongkim.crud.dto.BoardEditRequestDto;
import dongwoongkim.crud.dto.BoardRequestDto;
import dongwoongkim.crud.dto.BoardResponseDto;
import dongwoongkim.crud.response.Response;
import dongwoongkim.crud.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    // 전체 조회
    @GetMapping
    public Response getBoards() {
        List<BoardResponseDto> boardResponseDtos = boardService.findAll();
        return Response.success(boardResponseDtos);
    }

    // 단건 조회
    @GetMapping("/{id}")
    public Response getBoard(@PathVariable Long id) {
        BoardResponseDto boardResponseDto = boardService.findById(id);
        return Response.success(boardResponseDto);
    }

    // 등록
    @PostMapping
    public Response save(@Valid @RequestBody BoardRequestDto boardRequestDto) {
        BoardResponseDto boardResponseDto = boardService.save(boardRequestDto);
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
