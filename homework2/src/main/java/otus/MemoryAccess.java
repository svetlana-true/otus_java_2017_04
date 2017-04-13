package otus;
/**
 * Created by svetlana on 4/11/17.
 */

import java.util.*;
import java.util.function.Supplier;


public class MemoryAccess
{

    public static void main(String[] args) throws Exception
    {
        int repeats = 10;
        int objects = 1000;//1000000;

        Scanner in = new Scanner(System.in);
        System.out.println("Set a count of the elements in full arrays: ");
        Integer sizeOfFull = Integer.parseInt(in.nextLine());
        System.out.println("Wait 15 seconds... ");

        Supplier<Object> emptyString = () -> new String();

        Supplier<Object> emptyCharArray = () -> new char[0];
        Supplier<Object> emptyIntArray = () -> new int[0];
        Supplier<Object> emptyLongArray = () -> new long[0];
        Supplier<Object> emptyDoubleArray = () -> new double[0];
        Supplier<Object> emptyArray = () -> new ArrayList();
        Supplier<Object> emptyHashSet = () -> new HashSet();
        Supplier<Object> emptyHashMap = () -> new HashMap();

        Supplier<Object> emptyTreeSet = () -> new TreeSet();
        Supplier<Object> emptyTreeMap = () -> new TreeMap();

        Supplier<Object> fullCharArray = () -> new char[sizeOfFull];
        Supplier<Object> fullIntArray = () -> new int[sizeOfFull];
        Supplier<Object> fullLongArray = () -> new long[sizeOfFull];
        Supplier<Object> fullDoubleArray = () -> new double[sizeOfFull];
        Supplier<Object> fullArray = () -> new ArrayList(sizeOfFull);
        Supplier<Object> fullHashSet = () -> new HashSet(sizeOfFull);
        Supplier<Object> fullHashMap = () -> new HashMap(sizeOfFull);


        WhatSize whatSize = new WhatSize();
        whatSize.sizeOf(emptyString, "empty String", repeats, objects);

        whatSize.sizeOf(emptyCharArray, "empty CharArray", repeats, objects);
        whatSize.sizeOf(fullCharArray, "full CharArray", repeats, objects);

        whatSize.sizeOf(emptyIntArray, "empty IntArray", repeats, objects);
        whatSize.sizeOf(fullIntArray, "full IntArray", repeats, objects);

        whatSize.sizeOf(emptyLongArray, "empty LongArray", repeats, objects);
        whatSize.sizeOf(fullLongArray, "full LongArray", repeats, objects);

        whatSize.sizeOf(emptyDoubleArray, "empty DoubleArray", repeats, objects);
        whatSize.sizeOf(fullDoubleArray, "full DoubleArray", repeats, objects);

        whatSize.sizeOf(emptyArray, "empty ArrayList", repeats, objects);
        whatSize.sizeOf(fullArray, "full ArrayList", repeats, objects);

        whatSize.sizeOf(emptyHashSet, "empty HashSet", repeats, objects);
        whatSize.sizeOf(fullHashSet, "full HashSet", repeats, objects);

        whatSize.sizeOf(emptyHashMap, "empty HashMap", repeats, objects);
        whatSize.sizeOf(fullHashMap, "full HashMap", repeats, objects);

        whatSize.sizeOf(emptyTreeSet, "empty TreeSet", repeats, objects);

        whatSize.sizeOf(emptyTreeMap, "empty TreeMap", repeats, objects);

    }
}
