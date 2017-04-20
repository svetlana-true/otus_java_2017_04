package otus;

import java.util.ArrayList;
import java.util.Arrays;

import static java.util.Collections.addAll;
import static java.util.Collections.copy;
import static java.util.Collections.sort;

/**
 * Created by svetlana on 4/19/17.
 */
public class TestArrayList
{
    public static void main(String[] args) throws Exception
    {
        String[] arrayForOwnList = new String[] { "one", "two", "three" };;
        OwnArrayList<String> ownArrayList = new OwnArrayList(arrayForOwnList);
        System.out.println("Own ArrayList: " + Arrays.toString(ownArrayList.toArray()));

        String[] arrayForAdd = new String[] { "four", "five", "six" };
        addAll(ownArrayList, arrayForAdd);
        System.out.println("Testing of \"addAll\": add " +  Arrays.toString(arrayForAdd) + " in own ArrayList: " + Arrays.toString(ownArrayList.toArray()));

        String[] arrayForList = new String[] {"seven", "eight", "nine" };
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(arrayForList));
        System.out.println("ArrayList: " + Arrays.toString(arrayList.toArray()));

        copy(ownArrayList, arrayList);
        System.out.println("Testing of \"copy\": copy ArrayList to own ArrayList: " + Arrays.toString(ownArrayList.toArray()));

        sort(ownArrayList);
        System.out.println("Testing of \"sort\": sort for own ArrayList: " + Arrays.toString(ownArrayList.toArray()));

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        Integer[] intArrayForOwnList = new Integer[] { 1, 2, 3 };
        OwnArrayList<Integer> ownArrayListInt = new OwnArrayList(intArrayForOwnList);
        System.out.println("Own ArrayList: " + Arrays.toString(ownArrayListInt.toArray()));

        Integer[] intArrayForAdd = new Integer[] { 4, 5, 6 };
        addAll(ownArrayListInt, intArrayForAdd);
        System.out.println("Testing of \"addAll\": add " + Arrays.toString(intArrayForAdd) + " in own ArrayList: " + Arrays.toString(ownArrayListInt.toArray()));

        Integer[] intArrayForList = new Integer[] { 7, 8, 9 };
        ArrayList<Integer> arrayListInt = new ArrayList<>(Arrays.asList(intArrayForList));
        System.out.println("ArrayList: " + Arrays.toString(arrayListInt.toArray()));

        copy(ownArrayListInt, arrayListInt);
        System.out.println("Testing of \"copy\": copy ArrayList to own ArrayList: " + Arrays.toString(ownArrayListInt.toArray()));

        sort(ownArrayListInt);
        System.out.println("Testing of \"sort\": sort for own ArrayList: " + Arrays.toString(ownArrayListInt.toArray()));


    }
}
