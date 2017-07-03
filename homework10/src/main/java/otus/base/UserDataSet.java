package otus.base;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Светлана on 02.07.2017.
 */
@Entity
@Table(name = "MyDB")
public class UserDataSet extends DataSet{

    @Column(name = "name", length = 255)
    private String name;
    @Column(name = "age", length = 3)
    private int age;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private AddressDataSet address;

    //@OneToMany(fetch = FetchType.EAGER, mappedBy = "MyDB")
    //@OneToMany(targetEntity=PhoneDataSet.class, mappedBy = "dataSet", cascade = CascadeType.ALL,
    //        fetch = FetchType.LAZY, orphanRemoval = true)
   // @Fetch(value = FetchMode.SELECT)
    /*@JoinTable(name = "phones",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "id")}
    )*/

    @OneToMany(mappedBy = "userdataset")
    private List<PhoneDataSet> phones;

    //Important for Hibernate
    public UserDataSet() {
    }
    public UserDataSet(long id, String name, int age) {
        this.setId(id);
        this.name = name;
        this.age = age;
    }
    //constructor for new dataSets without id
    public UserDataSet(String name, int age) {
        this.setId(-1);
        this.name = name;
        this.age = age;
    }
}