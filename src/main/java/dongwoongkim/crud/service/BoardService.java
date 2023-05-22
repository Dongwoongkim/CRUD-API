package dongwoongkim.crud.service;

import dongwoongkim.crud.domain.Board;
import dongwoongkim.crud.dto.BoardEditRequestDto;
import dongwoongkim.crud.dto.BoardRequestDto;
import dongwoongkim.crud.dto.BoardResponseDto;
import dongwoongkim.crud.exception.BoardNotFoundException;
import dongwoongkim.crud.exception.UpdateFailureException;
import dongwoongkim.crud.exception.WriteFailureException;
import dongwoongkim.crud.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;

    // 전체 조회
    public List<BoardResponseDto> findAll() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream().map(b -> BoardResponseDto.toDto(b)).collect(Collectors.toList());
    }

    // 조회
    public BoardResponseDto findById(Long id) {
        return BoardResponseDto.toDto(boardRepository.findById(id).orElseThrow(BoardNotFoundException::new));
    }

    // 생성
    @Transactional
    public BoardResponseDto save(BoardRequestDto boardRequestDto) {
        Board board = boardRepository.save(new Board(boardRequestDto.getTitle(), boardRequestDto.getContent(), boardRequestDto.getWriter()));
        if (board == null) {
            throw new WriteFailureException();
        }
        return BoardResponseDto.toDto(board);
    }

    // 수정
    @Transactional
    public BoardResponseDto update(Long id, BoardEditRequestDto boardEditRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        board.updateBoard(boardEditRequestDto.getTitle(), boardEditRequestDto.getContent(), boardEditRequestDto.getWriter());
        Board updatedBoard = boardRepository.save(board);// merge 유도 (createdDate == null -> save , createdDate != null -> merge )
        if (updatedBoard == null) {
            throw new UpdateFailureException();
        }
        return BoardResponseDto.toDto(board);
    }

    // 삭제
    @Transactional
    public BoardResponseDto delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        boardRepository.delete(board);
        return BoardResponseDto.toDto(board);
    }
}
