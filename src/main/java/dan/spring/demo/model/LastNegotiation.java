package dan.spring.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "last_negotiation")
public class LastNegotiation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pk;

    @OneToOne(cascade = CascadeType.ALL)
    private EmployerState employer_state;
    private String created_at;

    @OneToOne(mappedBy = "last_negotiation")
    transient private Person person;

    @Override
    public String toString() {
        return  "employer_state : " + employer_state +"\n" +
                "created_at : " + created_at + "\n";
    }

    public LastNegotiation() {
    }
}
