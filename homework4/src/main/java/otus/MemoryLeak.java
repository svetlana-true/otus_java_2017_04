package otus;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

/**
 * Created by svetlana on 4/27/17.
 */
public class MemoryLeak {

    public static void main(String... args) throws Exception {

        System.out.println("Starting pid: " + ManagementFactory.getRuntimeMXBean().getName());

        Scanner in = new Scanner(System.in);
        System.out.println("Set path to log file: ");

        // Create file for logging
        //For UNIX system
        String path = in.nextLine();//File.separator + "tmp" + File.separator + "MyLogFile.log";
        File file = new File(path);
        file.getParentFile().mkdirs();
        file.createNewFile();

        FileHandler fh = new FileHandler(path);//"/tmp/MyLogFile.log");
        SimpleFormatter formatter = new SimpleFormatter();
        fh.setFormatter(formatter);

        MemoryAnalytics memoryAnalytics = MemoryAnalytics.getInstance();
        memoryAnalytics.pSetLogger(fh);
        memoryAnalytics.startGCMonitor();


        System.out.println("Set a count of the elements in full arrays (better about 5000000): ");
        int size = Integer.parseInt(in.nextLine());

        Object[] array = new Object[size];
        System.out.println("Array of size: " + array.length + " created");

        int period = 1 + size/10000;
        int n = 0;
        int currentSize = size;
        System.out.println("Starting the loop");
        while (n < Integer.MAX_VALUE) {
            int i = n % currentSize;
            array[i] = new String(new char[0]);
            n++;
            if (n % currentSize == 0) {
                System.out.println("Created " + n + " objects");
                System.out.println("Creating new array of size: " + size);

                currentSize = size;
                array = new Object[currentSize];
                Thread.sleep(period);

            }
        }
        memoryAnalytics.stopGCMonitor();

    }
}
