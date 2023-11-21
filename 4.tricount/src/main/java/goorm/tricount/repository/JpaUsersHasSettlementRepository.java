package goorm.tricount.repository;

import goorm.tricount.domain.entity.settlement.UsersHasSettlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface JpaUsersHasSettlementRepository extends JpaRepository<UsersHasSettlement, Long> {
    @Override
    @Transactional
    <S extends UsersHasSettlement> S save(S entity);

    @Query("select uhs " +
            "from UsersHasSettlement uhs " +
            "join fetch uhs.expenseList " +
            "join fetch uhs.settlement s " +
            "join fetch uhs.user u " +
            "where s.settlementId= :settlementId and s.type = 'CREATE'")
    List<UsersHasSettlement> findAllBySettlementId(Long settlementId);

    @Query("select uhs " +
            "from UsersHasSettlement uhs " +
            "where uhs.user.usersId = :usersId and uhs.settlement.settlementId = :settlementId and uhs.settlement.type ='CREATE'")
    Optional<UsersHasSettlement> findByUserIdAndSettlementId(Long usersId, Long settlementId);

    @Query("select uhs " +
            "from UsersHasSettlement uhs " +
            "join fetch uhs.expenseList e " +
            "where uhs.settlement.settlementId = :settlementId and uhs.settlement.type ='CREATE' and e.type = 'CREATE'")
    List<UsersHasSettlement> findAllByUserIdAndSettlementId(Long settlementId);
}
