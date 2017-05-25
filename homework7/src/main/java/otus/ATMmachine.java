package otus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svetlana on 5/25/17.
 */
public class ATMmachine {
    int[] values = {100,500,1000,5000};

    int[] initialAmounts = {10,10,10,10};
    int[] amounts = {10,10,10,10};

    String nameATM = new String();

    List<Integer[]> resultsTakeOf = new ArrayList<>();

    ATMmachine(int[] amm, String name)
    {
        System.arraycopy( amm, 0, amounts, 0, amm.length );
        System.arraycopy( amm, 0, initialAmounts, 0, amm.length );
        nameATM = name;
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
        System.arraycopy( initialAmounts, 0, amounts, 0, initialAmounts.length );
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
    /*List<Integer[]> results = solutions(values, ammounts, new int[5], 180, 0);

    for (Integer[] result : results){
            System.out.println(Arrays.toString(result));
        }



    public static List<Integer[]> solutions(int[] values, int[] ammounts, int[] variation, int price, int position){
        List<Integer[]> list = new ArrayList<>();
        int value = compute(values, variation);
        if (value < price){
            for (int i = position; i < values.length; i++) {
                if (ammounts[i] > variation[i]){
                    int[] newvariation = variation.clone();
                    newvariation[i]++;
                    List<Integer[]> newList = solutions(values, ammounts, newvariation, price, i);
                    if (newList != null){
                        list.addAll(newList);
                    }
                }
            }
        } else if (value == price) {
            list.add(myCopy(variation));
        }
        return list;
    }

    public static int compute(int[] values, int[] variation){
        int ret = 0;
        for (int i = 0; i < variation.length; i++) {
            ret += values[i] * variation[i];
        }
        return ret;
    }

    public static Integer[] myCopy(int[] ar){
        Integer[] ret = new Integer[ar.length];
        for (int i = 0; i < ar.length; i++) {
            ret[i] = ar[i];
        }
        return ret;
    }*/
}
