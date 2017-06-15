import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import otus.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svetlana on 6/14/17.
 */
public class JSONTest {
    private JSONObject jsObject;

    @Before
    public void setUp()
    {
        jsObject = new JSONObject();
    }

    @Test
    public void numbersTest() throws InstantiationException, IllegalAccessException {
        Assert.assertEquals("int serialized", "1", jsObject.toJSON(1));
        Assert.assertEquals("long serialized", "1", jsObject.toJSON(1L));
    }

    @Test
    public void charsTest() throws InstantiationException, IllegalAccessException {
        Assert.assertEquals("char serialized", "a", jsObject.toJSON('a'));
    }

    @Test
    public void stringsTest() throws InstantiationException, IllegalAccessException {
        Assert.assertEquals("java.lang.String Serialized", "One string", jsObject.toJSON("One string"));
    }

    @Test
    public void simpleArraysTest() throws InstantiationException, IllegalAccessException {
        Assert.assertEquals("int[] serialized ", "[\"1\",\"2\",\"3\",\"4\",\"5\"]", jsObject.toJSON(new int[] {1, 2, 3, 4, 5}));
        Assert.assertEquals("String[] serialized", "[\"one\",\"two\",\"three\",\"four\",\"five\"]", jsObject.toJSON(new String[] {"one", "two", "three", "four", "five"}));
    }

    @Test
    @SuppressWarnings("unchecked")
    public void objectsWithPrimitivesTest () throws InstantiationException, IllegalAccessException {
        Address address = new Address("Third Avenue", "New York", 10022);
        String serialized = jsObject.toJSON(address);
    }


    @Test
    @SuppressWarnings("unchecked")
    public void javaCollectionsTest() throws InstantiationException, IllegalAccessException {
        List<Address> addressList = new ArrayList<Address>();
        addressList.add(new Address("Third Avenue", "New York", 10022));
        addressList.add(new Address("Boulevard Lannes", "Paris", 75116));
        addressList.add(new Address("Unter den Linden", "Berlin", 10117));
        String res = jsObject.toJSON(addressList);

    }

}
