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

        ATMdepartment atmDepartment = ATMdepartment.getInstance();
        atmDepartment.addATM( "ATM 0", new int[]{50,50,50,50});
        atmDepartment.addATM( "ATM 1", new int[]{0,100,0,100});
        atmDepartment.addATM( "ATM 2", new int[]{10,50,100,50});

        atmDepartment.printAllAmount(2);
        atmDepartment.printCurrentAmount(2);

        List<Integer[]> results3 = atmDepartment.TakeOfMoney(2, 700);
        for (Integer[] result : results3){
            System.out.println("Variations: " + Arrays.toString(result));
        }

        Scanner in = new Scanner(System.in);
        System.out.println("Set number of a needed variation (between 1 and " + results3.size() + "): ");
        Integer sizeOfFull = Integer.parseInt(in.nextLine());
        atmDepartment.chooseVariationTakeOfMoney(2, sizeOfFull.intValue());

        atmDepartment.printAllAmount(2);
        atmDepartment.printCurrentAmount(2);
        atmDepartment.setInitalAmount(2);

        atmDepartment.setAllInitialAmount();

    }
}
