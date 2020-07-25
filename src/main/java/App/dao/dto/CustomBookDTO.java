package App.dao.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class CustomBookDTO {
    private Long id;
    private String bookName;
    private BigDecimal bookPrice;
}
