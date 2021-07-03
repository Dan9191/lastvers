package dan.spring.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "comments")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pk;

    private String url;

    @OneToOne(cascade = CascadeType.ALL)
    private Counters counters;

    @OneToOne(mappedBy = "comments")
    transient private Owner Owner;


    @Override
    public String toString() {
        return  "url : " + url + "\n" +
                "счетчики : " + counters;
    }

    public Comments() {
    }
}