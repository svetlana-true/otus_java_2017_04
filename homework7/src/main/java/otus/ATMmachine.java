package otus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svetlana on 5/25/17.
 */
public class ATMmachine {
    private String nameATM = new String();
    private int[] values = {100,500,1000,5000};
    private int[] amounts = {10,10,10,10};
    private List<Integer[]> resultsTakeOf = new ArrayList<>();
    private Memento undo;

    private class Memento {
        private int[] initialAmounts;

        Memento()
        {
            System.arraycopy( amounts, 0, initialAmounts, 0, amounts.length );
        }

        void setAmount()
        {
            System.arraycopy( initialAmounts, 0, amounts, 0, initialAmounts.length );
        }
    }

    ATMmachine( String name, int[] amm)
    {
        nameATM = name;
        System.arraycopy( amm, 0, amounts, 0, amm.length );
        undo = new Memento();
    }

    void printAllAmount()
    {
        System.out.println(nameATM + ":");

        for (int i = 0; i< amounts.length; i++)
        {
            System.out.println(" value " + values[i] + "; ammount " + amounts[i]);
        }
    }

    int getAllAmount()
    {
        int result = 0;
        for (int i = 0; i< amounts.length; i++)
        {
            result += amounts[i] * values[i];
        }

        return result;
    }

    void printCurrentAmount()
    {
        System.out.println(nameATM + ": current amount " + getAllAmount());
    }

    void setInitalAmount()
    {
        undo.setAmount();
    }

    public List<Integer[]> TakeOfMoney (int takeOfMoney)
    {
        return TakeOfMoney (takeOfMoney, new int[4], 0);
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

    public int compute(int[] variation){
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

    void chooseVariationTakeOfMoney(int variation)
    {
        Integer[] var = resultsTakeOf.get(variation - 1);

        for (int i = 0; i < var.length; i++) {
            amounts[i] -= var[i];
        }
    }
}
