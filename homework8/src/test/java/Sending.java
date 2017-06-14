import java.util.Arrays;

/**
 * Created by svetlana on 6/13/17.
 */
public class Sending {
    private int id;
    private String name;
    private Address address;
    private long[] phoneNumbers;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Address getAddress() {
        return address;
    }
    public void setAddress(Address address) {
        this.address = address;
    }
    public long[] getPhoneNumbers() {
        return phoneNumbers;
    }
    public void setPhoneNumbers(long[] phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("***** Employee Details *****\n");
        sb.append("ID="+getId()+"\n");
        sb.append("Name="+getName()+"\n");
        sb.append("Phone Numbers="+ Arrays.toString(getPhoneNumbers())+"\n");
        sb.append("Address="+getAddress());
        sb.append("\n*****************************");

        return sb.toString();
    }
}
