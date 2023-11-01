package goormtoon.postapp.dto.board;

import goormtoon.postapp.domain.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardDetailResponseDto {
    private Long id;
    private String title;
    private String content;
    private int view;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public BoardDetailResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.view = board.getView();
        this.createAt = board.getCreateAt();
        this.updateAt = board.getUpdateAt();
    }
}
