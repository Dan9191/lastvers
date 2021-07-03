package dan.spring.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Comments comments;

    @OneToOne(mappedBy = "owner")
    transient private Person person;


    @Override
    public String toString() {
        return "Владелец : " + "\n" +
                "Комментарии : " + comments;
    }

    public Owner() {
    }
}
