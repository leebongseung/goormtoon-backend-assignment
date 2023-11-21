package goorm.tricount.domain.dto.response.expense;

import goorm.tricount.domain.entity.expense.Expense;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ExpensesDto {
    private List<ExpenseDto> expenseDtoList = new ArrayList<>();

    public ExpensesDto(List<ExpenseDto> expenses) {
        expenseDtoList = new ArrayList<>(expenses);
    }
}
