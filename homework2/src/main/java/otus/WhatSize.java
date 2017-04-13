package otus;
import java.util.function.Supplier;

/**
 * Created by svetlana on 4/12/17.
 */
class WhatSize
{
    private int numberOfRepeats = 10;
    private int countOfObjects = 1000;


    private long getMemory() throws InterruptedException
    {
        System.gc();
        Thread.sleep(50);
        System.runFinalization();
        Thread.sleep(50);
        long totalMemory = Runtime.getRuntime().totalMemory();

        System.gc();
        Thread.sleep(50);
        System.runFinalization();
        Thread.sleep(50);
        long freeMemory = Runtime.getRuntime().freeMemory();


        return  totalMemory - freeMemory;
    }

    public void findSize(Supplier<Object> input, String nameOfCollection)
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

            AverageSize += Math.round(1f * (endMemory - startMemory) / countOfObjects);
        }

        System.out.println("Size of the " + nameOfCollection + ": <"
                + Long.toString(AverageSize / countOfObjects) + ">");
    }

}