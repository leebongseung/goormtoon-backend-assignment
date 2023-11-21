package goorm.tricount.domain.dto.response.settlement;

import goorm.tricount.domain.dto.request.user.UserLoginDto;
import goorm.tricount.domain.dto.response.user.UserDetailDto;
import goorm.tricount.domain.entity.settlement.UsersHasSettlement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SettlementOfUsersResponseDto {
    private Long usersHasSettlementId;
    private UserDetailDto user;

    public SettlementOfUsersResponseDto(UsersHasSettlement usersHasSettlement) {
        this.usersHasSettlementId = usersHasSettlement.getId();
        this.user = new UserDetailDto(usersHasSettlement.getUser());
    }
}
