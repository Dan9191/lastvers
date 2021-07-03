package dan.spring.demo.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pk;

    private String achieved_at;
    private String owner;
    private String title;
    private String type;
    private String url;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @Override
    public String toString() {
        return "{" +
                "получен ='" + achieved_at + '\'' + "\n" +
                "owner='" + owner + '\'' + "\n" +
                "title='" + title + '\'' + "\n" +
                "type='" + type + '\'' + "\n" +
                "url='" + url + '\'' + "\n" +
                '}';
    }

    public Certificate() {
    }
}