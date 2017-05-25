package otus;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by svetlana on 5/25/17.
 */
public class ATMmachineRunner {

    public static void main(String args[]) throws Exception {
        System.out.println("Here is ATM machines's manager. ");

        ATMmachine atm1 = new ATMmachine(new int[]{50,50,50,50}, "ATM 1");
        ATMmachine atm2 = new ATMmachine(new int[]{0,100,0,100}, "ATM 2");
        ATMmachine atm3 = new ATMmachine(new int[]{10,50,100,50}, "ATM 3");

        atm3.printAllAmount();
        atm3.printCurrentAmount();
        List<Integer[]> results3 = atm3.TakeOfMoney(700);
        for (Integer[] result : results3){
            System.out.println("Variations: " + Arrays.toString(result));
        }

        Scanner in = new Scanner(System.in);
        System.out.println("Set number of a needed variation (between 1 and " + results3.size() + "): ");
        Integer sizeOfFull = Integer.parseInt(in.nextLine());
        atm3.chooseVariationTakeOfMoney(sizeOfFull.intValue());

        atm3.printAllAmount();
        atm3.printCurrentAmount();
        atm3.setInitalAmount();

    }
}
