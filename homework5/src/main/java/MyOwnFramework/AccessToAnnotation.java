package MyOwnFramework;

import MyOwnFramework.annotations.After;
import MyOwnFramework.annotations.Before;
import MyOwnFramework.annotations.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by svetlana on 5/18/17.
 */
public class AccessToAnnotation {
    private Class<?> clazz = null;
    private Method beforeMethod = null;
    private Method afterMethod = null;
    private List<Method> testMethods = null;

    public AccessToAnnotation(String className) throws Exception {
        try {
            clazz = Class.forName(className);
        } catch (Exception e) {
            Exception exception = new Exception("Can't found test class: " + className);
            exception.initCause(e);
            throw exception;
        }

    }

    public AccessToAnnotation(Class<?> clazz) throws Exception {
        this.clazz = clazz;
    }


    public Class<?> getTestClass()
    {
        return clazz;
    }


    public List<Method> getTestMethods() {
        if (testMethods != null)
            return testMethods;

        testMethods = getMethods(Test.class);

        return testMethods;
    }

    public Method getBeforeMethod() throws Exception {
        if (beforeMethod != null)
            return beforeMethod;

        List<Method> beforeMethods = getMethods(Before.class);

        if (beforeMethods.isEmpty()) {
            beforeMethod = null;
        } else if (beforeMethods.size() == 1) {
            beforeMethod = beforeMethods.get(0);

        } else {
            beforeMethod = null;
            throw new Exception("Too much before methods: " + beforeMethods.size());
        }

        return beforeMethod;
    }

    public Method getAfterMethod() throws Exception {
        if (afterMethod != null)
            return afterMethod;

        List<Method> afterMethods = getMethods(After.class);

        if (afterMethods.isEmpty()) {
            afterMethods = null;
        } else if (afterMethods.size() == 1) {
            afterMethod = afterMethods.get(0);
        } else {
            afterMethod = null;
            throw new Exception("Too much after methods: " + afterMethods.size());
        }

        return afterMethod;
    }

    private List<Method> getMethods(Class<? extends Annotation> annotationClass)
    {
        return Arrays.stream(clazz.getMethods())
                .filter(method -> method.isAnnotationPresent(annotationClass))
                .collect(Collectors.toList());
    }
}
