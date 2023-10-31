package goormtoon.postapp.service;

import goormtoon.postapp.domain.Board;
import goormtoon.postapp.dto.board.BoardListCommentDto;
import goormtoon.postapp.dto.board.BoardRequestDto;
import goormtoon.postapp.dto.board.BoardResponseDto;
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
import java.util.Optional;
import java.util.stream.Collectors;

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

        findBoard.addView();
//        boardRepository.save(findBoard);

        return new BoardListCommentDto(findBoard);
    }

    @Override
    public Page<BoardResponseDto> getAllBoards(Pageable pageable) {
        Page<Board> page = boardRepository.findBoardAllcountBy(pageable);

        return page.map(board -> new BoardResponseDto(board));
    }

    @Override
    public BoardResponseDto saveBoard(BoardRequestDto board) {
        Board save = boardRepository.save(new Board(board));
        Board findBoard = boardRepository.findById(save.getId()).orElseThrow(() -> new NullPointerException("saveBoard"));

        log.info("findBoard ={}", findBoard);
        return new BoardResponseDto(findBoard);
    }

    @Override
    public Boolean deleteBoard(Long boardId) { // soft delete 수행.
        Board findBoard = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException("deleteBoard"));
        findBoard.setDelete(true);

//        boardRepository.save(findBoard);
        // update 쿼리 벌크

        // 이게 왜..? ... ?..........? 음...
        commentRepository.updateIsDeleteTrue(findBoard.getId());

        return true;
    }

    @Override
    public BoardResponseDto updateBoard(Long boardId, BoardRequestDto board) {
        Board findBoard = boardRepository.findById(boardId).orElseThrow(() -> new NullPointerException("updateBoard"));
        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());
//        boardRepository.save(findBoard);

        return new BoardResponseDto(findBoard);
    }
}
