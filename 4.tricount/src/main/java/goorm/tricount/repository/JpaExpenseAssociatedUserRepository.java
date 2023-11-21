package goorm.tricount.repository;

import goorm.tricount.domain.entity.expense.ExpenseAssociatedUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaExpenseAssociatedUserRepository extends JpaRepository<ExpenseAssociatedUser, Long> {
}
