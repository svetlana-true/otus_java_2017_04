package otus;

/**
 * Created by Светлана on 02.07.2017.
 */

import otus.base.AddressDataSet;
import otus.base.DBService;
import otus.base.PhoneDataSet;
import otus.base.UserDataSet;
import otus.dbservice.DBServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        System.out.println("Here is my DBService. ");

        DBService dbService = new DBServiceImpl();

        String status = dbService.getLocalStatus();
        System.out.println("Status: " + status);

        UserDataSet user1 = new UserDataSet(
                "user1",
                new AddressDataSet("33, Boulevard Lannes, Paris", "75116"),
                new PhoneDataSet("0145041423"),
                new PhoneDataSet("0145043038"));

        System.out.println(user1);

        dbService.save(user1);
        dbService.save(new UserDataSet("user2", new AddressDataSet("845, Third Avenue, New York", "10022"), new PhoneDataSet("+18554003575")));

        UserDataSet dataSet = dbService.read(1);
        System.out.println(dataSet);

        dataSet = dbService.readByName("user2");
        System.out.println(dataSet);

        List<UserDataSet> dataSets = dbService.readAll();
        for (UserDataSet userDataSet : dataSets) {
            System.out.println(userDataSet);
        }

        dbService.shutdown();
    }
}