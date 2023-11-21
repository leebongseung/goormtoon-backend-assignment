package goorm.tricount.domain.dto.response.settlement;

import goorm.tricount.domain.dto.request.user.UserLoginDto;
import goorm.tricount.domain.dto.response.user.LoginUser;
import goorm.tricount.domain.entity.settlement.Settlement;
import goorm.tricount.domain.entity.settlement.UsersHasSettlement;
import goorm.tricount.domain.entity.user.Users;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Stream;

@Data
@NoArgsConstructor
public class UsersHasSettlementResponseDto {
    private Long usersHasSettlementId;
    private UserLoginDto user;
    private SettlementDto settlement;

    public UsersHasSettlementResponseDto(UsersHasSettlement usersHasSettlement) {
        this.usersHasSettlementId = usersHasSettlement.getId();
        this.user = new UserLoginDto(usersHasSettlement.getUser());
        this.settlement = new SettlementDto(usersHasSettlement.getSettlement());
    }

}
