package otus;
/**
 * Created by svetlana on 4/11/17.
 */

import java.util.Scanner;
import java.util.function.Supplier;
import otus.SetString;
import otus.WhatSizeString;


public class MemoryAccess {

    public static void main(String[] args) throws Exception
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Set count of repeats: ");
        Integer counts = Integer.parseInt(in.nextLine());

        WhatSizeString whatSizeString = new WhatSizeString(counts);
        whatSizeString.getRepeats();

        Supplier<String> string1 = () -> new String();
        System.out.println("String1: <" + string1.get() + ">");
        whatSizeString.findSize(string1.get());

        Supplier<SetString> setString = ()->{
            System.out.println("Set string: ");
            String name = in.nextLine();
            return new SetString(name);
        };
        SetString string2 = setString.get();
        System.out.println("String2: <" + string2.getName() + ">");
        whatSizeString.findSize(string2.getName());

    }
}
