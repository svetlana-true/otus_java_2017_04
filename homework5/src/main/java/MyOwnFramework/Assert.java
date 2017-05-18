package MyOwnFramework;

/**
 * Created by svetlana on 5/18/17.
 */
public final class Assert {

    public static void assertTrue(String message, boolean condition) {
        if (!condition) {
            assertFail(message);
        }
    }

    public static void assertNotNull(String message, Object obj) {
        assertTrue(message,obj != null);
    }

    public static void assertFail(String message) {
        //throw new AssertionError(message);
        if (message == null) {
            throw new RuntimeException();
        }
        else {
            throw new RuntimeException(message);
        }
    }

}