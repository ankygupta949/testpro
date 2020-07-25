package App.dao.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AuthorDTO {
    private Long authId;
    private String authName;
    private Date dob;
    private String bookName;
}
