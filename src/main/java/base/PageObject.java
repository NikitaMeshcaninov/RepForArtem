package base;

import utils.Timeout;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WebDriverOptionalWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public abstract class PageObject {
    private static final long waitForTimeoutInSeconds = 7;
    protected WebDriver driver;
    protected Logger logger = Logger.getLogger(PageObject.class.getName());
    private Random random = new Random();
    protected AtomicInteger screenShotNumber;

    public PageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private static int obtainVerticalScrollPosition(WebDriver driver) {
        Long scrollLong = (Long) ((JavascriptExecutor) driver).executeScript("return (window.pageYOffset !== undefined) ? window.pageYOffset : (document.documentElement || document.body.parentNode || document.body).scrollTop;");
        return scrollLong.intValue();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void waitCondition(ExpectedCondition<?> condition) {
        new WebDriverWait(getDriver(), Timeout.DEFAULT_IMPLICITLY_WAIT).until(condition);
    }

    public WebElementFacade element(WebElement webElement) {
        return element(webElement, TimeUnit.SECONDS.toMillis(waitForTimeoutInSeconds));
    }

    public WebElementFacade element(WebElement webElement, Long waitForTimeoutInMilliseconds) {
        return WebElementFacade.wrapWebElement(driver, webElement, waitForTimeoutInMilliseconds);
    }

    public List<WebElementFacade> elements(List<WebElement> webElements) {
        return webElements.stream().map(this::element).collect(Collectors.toList());
    }

    public void selectRandomChildElement(List<WebElementFacade> listOfWebElement) {
        Assert.assertFalse("List of child elements is empty", listOfWebElement == null || listOfWebElement.size() == 0);
        listOfWebElement.get(random.nextInt(listOfWebElement.size())).click();
    }

    public void selectChildElementByText(List<WebElementFacade> listOfWebElement, String text) {
        Assert.assertFalse("List of child elements is empty", listOfWebElement == null || listOfWebElement.size() == 0);
        listOfWebElement.stream().filter(element -> (element).containsText(text)).forEach(WebElementFacade::click);
    }

    public String selectRandomOptionText(CustomSelect select) {
        select.selectRandomValue();
        return select.getFirstSelectedOptionText();
    }

    public String selectRandomOptionValue(CustomSelect select) {
        select.selectRandomValue();
        return select.getFirstSelectedOptionValue();
    }

    public boolean waitForAlertPresent(Long timeoutInSeconds) {
        try {
            waitFor().withTimeout(timeoutInSeconds, TimeUnit.SECONDS)
                    .until(CustomExpectedConditions.alertIsPresent());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public WebDriverWait waitFor() {
        return new WebDriverWait(getDriver(), 30L);
    }

    public WebDriverWait waitFor(Long timeout) {
        return new WebDriverWait(getDriver(), timeout);
    }


    public boolean isElementPresent(By locator) {
        return getDriver().findElements(locator).size() > 0;
    }

    public boolean isElementCurrentlyPresent(By locator) {
        getDriver().manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        int size = getDriver().findElements(locator).size();
        getDriver().manage().timeouts().implicitlyWait(Timeout.DEFAULT_IMPLICITLY_WAIT, TimeUnit.SECONDS);
        return size > 0;
    }

    public void waitTimeOut(long timeout) {
        waitTimeOut(timeout / 1000, TimeUnit.SECONDS);
    }

    public void waitTimeOut(long timeout, TimeUnit timeUnit) {
        try {
            if (timeUnit.toSeconds(timeout) >= 1) {
                logger.info("Waiting " + timeout + " " + timeUnit.name().toLowerCase() + "...");
            }
            timeUnit.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getHiddenValueByJS(String id) {
        return element(driver.findElement(By.id(id))).getHiddenValueByJS();
    }

    @SuppressWarnings("unchecked")
    public byte[] makeFullScreenshot(WebDriver driver) throws IOException, InterruptedException {
        scrollVerticallyTo(driver, 0);
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES))) {
            BufferedImage image = ImageIO.read(inputStream);
            closeInputSteam(inputStream);
            int capturedWidth = image.getWidth();
            int capturedHeight = image.getHeight();
            long longScrollHeight = ((Long) ((JavascriptExecutor) driver).executeScript("return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight,document.body.offsetHeight, document.documentElement.offsetHeight,document.body.clientHeight, document.documentElement.clientHeight);"));
            Double devicePixelRatio = ((Number) ((JavascriptExecutor) driver).executeScript("window.devicePixelRatio = window.devicePixelRatio || window.screen.deviceXDPI / window.screen.logicalXDPI; var pr = window.devicePixelRatio; if (pr != undefined && pr != null) return pr; else return 1.0;")).doubleValue();
            int scrollHeight = (int) longScrollHeight;
            int adaptedCapturedHeight = (int) ((double) capturedHeight / devicePixelRatio);
            BufferedImage resultingImage;
            if (Math.abs(adaptedCapturedHeight - scrollHeight) > 40) {
                int times = scrollHeight / adaptedCapturedHeight;
                int leftover = scrollHeight % adaptedCapturedHeight;
                BufferedImage tiledImage = new BufferedImage(capturedWidth, (int) ((double) scrollHeight * devicePixelRatio), 1);
                Graphics2D g2dTile = tiledImage.createGraphics();
                g2dTile.drawImage(image, 0, 0, null);
                int scroll = 0;

                BufferedImage lastPart;
                for (int nextImage = 0; nextImage < times - 1; ++nextImage) {
                    scroll += adaptedCapturedHeight;
                    scrollVerticallyTo(driver, scroll);
                    try (ByteArrayInputStream inputStream2 = new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES))) {
                        lastPart = ImageIO.read(inputStream2);
                        closeInputSteam(inputStream);
                        g2dTile.drawImage(lastPart, 0, (nextImage + 1) * capturedHeight, null);
                    }
                }

                if (leftover > 0) {
                    scroll += adaptedCapturedHeight;
                    scrollVerticallyTo(driver, scroll);
                    try (ByteArrayInputStream inputStream3 = new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES))) {
                        BufferedImage var21 = ImageIO.read(inputStream3);
                        lastPart = var21.getSubimage(0, var21.getHeight() - (int) ((double) leftover * devicePixelRatio), var21.getWidth(), leftover);
                        g2dTile.drawImage(lastPart, 0, times * capturedHeight, null);
                    }
                }

                scrollVerticallyTo(driver, 0);
                resultingImage = tiledImage;
            } else {
                resultingImage = image;
            }
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                ImageIO.write(resultingImage, "png", baos);
                baos.flush();
                byte[] bytes = baos.toByteArray();
                baos.reset();
                IOUtils.closeQuietly(baos);
                return bytes;
            }
        }
    }

    protected void closeInputSteam(ByteArrayInputStream inputStream) throws IOException {
        inputStream.reset();
        IOUtils.closeQuietly(inputStream);
        inputStream.close();
    }

    public void scrollVerticallyTo(WebDriver driver, int scroll) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, " + scroll + ");");
        try {
            waitUntilItIsScrolledToPosition(driver, scroll);
        } catch (InterruptedException var3) {
            logger.info("Interrupt error during scrolling occurred. " + var3);
        }

    }

    private void waitUntilItIsScrolledToPosition(WebDriver driver, int scrollPosition) throws InterruptedException {
        int time = 250;
        for (boolean isScrolledToPosition = false; time >= 0 && !isScrolledToPosition; isScrolledToPosition = Math.abs(obtainVerticalScrollPosition(driver) - scrollPosition) < 3) {
            Thread.sleep(50L);
            time -= 50;
        }

    }

    public byte[] takeScreenshot() {
        byte[] screenshot = null;
        RemoteWebDriver remote = (RemoteWebDriver) driver;
        try {
            screenshot = makeFullScreenshot(remote);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (screenshot == null)
            try {
                screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);
            } catch (Exception e) {
                logger.error("Couldn't take screenshot\n" + e.getMessage());
                return new byte[]{};
            }

        return screenshot;
    }

    @SuppressWarnings("unused")
    private void saveImage(BufferedImage part) throws IOException {
        saveImage(part, "" + part.hashCode());
    }

    public void saveImage(BufferedImage part, String name) throws IOException {
        final String EXPORT_DIR_PATH = new File("").getPath() + "/artifacts/";
        ImageIO.write(part, "png", new File(EXPORT_DIR_PATH + "/" + name + ".png"));
    }

    public AtomicInteger getIncrementedScreenShotNumber() {
        if (screenShotNumber != null) {
            screenShotNumber.incrementAndGet();
        }
        return screenShotNumber;
    }

    @SuppressWarnings("unchecked")
    public Cookie getSessionCookie() {
        String js = "return document.cookie.split(';')";
        List<String> cookies = (List<String>) ((JavascriptExecutor) getDriver()).executeScript(js);
        for (String cookie : cookies) {
            cookie = cookie.trim();
            if (cookie.startsWith("SID")) {
                Pattern cookieParser = Pattern.compile("(\\w+)=(\\w+)");
                Matcher matcher = cookieParser.matcher(cookie);
                if (matcher.find())
                    return new Cookie(matcher.group(1), matcher.group(2));
            }
        }
        Assert.assertTrue("No SID cookie found:\n" + cookies,
                cookies.toString().startsWith("SID"));
        return null;
    }

    public PageObject optionallyWaitFor(ExpectedCondition<?> expectedCondition) {
        new WebDriverOptionalWait(getDriver(), waitForTimeoutInSeconds).until(expectedCondition);
        return this;
    }

    public Alert getAlert() {
        return new WebDriverOptionalWait(driver, 10L).until(ExpectedConditions.alertIsPresent());
    }

    public boolean alertIsPresent() {
        return getAlert() != null;
    }
}

