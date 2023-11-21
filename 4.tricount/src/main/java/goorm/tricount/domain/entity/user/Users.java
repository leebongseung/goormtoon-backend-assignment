package goorm.tricount.domain.entity.user;

import goorm.tricount.domain.dto.request.user.UserSingUpDto;
import goorm.tricount.domain.dto.response.user.LoginUser;
import goorm.tricount.domain.entity.expense.Expense;
import goorm.tricount.domain.entity.expense.ExpenseAssociatedUser;
import goorm.tricount.domain.entity.settlement.UsersHasSettlement;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
//@Setter
public class Users {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "users_id")
    private Long usersId;

    @Column(length = 20, nullable = false)
    private String id;
    @Column(length = 255, nullable = false)
    private String password;
    @Column(length = 20, nullable = false)
    private String nickname;

    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @Enumerated(EnumType.STRING)
    private UserType type;

    // 사용자와 관련된 모든 정산.
    @OneToMany(mappedBy = "user")
    private List<UsersHasSettlement> usersHasSettlementList = new ArrayList<>();


    public Users(UserSingUpDto singUpDto) {
        this.id = singUpDto.getId();
        this.password = singUpDto.getPassword();
        this.nickname = singUpDto.getNickName();
        this.type = UserType.CREATE;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }


    public Users(Long userId) {
        this.usersId = userId;
    }

    public Users(LoginUser loginUser) {
        this.id = loginUser.getId();
        this.password = loginUser.getPassword();
        this.nickname = loginUser.getNickname();
        this.type = loginUser.getType();
        this.createAt = loginUser.getCreateAt();
        this.updateAt = loginUser.getUpdateAt();
    }
}
