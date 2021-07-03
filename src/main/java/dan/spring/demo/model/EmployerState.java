package dan.spring.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "employer_state")
public class EmployerState {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pk;
    private String id;
    private String name;

    @OneToOne(mappedBy = "employer_state")
    transient private LastNegotiation lastNegotiation;


    @Override
    public String toString() {
        return  "\n" + "id : " + id + "\n" +
                "name : " + name + "\n" ;
    }

    public EmployerState() {
    }
}
