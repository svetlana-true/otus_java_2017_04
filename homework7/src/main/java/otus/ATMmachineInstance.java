package otus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svetlana on 5/25/17.
 */
public class ATMmachineInstance implements ATMmachine{
    private String nameATM = new String();
    private int[] values;
    private int[] amounts;
    private List<Integer[]> resultsTakeOf = new ArrayList<>();
    private Memento undo;

    private class Memento {
        int[] initialAmounts;

        Memento()
        {
            initialAmounts = new int [amounts.length];
            arrayCopy(amounts, initialAmounts);
        }

        void setAmount()
        {
            arrayCopy(initialAmounts, amounts);
        }
    }

    ATMmachineInstance(String name, int[] val, int[] amm)
    {
        nameATM = name;
        values = new int [val.length];
        amounts = new int [amm.length];
        arrayCopy(val, values);
        arrayCopy(amm, amounts);
        undo = new Memento();
    }

    private void arrayCopy(int [] first, int [] second)
    {
        System.arraycopy( first, 0, second, 0, first.length );
    }

    @Override
    public void printAllAmount()
    {
        System.out.println(nameATM + ":");

        for (int i = 0; i< amounts.length; i++)
        {
            System.out.println(" value " + values[i] + "; ammount " + amounts[i]);
        }
    }

    @Override
    public int getAllAmount()
    {
        int result = 0;
        for (int i = 0; i< amounts.length; i++)
        {
            result += amounts[i] * values[i];
        }

        return result;
    }

    @Override
    public void printCurrentAmount()
    {
        System.out.println(nameATM + ": current amount " + getAllAmount());
    }

    @Override
    public void setInitalAmount()
    {
        undo.setAmount();
    }

    @Override
    public List<Integer[]> TakeOfMoney (int takeOfMoney)
    {
        return TakeOfMoney (takeOfMoney, new int[amounts.length], 0);
    }

    private List<Integer[]> TakeOfMoney (int takeOfMoney, int[] variation, int position)
    {
        int value = compute(variation);

        List<Integer[]> list = new ArrayList<>();

        if (value < takeOfMoney){

            for (int i = position; i < values.length; i++) {

                if (amounts[i] > variation[i])
                {
                    int[] newvariation = variation.clone();
                    newvariation[i]++;
                    List<Integer[]> newList = TakeOfMoney(takeOfMoney, newvariation, i);
                    if (newList != null){
                        list.addAll(newList);
                    }
                }
            }
        } else if (value == takeOfMoney) {
            list.add(myCopy(variation));
        }
        resultsTakeOf = new ArrayList(list);

        return list;
    }

    private int compute(int[] variation){
        int res = 0;
        for (int i = 0; i < variation.length; i++) {
            res += values[i] * variation[i];
        }
        return res;
    }

    private Integer[] myCopy(int[] ar){
        Integer[] res = new Integer[ar.length];
        for (int i = 0; i < ar.length; i++) {
            res[i] = ar[i];
        }
        return res;
    }

    @Override
    public void chooseVariationTakeOfMoney(int variation)
    {
        Integer[] var = resultsTakeOf.get(variation - 1);

        for (int i = 0; i < var.length; i++) {
            amounts[i] -= var[i];
        }
    }
}
