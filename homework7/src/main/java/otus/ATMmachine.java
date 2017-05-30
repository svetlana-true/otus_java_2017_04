package otus;

import java.util.List;

/**
 * Created by svetlana on 5/30/17.
 */
public interface ATMmachine {
    void printAllAmount();

    int getAllAmount();

    void printCurrentAmount();

    void setInitalAmount();

    List<Integer[]> TakeOfMoney (int takeOfMoney);

    void chooseVariationTakeOfMoney(int variation);
}
