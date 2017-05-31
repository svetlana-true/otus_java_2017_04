package otus;

/**
 * Created by svetlana on 5/31/17.
 */
public class Cell {
    private int value;
    private int amount;

    Cell(int val, int amm)
    {
        value = val;
        amount = amm;
    }

    public int getValue()
    {
        return value;
    }

    public void setValue(int val)
    {
        value = val;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amm)
    {
        amount = amm;
    }

}
