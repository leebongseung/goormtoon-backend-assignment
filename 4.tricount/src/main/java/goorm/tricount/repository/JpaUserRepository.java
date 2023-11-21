package goorm.tricount.repository;

import goorm.tricount.domain.dto.request.user.UserLoginDto;
import goorm.tricount.domain.entity.user.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<Users, Long> {
    @Override
    @EntityGraph(attributePaths = "usersHasSettlementList")
    Optional<Users> findById(Long aLong);

    @Override
    <S extends Users> S save(S entity); // saveUser

    @Query("select u from Users u where u.id =:id")
    public Optional<Users> findByStringId(String id);

    @Query("select u from Users u where u.id =:id and u.password = :password")
    public Optional<Users> login(String id, String password);
}
