package dan.spring.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "salary")
public class Salary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pk;

    private int amount;
    private String currency;

    @OneToOne(mappedBy = "salary")
    transient private Person person;



    @Override
    public String toString() {
        return  "сумма=" + amount +  "\n" +
                "валюта='" + currency + '\'' +  "\n";
    }

    public Salary() {
    }


}
