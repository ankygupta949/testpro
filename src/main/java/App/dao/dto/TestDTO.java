package App.dao.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TestDTO {

    private Long orderId ;
    private List<DataDTO> bags= new ArrayList<>();
}
