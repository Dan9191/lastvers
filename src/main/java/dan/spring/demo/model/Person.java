package dan.spring.demo.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name ="person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pk;

    private String id;
    private String title;
    private String url;
    private String first_name;
    private String last_name;
    private String middle_name;
    private boolean can_view_full_info;
    private int age;
    private String alternate_url;
    private String created_at;
    private String updated_at;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pk")
    private Area area;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "person")
    private List<Certificate> certificate;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pk")
    private TotalExperience total_experience;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pk")
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pk")
    private Salary salary;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pk")
    private Photo photo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pk")
    private Owner owner;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pk")
    private NegotiationsHistory negotiations_history;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pk")
    private LastNegotiation last_negotiation;

    private String text = "empty";

    @Override
    public String toString() {
        return  "Резюме: " +
                "id : '" + id + " \", " + '\n' +
                "title : '" + title + " \", " + '\n' +
                "url : '" + url + " \", " + '\n' +
                "Имя : '" + first_name + " \", " + '\n' +
                "Фамилия : '" + last_name + " \", " + '\n' +
                "Отчество  : '" + middle_name + " \", " + '\n' +
                "можно ли показать полное инфо : '" + can_view_full_info + " \", " + '\n' +
                "возраст : '" + age + " \", " + '\n' +
                "альтернативный URL : '" + alternate_url + " \", " + '\n' +
                "создано : '" + created_at + '\'' +  "\n" +
                "обновлено : '" + updated_at + '\'' +  "\n" +
                "место : '" + area  + '\'' + "\n" +
                "сертификаты : " + certificate +  "\n" +
                "общий стаж : " + total_experience + "\n" +
                "пол : " + gender + "\n" +
                "зп : " + salary + "\n" +
                "фото  : " + photo + "\n" +
                "владелец : " + owner + "\n" +
                "История переговоров : " + negotiations_history + "\n" +
                "последние переговоры : " + last_negotiation + "\n" +
                " Отзыв : " + text + "\n";
    }
    public Person() {
    }

}
