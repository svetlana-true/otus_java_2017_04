package otus;

import java.util.List;

/**
 * Created by svetlana on 5/30/17.
 */
public interface ATMdepartment {
    void addATM(String nameATM, int [] valuesATM, int [] amountATM);

    void printAllAmount(int numberATM);

    void getAllAmount(int numberATM);

    void printCurrentAmount(int numberATM);

    void setInitalAmount(int numberATM);

    void setAllInitialAmount();

    void printCurrentAmount();

    List<Integer[]> TakeOfMoney(int numberATM, int takeOfMoney);

    void chooseVariationTakeOfMoney(int numberATM, int variation);
}
