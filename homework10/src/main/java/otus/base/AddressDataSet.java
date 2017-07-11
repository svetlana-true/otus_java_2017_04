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
    @Column(name = "zipcode")
    private String zipcode;

    public AddressDataSet() {
    }

    public AddressDataSet(String street) {
        this.street = street;
        this.zipcode = "000000";
    }

    public AddressDataSet(String street, String zipcode) {
        this.street = street;
        this.zipcode = zipcode;
    }

    @Override
    public String toString() {
        return "AddressDataSet{" +
                "street='" + this.street + '\'' +
                "index='" + this.zipcode + '\'' +
                '}';
    }
}