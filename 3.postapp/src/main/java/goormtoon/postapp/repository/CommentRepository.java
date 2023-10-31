package goormtoon.postapp.repository;

import goormtoon.postapp.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Modifying // 벌크연산후 영속컨테이크
    @Query("UPDATE Comment c SET c.isDelete = TRUE where c.board.id = :id")
    public void updateIsDeleteTrue(@Param("id") Long id);

}
