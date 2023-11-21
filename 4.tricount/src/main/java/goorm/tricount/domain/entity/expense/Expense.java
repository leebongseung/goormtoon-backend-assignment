package goorm.tricount.domain.entity.expense;

import goorm.tricount.domain.dto.request.expense.ExpenseRequestDto;
import goorm.tricount.domain.entity.settlement.UsersHasSettlement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Table(indexes = {@Index(name = "index_payment_user_id",columnList = "PAYMENT_USER_ID")})
public class Expense {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    //todo : 일대다 수정하기
    @JoinColumn(name = "PAYMENT_USER_ID", referencedColumnName = "users_has_settlement_id")
    private UsersHasSettlement paymentUser;

    private String title;
    private BigDecimal amount;
    private LocalDateTime expenditureDate;

    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @Enumerated(EnumType.STRING)
    private ExpenseType type;

    @OneToMany(mappedBy = "expense")
    private List<ExpenseAssociatedUser> expenseAssociatedUserList = new ArrayList<>();

    public void deleteExpense(){
        this.type =ExpenseType.DELETE;
    }

    public void addUsersHasSettlement(UsersHasSettlement usersHasSettlement){
        this.paymentUser = usersHasSettlement;
        usersHasSettlement.getExpenseList().add(this);
    }


    public Expense(ExpenseRequestDto expenseRequestDto, UsersHasSettlement usersHasSettlement) {
        this.title = expenseRequestDto.getTitle();
        this.amount = expenseRequestDto.getAmount();
        this.expenditureDate = expenseRequestDto.getExpenditureDate();
        this.paymentUser = usersHasSettlement;
        this.type = ExpenseType.CREATE;
    }
}
