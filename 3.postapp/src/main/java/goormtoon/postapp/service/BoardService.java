package goormtoon.postapp.service;

import goormtoon.postapp.dto.board.BoardListCommentDto;
import goormtoon.postapp.dto.board.BoardListResponseDto;
import goormtoon.postapp.dto.board.BoardRequestDto;
import goormtoon.postapp.dto.board.BoardDetailResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface BoardService {

    BoardListCommentDto getBoardById(Long boardId);

    List<BoardListResponseDto> getAllBoards(Pageable pageable);

    BoardDetailResponseDto saveBoard(BoardRequestDto board);

    Boolean deleteBoard(Long boardId);

    BoardDetailResponseDto updateBoard(Long boardId, BoardRequestDto board);


}
