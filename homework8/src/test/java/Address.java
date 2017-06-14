/**
 * Created by svetlana on 6/13/17.
 */
public class Address {

    private String street;
    private String city;
    private int zipcode;

    Address(String str, String ci, int zipc)
    {
        street = str;
        city = ci;
        zipcode = zipc;
    };

    @Override
    public String toString(){
        return street + ", "+city+", "+zipcode;
    }
}