package otus.base;

import javax.persistence.*;

/**
 * Created by Светлана on 02.07.2017.
 */
@Entity
@Table(name = "phones")
public class PhoneDataSet extends DataSet{
    @Column(name = "code")
    private int code;
    @Column(name = "number")
    private String number;

   // @ManyToOne(fetch = FetchType.EAGER, targetEntity = UserDataSet.class)
    @ManyToOne
  //  @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "userdataset_id")//, nullable = false)
    private UserDataSet userdataset;

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
                "code='" + Integer.toString(code) + '\'' +
                "number='" + number + '\'' +
                '}';
    }
}