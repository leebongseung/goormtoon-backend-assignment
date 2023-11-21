package goorm.tricount.domain.dto.response.expense;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class BalanceDto {
    private Long senderUserNo;
    private String senderUserName;
    private BigDecimal sendAmount;
    private Long receiverUserNo;
    private String receiverUserName;

    public void addAmount(BigDecimal amount){
        this.sendAmount = this.sendAmount.add(amount);
    }
    public void minusAmount(BigDecimal amount){
        this.sendAmount = this.sendAmount.subtract(amount);
    }
}
