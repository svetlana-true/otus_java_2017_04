package otus;

/**
 * Created by svetlana on 4/12/17.
 */
class WhatSizeString {
    private int repeats = 10;
    WhatSizeString(int rep)
    {
        repeats = rep;
    }

    public void getRepeats()
    {
        System.out.println("Repeats: <" + Integer.toString(repeats) + ">");
    }
    private long getMemory() throws InterruptedException {
        System.gc();
        Thread.sleep(200);
        System.runFinalization();
        Thread.sleep(200);

        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    public void findSize(String input) throws InterruptedException {
        long AverageSize = 0L;
        long startMemory = 0L;
        long endMemory = 0L;
        for (int i = 0; i < repeats; i++)
        {
            startMemory = 0L;
            endMemory = 0L;
            startMemory = getMemory();
            String inputString = input;
            endMemory = getMemory();

            AverageSize += Math.round(1f * (endMemory - startMemory));
        }
        AverageSize /= repeats;
        System.out.println("Size of the string: <" + Long.toString(AverageSize) + ">");
    }

}