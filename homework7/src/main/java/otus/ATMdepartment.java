package otus;

import java.util.List;

/**
 * Created by svetlana on 5/30/17.
 */
public interface ATMdepartment {
    void addATM(String nameATM, int [] valuesBacknots, int [] amountBacknots);

    void printAllAmount(int indexATM);

    void getAllAmount(int indexATM);

    void printCurrentAmount(int indexATM);

    void setInitalAmount(int indexATM);

    void setAllInitialAmount();

    void printCurrentAmount();

    List<Integer[]> TakeOfMoney(int indexATM, int takeOfMoney);

    void chooseVariationTakeOfMoney(int indexATM, int variation);
}
