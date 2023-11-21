package goorm.tricount.domain.dto.response.settlement;

import goorm.tricount.domain.entity.settlement.Settlement;
import goorm.tricount.domain.entity.settlement.SettlementType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


/**
 * SettlmentListDto에서 사용되는 Dto입니다.
 * */
@AllArgsConstructor
@NoArgsConstructor

@Getter
@Setter
public class SettlementDto {
    private Long settlementId;
    private String title;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private SettlementType type;

    public SettlementDto(Long settlementId, String title) {
        this.settlementId = settlementId;
        this.title = title;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.type = SettlementType.CREATE;
    }

    public SettlementDto(Settlement settlement) {
        this.settlementId = settlement.getSettlementId();
        this.title = settlement.getTitle();
        this.createAt = settlement.getCreateAt();
        this.updateAt = settlement.getUpdateAt();
        this.type = settlement.getType();
    }
}
