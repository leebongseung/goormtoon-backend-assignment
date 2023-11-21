package goorm.tricount.domain.dto.response.settlement;

import goorm.tricount.domain.entity.settlement.Settlement;
import goorm.tricount.domain.entity.settlement.SettlementType;
import goorm.tricount.domain.entity.settlement.UsersHasSettlement;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class SettlementDetailDto {
    private Long settlementId;
    private String title;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private SettlementType type;
    private List<SettlementOfUsersResponseDto> settlementOfUsers = new ArrayList<>();


    public SettlementDetailDto(SettlementDto newSettlement, SettlementOfUsersResponseDto settlementOfUsers) {
        this.settlementId = newSettlement.getSettlementId();
        this.title = newSettlement.getTitle();
        this.createAt = newSettlement.getCreateAt();
        this.updateAt = newSettlement.getUpdateAt();
        this.type = newSettlement.getType();
        this.settlementOfUsers.add(settlementOfUsers);
    }

    public SettlementDetailDto(Settlement result) {
        this.settlementId = result.getSettlementId();
        this.title= result.getTitle();
        this.createAt = result.getCreateAt();
        this.updateAt = result.getUpdateAt();
        this.type = result.getType();
        this.settlementOfUsers =  result.getUsersHasSettlementList()
                                .stream()
                                .map(SettlementOfUsersResponseDto::new)
                                .collect(Collectors.toList());
    }
}
