package goorm.tricount.domain.dto.request.settlement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateSettlementDto {
    @NotBlank
    private String title;
}
