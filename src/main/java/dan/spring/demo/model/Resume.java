/*
package dan.spring.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table("resume")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "resume")
    private List<Person> resumes;

    public Resume() {
    }
}
*/
