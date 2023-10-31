package goormtoon.postapp.dto.board;

import goormtoon.postapp.domain.Board;
import goormtoon.postapp.domain.Comment;
import goormtoon.postapp.dto.comment.CommentResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class BoardListCommentDto {

    private Long id;
    private String title;
    private String content;
    private int view;
    private List<CommentResponseDto> commentList = new ArrayList<>();


    public BoardListCommentDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.view = board.getView();
        this.commentList = board.getCommentList()
                .stream()
                .filter(comment -> comment.getIsDelete().equals(false))
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }
}
