package goorm.tricount.domain.entity.settlement;

import goorm.tricount.domain.dto.response.settlement.SettlementDto;
import goorm.tricount.domain.dto.response.user.LoginUser;
import goorm.tricount.domain.entity.expense.Expense;
import goorm.tricount.domain.entity.expense.ExpenseAssociatedUser;
import goorm.tricount.domain.entity.user.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(indexes = {
        @Index(name = "index_users_id", columnList = "USERS_ID"),
        @Index(name = "index_settlement_id", columnList = "SETTLEMENT_ID")
})
public class UsersHasSettlement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_has_settlement_id")
    private Long id;
    

    // 정산 참여 유저
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERS_ID", referencedColumnName = "USERS_ID")
    private Users user;
    

    // 계모임 주소.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SETTLEMENT_ID")
    private Settlement settlement;

    @OneToMany(mappedBy = "usersHasSettlement")
    private List<ExpenseAssociatedUser> expenseAssociatedUserList = new ArrayList<>();

    @OneToMany(mappedBy = "paymentUser")
    private List<Expense> expenseList = new ArrayList<>();

    public void addUser(Users user){
        this.user = user;
        user.getUsersHasSettlementList().add(this);
    }
    public void addSettlement(Settlement settlement) {
        this.settlement = settlement;
        settlement.getUsersHasSettlementList().add(this);
    }
    public UsersHasSettlement(Settlement settlement, Users users) {
        this.user = users;
        this.settlement = settlement;
    }
}
