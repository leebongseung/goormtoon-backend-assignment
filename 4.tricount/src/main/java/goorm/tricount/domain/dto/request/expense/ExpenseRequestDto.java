package goorm.tricount.domain.dto.request.expense;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class ExpenseRequestDto {
    @NotBlank
    private String title;
    @NotNull
    private BigDecimal amount;
    @NotNull
    private LocalDateTime expenditureDate;
    @NotNull
    private List<Long> expenseAssociatedUserList = new ArrayList<>();
}
