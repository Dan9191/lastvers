package dan.spring.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name ="area")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    private String id;
    private String name;
    private String url;

    @OneToOne(mappedBy = "area")
    transient private Person person;

    @Override
    public String toString() {
        return "{" +
                "id='" + id + '\'' + "\n" +
                " Название города ='" + name + '\'' + "\n" +
                " url='" + url + '\'' + "\n" +
                '}';
    }

    public Area() {
    }
}
