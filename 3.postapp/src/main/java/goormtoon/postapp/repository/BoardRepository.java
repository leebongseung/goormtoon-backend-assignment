package goormtoon.postapp.repository;

import goormtoon.postapp.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface BoardRepository extends JpaRepository<Board, Long>, JpaSpecificationExecutor<Board> {
    // Data JPA에서 fect 조인을 어노테이션으로 사용할 수 있도록 만들어 준 기능이다.
    @EntityGraph(attributePaths = "commentList") // 콜렉션 타입을 EntityGraph를 이용
    @Override
    Optional<Board> findById(Long id); // N+1 해결방안


    @Query(value = "select b from Board b where b.isDelete = false order by b.id desc ")
    Page<Board> findBoardAllcountBy(Pageable pageable);
}
