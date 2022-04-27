package util;

public class TestUtil {

    public static long PAGE_LOAD_TIMEOUT = 20;
    public static long IMPLICIT_WAIT = 20;


    public static void timeout(long timeout) {
        try {
            Thread.sleep(timeout);
        }catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


}
