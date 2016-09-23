package utils;

import java.util.concurrent.TimeUnit;

public class Utils {
    public static void waitTimeOut(long timeout, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
