package otus.base;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Светлана on 02.07.2017.
 */
@Entity
@Table(name = "phones")
public class PhoneDataSet {
    @Column(name = "code")
    private int code;
    @Column(name = "number")
    private String number;

    public PhoneDataSet() {
    }

    public PhoneDataSet(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "PhoneDataSet{" +
                "number='" + number + '\'' +
                '}';
    }
}