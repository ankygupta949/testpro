package App.dao.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CustomTestDTO {
    List<TestDTO> orders = new ArrayList<>();
}
