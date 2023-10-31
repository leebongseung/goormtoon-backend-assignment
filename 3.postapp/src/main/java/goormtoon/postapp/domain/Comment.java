package goormtoon.postapp.domain;

import goormtoon.postapp.dto.comment.CommentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "COMMENT_ID")
    private Long id;

    private String comment;
    private Boolean isDelete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="BOARD_ID")
    private Board board;

    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;

 //latmodifiedDate -> 한나님꺼

    public Comment(CommentRequestDto commentRequestDto){
        this.comment = commentRequestDto.getComment();
        this.isDelete = false;
    }

    public void changeBoard(Board board){
        this.board = board;
        board.getCommentList().add(this);
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
