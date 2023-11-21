package goorm.tricount.domain.entity.expense;

import goorm.tricount.domain.entity.settlement.UsersHasSettlement;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "index_users_has_settlement_id", columnList = "users_has_settlement_id"),
        @Index(name = "index_expense_id", columnList = "EXPENSE_ID")
})
public class ExpenseAssociatedUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EA_ID")
    private Long id;

    // 해당되는 지출
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EXPENSE_ID")
    private Expense expense;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="users_has_settlement_id")
    private UsersHasSettlement usersHasSettlement;

    public void addExpense(Expense expense) {
        this.expense = expense;
        expense.getExpenseAssociatedUserList().add(this);
    }

    public void addUsersHasSettlement(UsersHasSettlement usersHasSettlement){
        this.usersHasSettlement = usersHasSettlement;
        usersHasSettlement.getExpenseAssociatedUserList().add(this);
    }


    public ExpenseAssociatedUser(Expense expense, UsersHasSettlement hasSettlement) {
        this.expense = expense;
        this.usersHasSettlement = hasSettlement;
    }
}
