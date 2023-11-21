package goorm.tricount.domain.dto.response.expense;

import goorm.tricount.domain.dto.response.settlement.UsersHasExpenseResponseDto;
import goorm.tricount.domain.entity.expense.Expense;
import goorm.tricount.domain.entity.expense.ExpenseType;
import goorm.tricount.domain.entity.settlement.UsersHasSettlement;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class ExpenseDetailDto {
    private Long expenseId;
    private String title;
    private BigDecimal amount;
    private LocalDateTime expenditure_date;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private ExpenseType type;
    private UsersHasExpenseResponseDto paymentUser;
    private List<ExpenseAssociatedUserDto> expenseAssociatedUserList;

    public ExpenseDetailDto(Expense result) {
        this.expenseId = result.getId();
        this.title = result.getTitle();
        this.amount = result.getAmount();
        this.expenditure_date = result.getExpenditureDate();
        this.createAt = result.getCreateAt();
        this.updateAt = result.getUpdateAt();
        this.type = result.getType();
        this.paymentUser = new UsersHasExpenseResponseDto(result.getPaymentUser());
        this.expenseAssociatedUserList = result.getExpenseAssociatedUserList()
                                        .stream()
                                        .map(ExpenseAssociatedUserDto::new)
                                        .collect(Collectors.toList());
    }
}
