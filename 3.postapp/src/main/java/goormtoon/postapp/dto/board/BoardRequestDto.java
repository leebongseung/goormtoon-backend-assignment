package goormtoon.postapp.dto.board;

import goormtoon.postapp.domain.Board;
import lombok.*;

@Getter
@NoArgsConstructor
public class BoardRequestDto {
    private String title;
    private String content;

    public BoardRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
