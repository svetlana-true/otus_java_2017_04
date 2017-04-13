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
        Supplier<Object> emptyString = () -> new String();
        Supplier<Object> emptyArray = () -> new ArrayList();
        Supplier<Object> emptyHashSet = () -> new HashSet();
        Supplier<Object> emptyTreeSet = () -> new TreeSet();
        Supplier<Object> emptyHashMap = () -> new HashMap();
        Supplier<Object> emptyTreeMap = () -> new TreeMap();

        WhatSize whatSize = new WhatSize();
        whatSize.findSize(emptyString, "String");
        whatSize.findSize(emptyArray, "ArrayList");
        whatSize.findSize(emptyHashSet, "HashSet");
        whatSize.findSize(emptyTreeSet, "TreeSet");
        whatSize.findSize(emptyHashMap, "HashMap");
        whatSize.findSize(emptyTreeMap, "TreeMap");


    }
}
