package dongwoongkim.crud.service;

import dongwoongkim.crud.domain.Board;
import dongwoongkim.crud.domain.Member;
import dongwoongkim.crud.dto.board.BoardEditRequestDto;
import dongwoongkim.crud.dto.board.BoardRequestDto;
import dongwoongkim.crud.dto.board.BoardResponseDto;
import dongwoongkim.crud.dto.member.MemberRequestDto;
import dongwoongkim.crud.dto.member.MemberResponseDto;
import dongwoongkim.crud.exception.BoardNotFoundException;
import dongwoongkim.crud.exception.MemberNotFoundException;
import dongwoongkim.crud.exception.WriteFailureException;
import dongwoongkim.crud.repository.BoardRepository;
import dongwoongkim.crud.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    // 전체 조회
    public List<BoardResponseDto> findAll() {
        List<Board> boards = boardRepository.findAll();
        return boards.stream().map(b -> BoardResponseDto.toDto(b)).collect(Collectors.toList());
    }

    public Page<BoardResponseDto> findAll(Pageable pageable) {
        Page<Board> page = boardRepository.findAll(pageable);
        return page.map(b -> BoardResponseDto.toDto(b));
    }

    // 조회
    public BoardResponseDto findById(Long id) {
        return BoardResponseDto.toDto(boardRepository.findById(id).orElseThrow(BoardNotFoundException::new));
    }

    // 저장
    @Transactional
    public BoardResponseDto save(BoardRequestDto boardRequestDto) {
        Board board = boardRepository.save(new Board(
                boardRequestDto.getTitle(),
                boardRequestDto.getContent(),
                boardRequestDto.getWriter()));

        if (board == null) {
            throw new WriteFailureException();
        }

        return BoardResponseDto.toDto(board);
    }

    // 수정
    @Transactional
    public BoardResponseDto update(Long id, BoardEditRequestDto boardEditRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(BoardNotFoundException::new);
        board.updateBoard(boardEditRequestDto);
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
