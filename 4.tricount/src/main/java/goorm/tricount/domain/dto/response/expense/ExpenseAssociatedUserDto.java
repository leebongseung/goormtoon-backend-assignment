package goorm.tricount.domain.dto.response.expense;

import goorm.tricount.domain.dto.response.settlement.UsersHasExpenseResponseDto;
import goorm.tricount.domain.entity.expense.ExpenseAssociatedUser;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExpenseAssociatedUserDto {
    private Long expenseAssociatedUserId;
    private UsersHasExpenseResponseDto userHasSettlement;

    public ExpenseAssociatedUserDto(ExpenseAssociatedUser expenseAssociatedUser) {
        this.expenseAssociatedUserId = expenseAssociatedUser.getId();
        this.userHasSettlement = new UsersHasExpenseResponseDto(expenseAssociatedUser.getUsersHasSettlement());
    }
}
