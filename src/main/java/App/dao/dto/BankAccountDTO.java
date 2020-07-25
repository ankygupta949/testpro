package App.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class BankAccountDTO {

    private Long id;
    private String name;
    private Boolean isUnassigned;
    private Boolean isInvoiceReceipt;
    private Boolean isFundsVariance;


}
