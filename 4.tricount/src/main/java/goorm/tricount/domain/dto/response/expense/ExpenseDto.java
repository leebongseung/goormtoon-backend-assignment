package goorm.tricount.domain.dto.response.expense;

import goorm.tricount.domain.dto.response.settlement.UsersHasSettlementResponseDto;
import goorm.tricount.domain.entity.expense.Expense;
import goorm.tricount.domain.entity.expense.ExpenseType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class ExpenseDto {
    private Long expenseId;
    private String paymentUserNickName;
    private String title;
    private BigDecimal amount;
    private LocalDateTime expenditure_date;
    private ExpenseType type;


    public ExpenseDto(Expense expense) {
        this.expenseId = expense.getId();
        this.paymentUserNickName = expense.getPaymentUser().getUser().getNickname();
        this.title = expense.getTitle();
        this.amount = expense.getAmount();
        this.expenditure_date = expense.getExpenditureDate();
        this.type = expense.getType();
    }
}
