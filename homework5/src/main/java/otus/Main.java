package otus;

import MyOwnFramework.MyOwnFrameworkClass;

/**
 * Created by svetlana on 5/18/17.
 */
public class Main {
    public void main(String[] args) throws Exception {
        if (args.length < 2)
        {
            System.out.println("Wrong arguments of command line");
            System.exit(0);
        }

        MyOwnFrameworkClass myTestFramework = new MyOwnFrameworkClass();

        switch (args[0])
        {
            case "classes":
                for (int i = 1; i < args.length; ++i)
                {
                    myTestFramework.addClass(args[i]);
                }
                break;

            case "packages":
                for (int i = 1; i < args.length; ++i)
                {
                    myTestFramework.addPackage(args[i]);
                }
                break;

            default:
                System.out.println("Wrong arguments of command line");
                System.exit(0);
        }

        MyOwnFrameworkClass.run();
    }
}
