package goorm.tricount.repository;

import goorm.tricount.domain.entity.expense.Expense;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JpaExpenseRepository extends JpaRepository<Expense, Long> {
    @Override
    @Query("select e " +
            "from Expense e " +
            "join fetch e.expenseAssociatedUserList eau " +
            "where e.id = :id and e.type = 'CREATE'")
    Optional<Expense> findById(Long id);

    @Query("select e " +
            "from Expense e " +
            "where e.id=:expenseId and e.paymentUser.id = :userHasSettlementId and e.type = 'CREATE'")
    Optional<Expense> findByUsersHasSettlementId( Long expenseId, Long userHasSettlementId);

    @Query("select distinct e " +
            "from Expense e " +
            "join fetch e.paymentUser p " +
            "join fetch e.expenseAssociatedUserList eau " +
            "join fetch eau.usersHasSettlement uhs " +
            "join fetch uhs.user u "+
            "join fetch uhs.settlement s "+
            "where s.settlementId=:settlementId and e.type = 'CREATE'")
    List<Expense> findAll(Long settlementId);
}
