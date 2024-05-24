package clone.tricount.model;


import clone.tricount.MemberContext;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExpenseRequest {
    private String name;
    private Long settlementId;
    private Long payerMemberId = MemberContext.getMember().getId();
    private BigDecimal amount;
}
