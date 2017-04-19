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
        String[] first = new String[] { "one", "two", "three" };
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(first));
        System.out.println("ArrayList: " + Arrays.toString(arrayList.toArray()));

        String[] second = new String[] { "four", "five", "six" };
        OwnArrayList<String> ownArrayList = new OwnArrayList(second);
        System.out.println("Own ArrayList: " + Arrays.toString(ownArrayList.toArray()));

        addAll(arrayList, second);
        System.out.println("ArrayList with add: " + Arrays.toString(arrayList.toArray()));

        addAll(ownArrayList, first);
        System.out.println("Own ArrayList with add: " + Arrays.toString(ownArrayList.toArray()));


        String[] third = new String[] { "seven", "eight", "nine" };
        ArrayList<String> arrayList2 = new ArrayList<>(Arrays.asList(third));
        System.out.println("ArrayList2: " + Arrays.toString(arrayList2.toArray()));

        copy(ownArrayList, arrayList2);
        System.out.println("Copy ArrayList to own ArrayList: " + Arrays.toString(ownArrayList.toArray()));

        sort(arrayList);
        System.out.println("Sorted ArrayList: " + Arrays.toString(arrayList.toArray()));

        sort(ownArrayList);
        System.out.println("Sorted own ArrayList: " + Arrays.toString(ownArrayList.toArray()));
    }
}
