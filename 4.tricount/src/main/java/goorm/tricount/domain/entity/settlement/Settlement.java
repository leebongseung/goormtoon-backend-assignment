package goorm.tricount.domain.entity.settlement;

import goorm.tricount.domain.dto.request.settlement.CreateSettlementDto;
import goorm.tricount.domain.dto.response.settlement.SettlementDto;
import goorm.tricount.domain.entity.user.Users;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Settlement {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "settlement_id")
    private Long settlementId;

    private String title;

    @CreationTimestamp
    private LocalDateTime createAt;
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @Enumerated(EnumType.STRING)
    private SettlementType type;

    // 정산 리스트
    @OneToMany(mappedBy = "settlement")
    private List<UsersHasSettlement> usersHasSettlementList = new ArrayList<>();


    public Settlement(CreateSettlementDto settlementDto) {
        this.title = settlementDto.getTitle();
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.type = SettlementType.CREATE;
    }

    public Settlement(SettlementDto settlement) {
        this.settlementId = settlement.getSettlementId();
        this.title = settlement.getTitle();
        this.type = settlement.getType();
        this.createAt = settlement.getCreateAt();
        this.updateAt = settlement.getUpdateAt();
    }

    public void setType(SettlementType type) {
        this.type = type;
    }

    //
//    public void setId(Long id) {
//        this.settlementId = id;
//    }

    //    // 지출 리스트
//    @OneToMany(mappedBy = "settlement")
//    private List<Expense> expenses;
}
