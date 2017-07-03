package otus.base;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Светлана on 02.07.2017.
 */
@Entity
@Table(name = "MyDB")
public class UserDataSet {
    @Id
    @Column(name = "id", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name", length = 255)
    private String name;
    @Column(name = "age", length = 3)
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressDataSet address;

    @OneToMany
    @JoinTable(name = "phones",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "id")}
    )
    private List<PhoneDataSet> phones;

    //Important for Hibernate
    public UserDataSet() {
    }
    public UserDataSet(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    //constructor for new dataSets without id
    public UserDataSet(String name, int age) {
        this.id = -1;
        this.name = name;
        this.age = age;
    }
}