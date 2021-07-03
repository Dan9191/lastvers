package dan.spring.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "negotiations_history")
public class NegotiationsHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pk;

    private String url;

    @OneToOne(mappedBy = "negotiations_history")
    transient private Person person;

    @Override
    public String toString() {
        return "url : " + url + "\n";
    }

    public NegotiationsHistory() {
    }
}
