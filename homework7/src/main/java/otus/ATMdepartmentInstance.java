package otus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Светлана on 26.05.2017.
 */
public class ATMdepartmentInstance implements ATMdepartment{
    private ArrayList<ATMmachine> atmMachineArrayList = new ArrayList();

    @Override
    public void addATM(String nameATM, int [] valuesBacknots, int [] amountBacknots)
    {
        atmMachineArrayList.add(new ATMmachineInstance(nameATM, valuesBacknots, amountBacknots));
    }
    private static class ATMdepartmentHolder{
        private final static ATMdepartmentInstance instance = new ATMdepartmentInstance();
    }

    public static ATMdepartmentInstance getInstance() {
        return ATMdepartmentHolder.instance;
    }

    @Override
    public void printAllAmount(int indexATM)
    {
        atmMachineArrayList.get(indexATM).printAllAmount();
    }

    @Override
    public void getAllAmount(int indexATM)
    {
        atmMachineArrayList.get(indexATM).getAllAmount();
    }

    @Override
    public void printCurrentAmount(int indexATM)
    {
        atmMachineArrayList.get(indexATM).printCurrentAmount();
    }

    @Override
    public void setInitalAmount(int indexATM)
    {
        atmMachineArrayList.get(indexATM).setInitalAmount();
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
    public List<Integer[]> TakeOfMoney(int indexATM, int takeOfMoney)
    {
       return atmMachineArrayList.get(indexATM).TakeOfMoney(takeOfMoney);
    }

    @Override
    public void chooseVariationTakeOfMoney(int indexATM, int variation)
    {
        atmMachineArrayList.get(indexATM).chooseVariationTakeOfMoney(variation);
    }
}
