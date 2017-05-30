package otus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Светлана on 26.05.2017.
 */
public class ATMdepartmentInstance implements ATMdepartment{
    private ArrayList<ATMmachine> atmMachineArrayList = new ArrayList();
    private int [] values;

    @Override
    public void addATM(String nameATM, int [] valuesATM, int [] amountATM)
    {
        atmMachineArrayList.add(new ATMmachineInstance(nameATM, valuesATM, amountATM));
    }
    private static class ATMdepartmentHolder{
        private final static ATMdepartmentInstance instance = new ATMdepartmentInstance();
    }

    public static ATMdepartmentInstance getInstance() {
        return ATMdepartmentHolder.instance;
    }

    @Override
    public void printAllAmount(int numberATM)
    {
        atmMachineArrayList.get(numberATM).printAllAmount();
    }

    @Override
    public void getAllAmount(int numberATM)
    {
        atmMachineArrayList.get(numberATM).getAllAmount();
    }

    @Override
    public void printCurrentAmount(int numberATM)
    {
        atmMachineArrayList.get(numberATM).printCurrentAmount();
    }

    @Override
    public void setInitalAmount(int numberATM)
    {
        atmMachineArrayList.get(numberATM).setInitalAmount();
    }

    @Override
    public void setAllInitialAmount()
    {
        System.out.println("Set all initial amounts");

        for (ATMmachine atm : atmMachineArrayList)
        {
            atm.setInitalAmount();
        }
    }

    @Override
    public void printCurrentAmount()
    {
        System.out.println("-------All current amounts-------");

        for (ATMmachine atm : atmMachineArrayList)
        {
            atm.printCurrentAmount();
        }
        System.out.println("---------------------------------");

    }

    @Override
    public List<Integer[]> TakeOfMoney(int numberATM, int takeOfMoney)
    {
       return atmMachineArrayList.get(numberATM).TakeOfMoney(takeOfMoney);
    }

    @Override
    public void chooseVariationTakeOfMoney(int numberATM, int variation)
    {
        atmMachineArrayList.get(numberATM).chooseVariationTakeOfMoney(variation);
    }
}
