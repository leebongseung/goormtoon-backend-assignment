package goormtoon.postapp.dto.comment;

import goormtoon.postapp.domain.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String comment;

    public CommentResponseDto(Long id, String comment) {
        this.id = id;
        this.comment = comment;
    }

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
    }
}
