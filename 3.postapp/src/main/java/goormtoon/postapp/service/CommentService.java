package goormtoon.postapp.service;

import goormtoon.postapp.dto.comment.CommentRequestDto;

public interface CommentService {
    // 댓글 등록 : 등록시 게시판 상세화면으로 리다이렉트!
    public void addComment(Long BoardId, CommentRequestDto commentRequestDto);

    // 댓글 삭제 : 삭제 시 게시판 상세화면으로 리다이렉트!
    public void deleteComment(Long CommentId);

    // 댓글 수정 : 수정 시 게시판 상세화면으로 리다이렉트!
    public void updateComment(Long CommentId, CommentRequestDto commentRequestDto);

}
