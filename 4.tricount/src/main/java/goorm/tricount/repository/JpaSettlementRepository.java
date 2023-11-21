package goorm.tricount.repository;

import goorm.tricount.domain.entity.settlement.Settlement;
import goorm.tricount.domain.entity.settlement.UsersHasSettlement;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface JpaSettlementRepository extends JpaRepository<Settlement, Long> {

    @Override
    @Query("select distinct s " +
            "from Settlement s " +
            "join fetch s.usersHasSettlementList uhs " +
            "join fetch uhs.user " +
            "where s.settlementId = :settlementId and s.type = 'CREATE'")
    Optional<Settlement> findById(Long settlementId);

    @Query("select distinct s " +
            "from Settlement s " +
            "join fetch s.usersHasSettlementList uhs " +
            "join fetch uhs.user u " +
            "where s.settlementId = :settlementId and s.type = 'CREATE' and u.usersId = :userId")
    Optional<Settlement> findByUsersIdAAndSettlementId(Long userId, Long settlementId);

    @Override
    @Query("select s from Settlement s where s.type = 'CREATE'")
    List<Settlement> findAll();

    @Override
    @Transactional
    <S extends Settlement> S save(S entity);

    @Query("select s from Settlement s " +
            "join fetch s.usersHasSettlementList uhs " +
            "join fetch uhs.user u " +
            "where u.usersId = :usersId and s.type = 'CREATE'")
    List<Settlement> findAllByUsersId(Long usersId);
}
