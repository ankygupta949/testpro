package App.dao.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@ToString
@Entity
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sid;
    private String sname;
    @ManyToOne
    private Country country;

}
