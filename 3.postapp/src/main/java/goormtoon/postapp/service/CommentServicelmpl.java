package goormtoon.postapp.service;

import goormtoon.postapp.domain.Board;
import goormtoon.postapp.domain.Comment;
import goormtoon.postapp.dto.comment.CommentRequestDto;
import goormtoon.postapp.repository.BoardRepository;
import goormtoon.postapp.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CommentServicelmpl implements CommentService{
    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @Override
    public void addComment(Long BoardId, CommentRequestDto commentRequestDto) {
        Optional<Board> byId = boardRepository.findById(BoardId);
        Board board = byId.get();
        Comment comment = new Comment(commentRequestDto);
        comment.changeBoard(board);
        commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long CommentId) {
        Comment comment = commentRepository.findById(CommentId)
                .orElseThrow(() -> new NullPointerException("deleteComment"));

        // 이미 지워진 댓글일 경우
        if(comment.getIsDelete().equals(true)){
            throw new NoSuchElementException("deleteComment");
        }

        comment.setDelete(true);
        commentRepository.save(comment);

    }

    @Override
    public void updateComment(Long CommentId, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(CommentId)
                .orElseThrow(() -> new NullPointerException("updateComment"));

        // 이미 지워진 댓글일 경우
        if(comment.getIsDelete().equals(true)){
            throw new NoSuchElementException("deleteComment");
        }

        comment.setComment(commentRequestDto.getComment());
        commentRepository.save(comment);
    }
}
