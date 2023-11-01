package goormtoon.postapp.dto.board;

import goormtoon.postapp.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;


import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class BoardListResponseDto {
    private Long id;
    private String title;
    private String content;
    private int view;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public BoardListResponseDto(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.view = board.getView();
        this.createAt = board.getCreateAt();
        this.updateAt = board.getUpdateAt();
    }
}
