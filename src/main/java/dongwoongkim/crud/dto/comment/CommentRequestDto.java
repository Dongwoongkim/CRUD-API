package dongwoongkim.crud.dto.comment;

import dongwoongkim.crud.domain.Board;
import dongwoongkim.crud.domain.Comment;
import dongwoongkim.crud.domain.Member;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class CommentRequestDto {

    @NotBlank(message = "댓글을 입력해주세요.")
    private String comment;
    private Board board;
    private Member member;

    public static CommentRequestDto toDto(Comment comment) {
        return new CommentRequestDto(comment.getComment(), comment.getBoard(), comment.getMember());
    }
}
