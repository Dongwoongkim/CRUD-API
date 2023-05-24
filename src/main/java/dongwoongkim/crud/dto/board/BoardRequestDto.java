package dongwoongkim.crud.dto.board;

import dongwoongkim.crud.domain.Board;
import dongwoongkim.crud.domain.Comment;
import dongwoongkim.crud.domain.Member;
import dongwoongkim.crud.dto.member.MemberRequestDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequestDto {

    private Long id;

    @NotBlank(message = "게시글 제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "게시글 내용을 입력해주세요.")
    private String content;

    private Member member;

    public static BoardRequestDto toDto(Board board) {
        return new BoardRequestDto(board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getMember());
    }

}
