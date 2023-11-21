package goorm.tricount.domain.dto.response.settlement;

import goorm.tricount.domain.dto.response.user.UserDetailDto;
import goorm.tricount.domain.entity.settlement.UsersHasSettlement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsersHasExpenseResponseDto {
    private Long usersHasSettlementId;
    private UserDetailDto user;

    public UsersHasExpenseResponseDto(UsersHasSettlement usersHasSettlement) {
        this.usersHasSettlementId = usersHasSettlement.getId();
        this.user = new UserDetailDto(usersHasSettlement.getUser());
    }

}
