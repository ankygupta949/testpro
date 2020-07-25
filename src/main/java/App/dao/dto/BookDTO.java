package App.dao.dto;

import App.dao.entity.Grade;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class BookDTO {

    private Long id;
    private String bookName;
    private BigDecimal bookPrice;
    private String authorName;
    private Long authorId;
    private Grade grade;

}
