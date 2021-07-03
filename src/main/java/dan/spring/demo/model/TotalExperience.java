package dan.spring.demo.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table ( name = "total_experience")
public class TotalExperience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pk;

    public int months;

    @OneToOne(mappedBy = "total_experience")
    transient private Person person;

    @Override
    public String toString() {
        return "общий стаж{" +
                "месяцев=" + months +
                '}';
    }

    public TotalExperience() {
    }
}