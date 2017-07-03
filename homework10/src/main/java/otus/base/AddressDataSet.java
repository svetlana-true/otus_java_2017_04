package otus.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Светлана on 02.07.2017.
 */
@Entity
@Table(name = "addresses")
public class AddressDataSet{
    private String street;
    private int index;

    @Column(name = "code")
    private int code;
    @Column(name = "number")
    private String number;

    public AddressDataSet() {
    }

    public AddressDataSet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    @Override
    public String toString() {
        return "AddressDataSet{" +
                "street='" + street + '\'' +
                '}';
    }
}