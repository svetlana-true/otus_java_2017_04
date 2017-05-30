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

        ATMdepartmentInstance atmDepartment = ATMdepartmentInstance.getInstance();

        atmDepartment.addATM( "ATM 0", new int[]{100,500,1000,5000}, new int[]{50,50,50,50});
        atmDepartment.addATM( "ATM 1", new int[]{100,500,1000}, new int[]{0,100,50});
        atmDepartment.addATM( "ATM 2", new int[]{100,500,1000,5000}, new int[]{10,50,100,50});

        Scanner in = new Scanner(System.in);
        Integer numberATM = -1;
        while ((numberATM < 0) || (numberATM > 2))
        {
            System.out.println("Set number of a ATM (between 0 and 2):");
            numberATM = Integer.parseInt(in.nextLine());
        }

        Integer takeOfMoney = 1;

        while (takeOfMoney.intValue() % 100 != 0)
        {
            System.out.println("Set amount to take of money (should be multiple of 100):");
            takeOfMoney = Integer.parseInt(in.nextLine());
        }

        atmDepartment.printAllAmount(numberATM);
        atmDepartment.printCurrentAmount(numberATM);

        List<Integer[]> results = atmDepartment.TakeOfMoney(numberATM, takeOfMoney);
        for (Integer[] result : results){
            System.out.println("Variations: " + Arrays.toString(result));
        }

        Integer sizeOfFull = 0;
        while ((sizeOfFull < 1) || (sizeOfFull > results.size()))
        {
            System.out.println("Set number of a needed variation (between 1 and " + results.size() + "): ");
            sizeOfFull = Integer.parseInt(in.nextLine());
        }

        atmDepartment.chooseVariationTakeOfMoney(numberATM, sizeOfFull.intValue());

        atmDepartment.printAllAmount(numberATM);
        atmDepartment.printCurrentAmount(numberATM);
        atmDepartment.setInitalAmount(numberATM);

        atmDepartment.setAllInitialAmount();
        atmDepartment.printCurrentAmount();

    }
}
