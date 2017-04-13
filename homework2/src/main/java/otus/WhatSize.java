package otus;
import java.util.function.Supplier;

/**
 * Created by svetlana on 4/12/17.
 */
class WhatSize
{
    private static long getMemory() throws InterruptedException
    {
        System.gc();
        Thread.sleep(200);
        System.runFinalization();
        Thread.sleep(200);
        System.gc();
        long totalMemory = Runtime.getRuntime().totalMemory();

        System.gc();
        Thread.sleep(200);
        System.runFinalization();
        Thread.sleep(200);
        System.gc();
        long freeMemory = Runtime.getRuntime().freeMemory();


        return  totalMemory - freeMemory;
    }

    public static void sizeOf(Supplier<Object> input, String nameOfCollection,
                                int numberOfRepeats, int countOfObjects)
            throws InterruptedException
    {
        long AverageSize = 0L;
        long startMemory = 0L;
        long endMemory = 0L;

        for (int i = 0; i < numberOfRepeats; i++)
        {
            Object[] objects = new Object[countOfObjects];

            startMemory = getMemory();

            for (int j = 0; j < countOfObjects; j++)
            {
                objects[j] = input.get();
            }

            endMemory = getMemory();

            //Using of object to the compiler can't
            objects[0] = null;

            AverageSize += Math.round(1f * (endMemory - startMemory) / countOfObjects);
        }

        AverageSize /= numberOfRepeats;
        System.out.println("Size of " + nameOfCollection + ": <"
                + Long.toString(AverageSize) + ">");
    }

}