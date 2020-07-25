package App.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CountryStateDTO {
    private Long countryId;
    private String countryName;
    private Long stateId;
    private String sname;

}
