package dongwoongkim.crud.dto;

import dongwoongkim.crud.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String titleDto;
    private String contentDto;
    private String writerDto;
    private LocalDateTime createdDate;

    public static BoardResponseDto toDto(Board board) {
        return new BoardResponseDto(
                board.getId(),
                board.getTitle(),
                board.getContent(),
                board.getWriter(),
                board.getCreatedDate()
        );
    }
}
