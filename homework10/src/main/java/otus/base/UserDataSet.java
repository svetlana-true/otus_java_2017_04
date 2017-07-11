package otus.base;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
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

    @OneToMany(
            mappedBy = "userdataset",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<PhoneDataSet> phones = new ArrayList<>();

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

    public UserDataSet(long id, String name, AddressDataSet address, PhoneDataSet... phones) {
        this.setId(id);
        this.name = name;
        this.address = address;
        List<PhoneDataSet> userPhones = Arrays.asList(phones);
        this.phones.addAll(userPhones);
        userPhones.forEach(phone -> phone.setUser(this));
    }
    public UserDataSet(String name, AddressDataSet address, PhoneDataSet... phones) {
        this(-1, name, address, phones);
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id='" + getId() + '\'' +
                "name='" + name + '\'' +
                ", address=" + address +
                ", phones=" + phones +
                '}';
    }
}
