package otus;

import MyOwnFramework.MyOwnFrameworkClass;

/**
 * Created by svetlana on 5/18/17.
 */
public class Main {
    public void main(String[] args) throws Exception {

        MyOwnFrameworkClass myOwnFrameworkClass = new MyOwnFrameworkClass();

        switch (args[0])
        {
            case "classes":
                for (int i = 1; i < args.length; ++i)
                {
                    myOwnFrameworkClass.addClass(args[i]);
                }
                break;

            case "packages":
                for (int i = 1; i < args.length; ++i)
                {
                    myOwnFrameworkClass.addPackage(args[i]);
                }
                break;

            default:
                System.out.println("Enter the line with format \"classes name_of_class\" or \"packages name_of_package\"");
                System.exit(0);
        }

        myOwnFrameworkClass.run();
    }
}
