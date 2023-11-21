package goorm.tricount.domain.dto.response.settlement;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class SettlementListDto {
    private List<SettlementDto> settlements;
}
