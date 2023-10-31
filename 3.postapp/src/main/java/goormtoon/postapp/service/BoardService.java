package goormtoon.postapp.service;

import goormtoon.postapp.dto.board.BoardListCommentDto;
import goormtoon.postapp.dto.board.BoardRequestDto;
import goormtoon.postapp.dto.board.BoardResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;



public interface BoardService {

    BoardListCommentDto getBoardById(Long boardId);

    Page<BoardResponseDto> getAllBoards(Pageable pageable);

    BoardResponseDto saveBoard(BoardRequestDto board);

    Boolean deleteBoard(Long boardId);

    BoardResponseDto updateBoard(Long boardId, BoardRequestDto board);


}
