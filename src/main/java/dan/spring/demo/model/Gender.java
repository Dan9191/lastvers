package dan.spring.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "gender")
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pk;
    private String id;
    private String name;

    @OneToOne(mappedBy = "gender")
    transient private Person person;


    @Override
    public String toString() {
        return  "id='" + id + '\'' +  "\n" +
                "принадлежность  ='" + name + '\'';
    }

    public Gender() {
    }
}