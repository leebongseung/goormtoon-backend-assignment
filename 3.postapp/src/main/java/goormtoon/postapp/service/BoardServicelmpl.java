package goormtoon.postapp.service;

import goormtoon.postapp.domain.Board;
import goormtoon.postapp.dto.board.BoardListCommentDto;
import goormtoon.postapp.dto.board.BoardListResponseDto;
import goormtoon.postapp.dto.board.BoardRequestDto;
import goormtoon.postapp.dto.board.BoardDetailResponseDto;
import goormtoon.postapp.repository.BoardRepository;
import goormtoon.postapp.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BoardServicelmpl implements BoardService{
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;


    @Override
    public BoardListCommentDto getBoardById(Long boardId) { // 게시글 조회
        Board findBoard = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException("getBoardById"));
        if(findBoard.getIsDelete()){
            throw new NoSuchElementException("getBoardById");
        }
        return new BoardListCommentDto(findBoard);
    }

    @Override
    public List<BoardListResponseDto> getAllBoards(Pageable pageable) {
        Page<Board> page = boardRepository.findBoardAllcountBy(pageable);

        return page.map(board -> new BoardListResponseDto(board)).getContent();
    }

    @Override
    public BoardDetailResponseDto saveBoard(BoardRequestDto board) {
        Board save = boardRepository.save(new Board(board));
        Board findBoard = boardRepository.findById(save.getId()).orElseThrow(() -> new NullPointerException("saveBoard"));

        log.info("findBoard ={}", findBoard);
        return new BoardDetailResponseDto(findBoard);
    }

    @Override
    public Boolean deleteBoard(Long boardId) { // soft delete 수행.
        Board findBoard = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException("deleteBoard"));
        findBoard.setDelete(true);

        commentRepository.updateIsDeleteTrue(findBoard.getId());

        return true;
    }

    @Override
    public BoardDetailResponseDto updateBoard(Long boardId, BoardRequestDto board) {
        Board findBoard = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException("updateBoard"));
        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());


        return new BoardDetailResponseDto(findBoard);
    }
}
