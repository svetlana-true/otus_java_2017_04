package otus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by svetlana on 5/25/17.
 */
public class ATMmachineInstance implements ATMmachine{
    private String nameATM = new String();
    private ArrayList<Cell> cells = new ArrayList();
    private List<Integer[]> resultsTakeOf = new ArrayList<>();
    private Memento undo;

    private class Memento {
        int[] initialAmounts;
//        private Cell[] cells;

        Memento(int[] amm)
        {
            initialAmounts = new int [amm.length];
            arrayCopy(amm, initialAmounts);
        }

        void setAmount()
        {
            int i = 0;
            for (Cell cl: cells)
            {
                cl.setAmount(initialAmounts[i]);
                i++;
            }
        }
    }

    ATMmachineInstance(String name, int[] val, int[] amm)
    {
        nameATM = name;
        for (int i = 0; i < val.length; i++)
        {
            cells.add(new Cell(val[i], amm[i]));
        }

        undo = new Memento(amm);
    }

    private void arrayCopy(int [] first, int [] second)
    {
        System.arraycopy( first, 0, second, 0, first.length );
    }

    @Override
    public void printAllAmount()
    {
        System.out.println(nameATM + ":");

        for(Cell cl : cells)
        {
            System.out.println(" value " + cl.getValue() + "; ammount " + cl.getAmount());
        }
    }

    @Override
    public int getAllAmount()
    {
        int result = 0;
        for(Cell cl : cells)
        {
            result += cl.getValue() * cl.getAmount();
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
        return TakeOfMoney (takeOfMoney, new int[cells.size()], 0);
    }

    private List<Integer[]> TakeOfMoney (int takeOfMoney, int[] variation, int position)
    {
        int value = compute(variation);

        List<Integer[]> list = new ArrayList<>();

        if (value < takeOfMoney){

            for (int i = position; i < cells.size(); i++) {

                if (cells.get(i).getAmount() > variation[i])
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
            res += cells.get(i).getValue() * variation[i];
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
            cells.get(i).setAmount( cells.get(i).getAmount()- var[i] );
        }
    }
}
