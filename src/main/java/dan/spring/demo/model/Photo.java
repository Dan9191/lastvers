package dan.spring.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="photo")
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pk;

    private String medium;
    private String small;
    private String id;

    @OneToOne(mappedBy = "photo")
    transient private Person person;

    @Override
    public String toString() {
        return  "medium : " + medium + "\n" +
                "small : " + small + "\n" +
                "id : " + id + "\n" +
                '}' + "\n";
    }

    public Photo() {
    }

}
