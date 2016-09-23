package utils;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;

public class WebDriverOptionalWait extends WebDriverWait {
    private final Clock clock = new SystemClock();
    private final WebDriver webDriver;
    private final Sleeper systemSleeper = Sleeper.SYSTEM_SLEEPER;
    private Duration duration;
    private Duration period;

    public WebDriverOptionalWait(WebDriver driver, long timeOutInSeconds) {
        super(driver, timeOutInSeconds);
        this.duration = new Duration(timeOutInSeconds, TimeUnit.SECONDS);
        this.period = new Duration(500L, TimeUnit.MILLISECONDS);
        webDriver = driver;
    }

    public WebDriverOptionalWait(WebDriver driver, long timeOutInSeconds, long sleepInMillis) {
        super(driver, timeOutInSeconds, sleepInMillis);
        this.duration = new Duration(timeOutInSeconds, TimeUnit.SECONDS);
        this.period = new Duration(sleepInMillis, TimeUnit.MILLISECONDS);
        webDriver = driver;
    }

    public WebDriverOptionalWait(WebDriver driver, Clock clock, Sleeper sleeper, long timeOutInSeconds, long sleepTimeOut) {
        super(driver, clock, sleeper, timeOutInSeconds, sleepTimeOut);
        this.duration = new Duration(timeOutInSeconds, TimeUnit.SECONDS);
        this.period = new Duration(sleepTimeOut, TimeUnit.MILLISECONDS);
        webDriver = driver;
    }

    @Override
    public void until(Predicate<WebDriver> isTrue) {
        try {
            super.until(isTrue);
        } catch (TimeoutException e) {/**/}
    }

    @Override
    public <V> V until(Function<? super WebDriver, V> isTrue) {
        long end = this.clock.laterBy(this.duration.in(TimeUnit.MILLISECONDS));
        while (true) {
            try {
                V e = isTrue.apply(this.webDriver);
                if (e != null && Boolean.class.equals(e.getClass())) {
                    if (Boolean.TRUE.equals(e)) {
                        return e;
                    }
                } else if (e != null) {
                    return e;
                }
                if (!this.clock.isNowBefore(end)) {
                    return e;
                }
            } catch (Throwable var8) {/**/}
            try {
                this.systemSleeper.sleep(this.period);
            } catch (InterruptedException var7) {
                Thread.currentThread().interrupt();
                throw new WebDriverException(var7);
            }
        }
    }
}
