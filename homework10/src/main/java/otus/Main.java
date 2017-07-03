package otus;

/**
 * Created by Светлана on 02.07.2017.
 */

import otus.base.DBService;
import otus.base.UserDataSet;
import otus.dbservice.DBServiceImpl;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        System.out.println("Here is my DBService. ");

        DBService dbService = new DBServiceImpl();

        String status = dbService.getLocalStatus();
        System.out.println("Status: " + status);

        dbService.save(new UserDataSet("user1", 12));
        dbService.save(new UserDataSet("user2", 21));

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