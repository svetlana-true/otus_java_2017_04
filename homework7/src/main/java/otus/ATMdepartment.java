package otus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Светлана on 26.05.2017.
 */
public class ATMdepartment {
    private ArrayList<ATMmachine> atmMachineArrayList = new ArrayList();

    public void addATM(String nameATM, int [] amountATM)
    {
        atmMachineArrayList.add(new ATMmachine(nameATM, amountATM));
    }
    private static class ATMdepartmentHolder{
        private final static ATMdepartment instance = new ATMdepartment();
    }

    public static ATMdepartment getInstance() {
        return ATMdepartmentHolder.instance;
    }

    void printAllAmount(int numberATM)
    {
        atmMachineArrayList.get(numberATM).printAllAmount();
    }

    void getAllAmount(int numberATM)
    {
        atmMachineArrayList.get(numberATM).getAllAmount();
    }

    void printCurrentAmount(int numberATM)
    {
        atmMachineArrayList.get(numberATM).printCurrentAmount();
    }

    void setInitalAmount(int numberATM)
    {
        atmMachineArrayList.get(numberATM).setInitalAmount();
    }

    void setAllInitialAmount()
    {
        for (ATMmachine atm : atmMachineArrayList)
        {
            atm.setInitalAmount();
        }
    }

    List<Integer[]> TakeOfMoney(int numberATM, int takeOfMoney)
    {
       return atmMachineArrayList.get(numberATM).TakeOfMoney(takeOfMoney);
    }

    void chooseVariationTakeOfMoney(int numberATM, int variation)
    {
        atmMachineArrayList.get(numberATM).chooseVariationTakeOfMoney(variation);
    }
}
