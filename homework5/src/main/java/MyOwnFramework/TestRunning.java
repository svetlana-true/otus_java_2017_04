package MyOwnFramework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by svetlana on 5/18/17.
 */
public class TestRunning {

    private AccessToAnnotation testCase = null;

    public TestRunning(AccessToAnnotation testCase) throws Exception {
        if (testCase != null) {
            this.testCase = testCase;
        }
        else {
            throw new Exception("Test case failed");
        }
    }

    public void run() throws Exception {
        try {
            for (Method testMethod : testCase.getTestMethods()) {
                Object instance = createInstance();
                runBefore(instance, testCase.getBeforeMethod());
                runTest(instance, testMethod);
                runAfter(instance, testCase.getAfterMethod());
            }
        } catch (Exception e)
        {
            Exception exception = new Exception("");
            exception.initCause(e);
            throw  exception;
        }

    }

    public Object createInstance() throws IllegalAccessException, InstantiationException {
        return testCase.getTestClass().newInstance();
    }

    private void runBefore(Object obj, Method beforeMethod) throws InvocationTargetException, IllegalAccessException {
        if (beforeMethod != null) {
            beforeMethod.invoke(obj);
        }
    }

    private void runAfter(Object obj, Method afterMethod) throws InvocationTargetException, IllegalAccessException {
        if (afterMethod != null) {
            afterMethod.invoke(obj);
        }
    }

    private void runTest(Object obj, Method testMethod) throws InvocationTargetException, IllegalAccessException {
        if (testMethod != null) {
            testMethod.invoke(obj);
        }
    }
}
