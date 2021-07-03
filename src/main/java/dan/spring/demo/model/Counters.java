package dan.spring.demo.model;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "counters")
public class Counters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pk;
    private int total;

    @OneToOne(mappedBy = "counters")
    transient private Comments comments;

    @Override
    public String toString() {
        return  "total:" + total ;
    }

    public Counters() {
    }
}
