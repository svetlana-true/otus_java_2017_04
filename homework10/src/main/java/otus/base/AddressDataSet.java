package otus.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Светлана on 02.07.2017.
 */
@Entity
@Table(name = "addresses")
public class AddressDataSet extends DataSet{
    @Column(name = "street")
    private String street;
    @Column(name = "index")
    private int index;

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
                "index='" + Integer.toString(index) + '\'' +
                '}';
    }
}