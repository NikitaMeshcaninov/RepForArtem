package base;


import utils.Timeout;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("unused")
public class CustomExpectedConditions {
    private static final Logger LOGGER = Logger.getLogger(CustomExpectedConditions.class);

    public static ExpectedCondition<Boolean> attributeIs(final By locator, final String attribute,
                                                         final String value) {
        return new ExpectedCondition<Boolean>() {
            private String currentValue = null;

            @Override
            public Boolean apply(WebDriver driver) {
                currentValue = driver.findElement(locator).getAttribute(attribute);
                if (currentValue == null) {
                    currentValue = driver.findElement(locator).getCssValue(attribute);
                }
                return value.equals(currentValue);
            }

            @Override
            public String toString() {
                return String
                        .format("value to be \"%s\". Current value: \"%s\"", value, currentValue);
            }
        };
    }

    public static ExpectedCondition<Boolean> textIs(final By locator, final String value) {
        return new ExpectedCondition<Boolean>() {
            private String currentValue = null;

            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    currentValue = driver.findElement(locator).getText();
                    return currentValue.equals(value);
                } catch (Exception e) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return String
                        .format("text to be \"%s\". Current text: \"%s\"", value, currentValue);
            }
        };
    }

    public static ExpectedCondition<Boolean> textContains(final By locator, final String value) {
        return new ExpectedCondition<Boolean>() {
            private String currentValue = null;

            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    currentValue = driver.findElement(locator).getText();
                    return currentValue.contains(value);
                } catch (Exception e) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return String
                        .format("text to be \"%s\". Current text: \"%s\"", value, currentValue);
            }
        };
    }

    public static ExpectedCondition<Boolean> numberOfElementsMoreThan(final By locator,
                                                                      final Integer number) {
        return new ExpectedCondition<Boolean>() {
            private Integer currentNumber = 0;

            @Override
            public Boolean apply(WebDriver webDriver) {
                currentNumber = webDriver.findElements(locator).size();
                return currentNumber > number;
            }

            @Override
            public String toString() {
                return String
                        .format("number to be more than \"%s\". Current number: \"%s\"", number,
                                currentNumber);
            }
        };
    }

    public static ExpectedCondition<Boolean> numberOfElementsMoreThan(
            final List<WebElementFacade> facade,
            final Integer number) {
        return new ExpectedCondition<Boolean>() {
            private Integer currentNumber = 0;

            @Override
            public Boolean apply(WebDriver webDriver) {
                currentNumber = facade.size();
                return currentNumber > number;
            }

            @Override
            public String toString() {
                return String
                        .format("number to be more than \"%s\". Current number: \"%s\"", number,
                                currentNumber);
            }
        };
    }

    public static ExpectedCondition<Boolean> numberOfOptionsMoreThan(final CustomSelect element,
                                                                     final Integer number) {
        return new ExpectedCondition<Boolean>() {
            private Integer currentNumber = 0;

            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    currentNumber = element.getOptions().size();
                    return currentNumber > number;
                } catch (StaleElementReferenceException e) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return String
                        .format(
                                "number of available options to be more than \"%s\". Current number: \"%s\"",
                                number,
                                currentNumber);
            }
        };
    }

    public static ExpectedCondition<Boolean> numberOfElementsLessThan(final By locator,
                                                                      final Integer number) {
        return new ExpectedCondition<Boolean>() {
            private Integer currentNumber = 0;

            @Override
            public Boolean apply(WebDriver webDriver) {
                currentNumber = webDriver.findElements(locator).size();
                return currentNumber < number;
            }

            @Override
            public String toString() {
                return String
                        .format("number to be less than \"%s\". Current number: \"%s\"", number,
                                currentNumber);
            }
        };
    }

    public static ExpectedCondition<Boolean> numberOfElements(final By locator,
                                                              final Integer number) {
        return new ExpectedCondition<Boolean>() {
            private Integer currentNumber = 0;

            @Override
            public Boolean apply(WebDriver webDriver) {
                currentNumber = webDriver.findElements(locator).size();
                return currentNumber.equals(number);
            }

            @Override
            public String toString() {
                return String
                        .format("number to be \"%s\". Current number: \"%s\"", number, currentNumber);
            }
        };
    }

    public static ExpectedCondition<Boolean> attributeIs(final WebElement element,
                                                         final String attribute, final String value) {
        return new ExpectedCondition<Boolean>() {
            private String currentValue = null;

            @Override
            public Boolean apply(WebDriver driver) {
                currentValue = element.getAttribute(attribute);
                if (currentValue == null) {
                    currentValue = element.getCssValue(attribute);
                }
                return value.equals(currentValue);
            }

            @Override
            public String toString() {
                return String
                        .format(attribute + " to be \"%s\". Current " + attribute + ": \"%s\"", value,
                                currentValue);
            }
        };
    }

    public static ExpectedCondition<Boolean> attributeContains(final WebElement element,
                                                               final String attribute, final String value) {
        return new ExpectedCondition<Boolean>() {
            private String currentValue = null;

            @Override
            public Boolean apply(WebDriver driver) {
                Boolean contains = false;
                try {
                    currentValue = element.getAttribute(attribute);
                    if (currentValue == null) {
                        currentValue = element.getCssValue(attribute);
                    }
                    contains = currentValue.contains(value);
                } catch (Exception e) {/**/}
                return contains;
            }

            @Override
            public String toString() {
                return String
                        .format("value to contain \"%s\". Current value: \"%s\"", value, currentValue);
            }
        };
    }

    public static ExpectedCondition<Boolean> attributeNOTContains(final WebElement element,
                                                                  final String attribute, final String value) {
        return new ExpectedCondition<Boolean>() {
            private String currentValue = null;

            @Override
            public Boolean apply(WebDriver driver) {
                Boolean contains = false;
                try {
                    currentValue = element.getAttribute(attribute);
                    if (currentValue == null) {
                        currentValue = element.getCssValue(attribute);
                    }
                    contains = !currentValue.contains(value);
                } catch (Exception e) {/**/}
                return contains;
            }

            @Override
            public String toString() {
                return String
                        .format("value to contain \"%s\". Current value: \"%s\"", value, currentValue);
            }
        };
    }

    public static ExpectedCondition<Boolean> attributeContains(final By locator,
                                                               final String attribute, final String value) {
        return new ExpectedCondition<Boolean>() {
            private String currentValue = null;

            @Override
            public Boolean apply(WebDriver driver) {
                Boolean contains = false;
                try {
                    currentValue = driver.findElement(locator).getAttribute(attribute);
                    if (currentValue == null) {
                        currentValue = driver.findElement(locator).getCssValue(attribute);
                    }
                    contains = currentValue.contains(value);
                } catch (Exception e) {/**/}
                return contains;
            }

            @Override
            public String toString() {
                return String
                        .format("value to contain \"%s\". Current value: \"%s\"", value, currentValue);
            }
        };
    }

    public static ExpectedCondition<Boolean> attributeNotEmpty(final WebElement element,
                                                               final String attribute) {
        return new ExpectedCondition<Boolean>() {
            private String currentValue = null;

            @Override
            public Boolean apply(WebDriver driver) {
                Boolean hasText = false;
                try {
                    currentValue = element.getAttribute(attribute);
                    if (currentValue == null) {
                        currentValue = element.getCssValue(attribute);
                    }
                    hasText = !currentValue.isEmpty();
                } catch (Exception e) {/**/}
                return hasText;
            }
        };
    }

    public static ExpectedCondition<Object> ajax() {
        return driver -> (Boolean) ((JavascriptExecutor) driver)
                .executeScript("return jQuery.active==0") ? true : null;
    }

    /**
     * This method checks pending requests on PMT tool
     */
    public static ExpectedCondition<Boolean> angularHttp() {
        return driver -> (Boolean) ((JavascriptExecutor) driver)
                .executeScript("var injector = window.angular.element('body').injector();\n" +
                        "    var $http = injector.get('$http');\n" +
                        "    return $http.pendingRequests.length == 0;") ? true : null;
    }

    public static ExpectedCondition<Boolean> invisibilityOfElementLocatedBy(final By locator) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver webDriver) {
                Boolean visible = false;
                try {
                    visible = webDriver.findElement(locator).isDisplayed();
                } catch (Exception e) {/**/}
                return !visible;
            }

            @Override
            public String toString() {
                return "invisibility of element located by " + locator;
            }
        };
    }

    public static ExpectedCondition<Boolean> visibilityOfElementLocatedBy(final By locator) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver webDriver) {
                Boolean visible = false;
                try {
                    visible = webDriver.findElement(locator).isDisplayed();
                } catch (Exception e) {/**/}
                return visible;
            }

            @Override
            public String toString() {
                return "visibility of element located by " + locator;
            }
        };
    }

    public static ExpectedCondition<WebElement> visibilityOfSubElementLocatedBy(final By locator,
                                                                                final By sub_locator) {
        return new ExpectedCondition<WebElement>() {

            @Override
            public WebElement apply(WebDriver webDriver) {
                Boolean displayed = false;
                Boolean exists = false;
                try {
                    exists = webDriver.findElement(locator).findElements(sub_locator).size() > 0;
                    displayed =
                            webDriver.findElement(locator).findElement(sub_locator).isDisplayed();
                } catch (Exception e) {/**/}
                return (exists && displayed) ?
                        webDriver.findElement(locator).findElement(sub_locator) :
                        null;
            }

            @Override
            public String toString() {
                return "visibility of element located by " + locator + sub_locator;
            }
        };
    }

    public static ExpectedCondition<WebElement> visibilityOfSubElementLocatedBy(
            final WebElement element, final By sub_locator) {
        return new ExpectedCondition<WebElement>() {

            @Override
            public WebElement apply(WebDriver webDriver) {
                Boolean displayed = false;
                Boolean exists = false;
                try {
                    exists = element.findElements(sub_locator).size() > 0;
                    displayed = element.findElement(sub_locator).isDisplayed();
                } catch (Exception e) {/**/}
                return (exists && displayed) ? element.findElement(sub_locator) : null;
            }

            @Override
            public String toString() {
                return "visibility of element located by " + element + sub_locator;
            }
        };
    }

    public static ExpectedCondition<WebElement> presenceOfSubElementLocatedBy(final By locator,
                                                                              final By sub_locator) {
        return new ExpectedCondition<WebElement>() {

            @Override
            public WebElement apply(WebDriver webDriver) {
                WebElement element = null;
                try {
                    element = webDriver.findElement(locator).findElement(sub_locator);
                } catch (Exception e) {/**/}
                return element;
            }

            @Override
            public String toString() {
                return "visibility of element located by " + locator + sub_locator;
            }
        };
    }

    public static ExpectedCondition<WebElement> presenceOfSubElementLocatedBy(
            final WebElement element, final By sub_locator) {
        return new ExpectedCondition<WebElement>() {

            @Override
            public WebElement apply(WebDriver webDriver) {
                WebElement s_element = null;
                try {
                    s_element = element.findElement(sub_locator);
                } catch (Exception e) {/**/}
                return s_element;
            }

            @Override
            public String toString() {
                return "visibility of element located by " + sub_locator;
            }
        };
    }

    public static ExpectedCondition<List<WebElement>> presenceOfSubElementsLocatedBy(
            final By locator, final By sub_locator) {
        return new ExpectedCondition<List<WebElement>>() {

            @Override
            public List<WebElement> apply(WebDriver webDriver) {
                List<WebElement> elements = null;
                try {
                    elements = webDriver.findElement(locator).findElements(sub_locator);
                } catch (Exception e) {/**/}
                return elements;
            }

            @Override
            public String toString() {
                return "visibility of element located by " + locator + sub_locator;
            }
        };
    }

    public static ExpectedCondition<Boolean> invisibilityOfElement(final WebElement element) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver webDriver) {
                Boolean visible = false;
                try {
                    webDriver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
                    visible = element.isDisplayed();
                } catch (Exception e) {/**/} finally {
                    webDriver.manage().timeouts()
                            .implicitlyWait(Timeout.DEFAULT_IMPLICITLY_WAIT, TimeUnit.SECONDS);
                }
                return !visible;
            }

            @Override
            public String toString() {
                return "invisibility of element " + element;
            }
        };
    }

    public static ExpectedCondition<Boolean> invisibilityOfElement(final WebElementFacade element) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver webDriver) {
                Boolean visible = false;
                try {
                    webDriver.manage().timeouts().implicitlyWait(1L, TimeUnit.SECONDS);
                    visible = element.isDisplayed();
                } catch (Exception e) {/**/} finally {
                    webDriver.manage().timeouts()
                            .implicitlyWait(Timeout.DEFAULT_IMPLICITLY_WAIT, TimeUnit.SECONDS);
                }
                return !visible;
            }

            @Override
            public String toString() {
                return "invisibility of element " + element;
            }
        };
    }

    public static ExpectedCondition<Boolean> invisibilityOfAllElements(
            final List<WebElement> elements) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver webDriver) {
                for (WebElement element : elements) {
                    try {
                        if (element.isDisplayed()) {
                            return false;
                        }
                    } catch (Exception e) {/**/}
                }
                return true;
            }

            @Override
            public String toString() {
                return "invisibility of all elements " + elements;
            }
        };
    }

    public static ExpectedCondition<Boolean> visibilityOfElement(final WebElement element) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver webDriver) {
                Boolean visible = false;
                try {
                    try {
                        ((JavascriptExecutor) webDriver)
                                .executeScript("arguments[0].scrollIntoView();", element);
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                    webDriver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
                    visible = element.isDisplayed();
                } catch (Exception e) {/**/} finally {
                    webDriver.manage().timeouts()
                            .implicitlyWait(Timeout.DEFAULT_IMPLICITLY_WAIT, TimeUnit.SECONDS);
                }
                return visible;
            }

            @Override
            public String toString() {
                return "visibility of element " + element;
            }
        };
    }

    public static ExpectedCondition<Boolean> visibilityOfElement(final WebElementFacade element) {
        return new ExpectedCondition<Boolean>() {
            String elementDescription = null;

            @Override
            public Boolean apply(WebDriver webDriver) {
                Boolean visible = false;
                try {
                    try {
                        ((JavascriptExecutor) webDriver)
                                .executeScript("arguments[0].scrollIntoView();", element);
                    } catch (Throwable e) {/**/ }
                    webDriver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
                    visible = element.isCurrentlyVisible();
                } catch (Exception e) {/**/} finally {
                    WebElementFacade el = element;
                    elementDescription = el == null ? "" : el.toString();
                    webDriver.manage().timeouts()
                            .implicitlyWait(Timeout.DEFAULT_IMPLICITLY_WAIT, TimeUnit.SECONDS);
                }
                return visible;
            }

            @Override
            public String toString() {
                return "visibility of element " + elementDescription;
            }
        };
    }

    public static ExpectedCondition<Boolean> nonBlockingVisibilityOfElement(final WebElementFacade element, final long waitForSeconds) {
        return input -> {
            long waitFor = waitForSeconds;
            while (waitFor > 0) {
                if (element.isCurrentlyVisible()) {
                    return true;
                }
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                waitFor--;
            }
            return true;
        };
    }

    public static ExpectedCondition<Boolean> anyTextPresence(final By locator) {
        return webDriver -> {
            String message = null;
            try {
                message = webDriver.findElement(locator).getText();
            } catch (Throwable e) {/**/}
            return !StringUtils.isEmpty(message);
        };
    }

    public static ExpectedCondition<Boolean> anyTextPresence(WebElementFacade webElementFacade) {
        return webDriver -> {
            String message = webElementFacade.getTextValue();
            return !message.isEmpty();
        };
    }

    public static ExpectedCondition<Boolean> anyTextAbsence(final By locator) {
        return webDriver -> {
            String message = webDriver.findElement(locator).getText();
            return message.isEmpty();
        };
    }

    public static ExpectedCondition<Object> newWindow(final Set<String> oldHandlersSet) {
        return new ExpectedCondition<Object>() {

            @Override
            public Object apply(WebDriver driver) {
                Set<String> newWindowsSet = driver.getWindowHandles();
                newWindowsSet.removeAll(oldHandlersSet);
                String handler = newWindowsSet.size() > 0 ?
                        newWindowsSet.iterator().next() : null;
                if (handler != null) {
                    driver.switchTo().window(handler);
                }
                return handler;
            }

            @Override
            public String toString() {
                return "New window has not been opened";
            }
        };
    }

    public static ExpectedCondition<Boolean> fileExists(final String fileName) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver webDriver) {
                return new File(fileName).exists();
            }

            @Override
            public String toString() {
                return "file " + fileName + " presence";
            }
        };
    }

    public static ExpectedCondition<Boolean> elementBeenEnabled(final WebElement element) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    return element.isEnabled();
                } catch (Exception e) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return "element " + element + " to be enabled";
            }
        };
    }

    public static ExpectedCondition<Boolean> elementBeenEnabled(final WebElementFacade element) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver webDriver) {
                try {
                    return element.isEnabled();
                } catch (Exception e) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return "element " + element + " to be enabled";
            }
        };
    }

    public static ExpectedCondition<Boolean> stalenessOfElement(final By locator) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver webDriver) {
                return webDriver.findElements(locator).size() == 0;
            }

            @Override
            public String toString() {
                return "staleness of element " + locator;
            }
        };
    }

    public static ExpectedCondition<String> newFilePresence(final List<String> files,
                                                            final String directoryPath, final FilenameFilter filter) {
        return new ExpectedCondition<String>() {

            @Override
            public String apply(WebDriver driver) {
                List<String> filesAfterDownloading = new ArrayList<>();
                String[] files_ = new File(directoryPath).list(filter);
                if (files_ != null) {
                    filesAfterDownloading.addAll(Arrays.asList(files_));
                }
                if (files != null) {
                    filesAfterDownloading.removeAll(files);
                }
                if (filesAfterDownloading.size() > 0) {
                    return filesAfterDownloading.get(0);
                } else {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "new file presence in the folder " + directoryPath;
            }
        };
    }

    public static ExpectedCondition<Boolean> elementToBeClickable(final WebElement element) {
        return new ExpectedCondition<Boolean>() {

            public ExpectedCondition<WebElement> visibilityOfElementLocated =
                    ExpectedConditions.visibilityOf(element);

            @Override
            public Boolean apply(WebDriver driver) {
                WebElement element = visibilityOfElementLocated.apply(driver);
                try {
                    return (element != null && element.isEnabled());
                } catch (StaleElementReferenceException e) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "element to be clickable: " + element.toString();
            }
        };
    }

    public static ExpectedCondition<Boolean> urlContains(final String text) {
        return new ExpectedCondition<Boolean>() {
            String current = "";

            @Override
            public Boolean apply(WebDriver driver) {
                current = driver.getCurrentUrl();
                return current.contains(text);
            }

            @Override
            public String toString() {
                return "location contains " + text + ", current location is " + current;
            }
        };
    }

    public static ExpectedCondition<Boolean> urlNotContains(final String text) {
        return new ExpectedCondition<Boolean>() {
            String current = "";

            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    current = driver.getCurrentUrl();
                    return !current.contains(text);
                } catch (Exception ex) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return "location not contains " + text + ", current location is " + current;
            }
        };
    }

    public static ExpectedCondition<Boolean> instantorPassed() {
        return new ExpectedCondition<Boolean>() {
            String current = "";

            @Override
            public Boolean apply(WebDriver driver) {
                current = driver.getCurrentUrl();
                return !current.contains("/preconfirm");
            }

            @Override
            public String toString() {
                return "User was redirected from instantor page back to preconfirm page: " + current + " instead of step6";
            }
        };
    }


    public static ExpectedCondition<Boolean> urlEndsWith(final String text) {
        return new ExpectedCondition<Boolean>() {
            String current = "";

            @Override
            public Boolean apply(WebDriver driver) {
                current = driver.getCurrentUrl();
                return current.endsWith(text);
            }

            @Override
            public String toString() {
                return "location ends with " + text + ", current location is " + current;
            }
        };
    }

    public static ExpectedCondition<String> textStalenessInElement(final By element,
                                                                   final String text) {
        return new ExpectedCondition<String>() {

            @Override
            public String apply(WebDriver driver) {
                String e_text;
                try {
                    e_text = driver.findElement(element).getText();
                } catch (StaleElementReferenceException e) {
                    e_text = null;
                }
                return (e_text != null && !e_text.contains(text)) ? text : null;
            }

            @Override
            public String toString() {
                return "text not contains " + text;
            }
        };
    }

    public static ExpectedCondition<Boolean> locationIs(final String currentLocation) {
        return webDriver -> webDriver.getCurrentUrl().equals(currentLocation);
    }

    public static ExpectedCondition<Boolean> stalenessOfSubElement(final By locator,
                                                                   final By sub_locator) {
        return new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(WebDriver webDriver) {
                Boolean present;
                try {
                    webDriver.findElement(locator).findElement(sub_locator);
                    present = true;
                } catch (Exception e) {
                    present = false;
                }
                return !present;
            }

            @Override
            public String toString() {
                return "staleness of element located by " + locator + sub_locator;
            }
        };
    }

    public static ExpectedCondition<Boolean> or(final ExpectedCondition<?>... conditions) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                for (ExpectedCondition<?> condition : conditions) {
                    try {
                        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                        Object result = condition.apply(driver);
                        if (result != null) {
                            if (result instanceof Boolean) {
                                if ((Boolean) result) {
                                    return Boolean.TRUE;
                                }
                            } else {
                                return Boolean.TRUE;
                            }
                        }
                    } catch (Exception e) {/**/} finally {
                        driver.manage().timeouts()
                                .implicitlyWait(Timeout.DEFAULT_IMPLICITLY_WAIT, TimeUnit.SECONDS);
                    }
                }
                return Boolean.FALSE;
            }

            @Override
            public String toString() {
                String message = "at least one condition to be valid: ";
                for (ExpectedCondition<?> condition : conditions) {
                    message += condition.toString();
                }
                return message;
            }
        };
    }

    public static ExpectedCondition<Boolean> and(final ExpectedCondition<?>... conditions) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                for (ExpectedCondition<?> condition : conditions) {
                    try {
                        Object result = condition.apply(driver);
                        if (result == null) {
                            return Boolean.FALSE;
                        } else if (result instanceof Boolean) {
                            if (!(Boolean) result) {
                                return Boolean.FALSE;
                            }
                        }
                    } catch (Exception e) {
                        return Boolean.FALSE;
                    }
                }
                return Boolean.TRUE;
            }

            @Override
            public String toString() {
                String message = "all conditions to be valid: ";
                for (ExpectedCondition<?> condition : conditions) {
                    message += condition.toString();
                }
                return message;
            }
        };
    }

    public static ExpectedCondition<Boolean> alertIsPresent() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    driver.switchTo().alert();
                    return true;
                } catch (NoAlertPresentException e) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return "alert to be present";
            }
        };
    }

    public static ExpectedCondition<Boolean> jsReturnsNoErrors(
            final String javaScript) {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    ((JavascriptExecutor) driver).executeScript(javaScript);
                    return Boolean.TRUE;
                } catch (Exception e) {
                    return Boolean.FALSE;
                }
            }

            @Override
            public String toString() {
                return String.format("js %s to be executable", javaScript);
            }
        };
    }

    public static ExpectedCondition<String> jsReturnsValue(
            final String javaScript) {
        return new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver driver) {
                String value = null;
                try {
                    value = (String) ((JavascriptExecutor) driver).executeScript(javaScript);
                } catch (Exception e) {/**/}
                return StringUtils.isEmpty(value) ? null : value;
            }

            @Override
            public String toString() {
                return String.format("js %s to be executable", javaScript);
            }
        };
    }

    public static ExpectedCondition<WebDriver> frameToBeAvailableAndSwitchToIt(final WebElement frameLocator) {
        return new ExpectedCondition<WebDriver>() {
            @Override
            public WebDriver apply(WebDriver driver) {
                try {
                    return driver.switchTo().frame(frameLocator);
                } catch (NoSuchFrameException var3) {
                    return null;
                }
            }

            @Override
            public String toString() {
                return "frame to be available: " + frameLocator;
            }
        };
    }

    public static ExpectedCondition<Boolean> isAdditionalWindowOpened() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return driver.getWindowHandles().size() > 1;
                } catch (Exception ex) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return "Number of windows returned";
            }
        };
    }

    public static ExpectedCondition<Boolean> allModalWindowsClosed() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return !(driver.getWindowHandles().size() > 1);
                } catch (Exception ex) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return "Number of windows returned";
            }
        };
    }


    public static ExpectedCondition<Boolean> isSmsForMonedoNowExists() {
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return Boolean.valueOf(((JavascriptExecutor) driver).executeScript("return localStorage['flex.activationSmsSent']").toString());
                } catch (Exception ex) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return "There is no sms in the local storage for MonedoNow client";
            }
        };
    }

    public static ExpectedCondition<Boolean> angularHasFinishedProcessing() {
        return driver -> Boolean.valueOf(((JavascriptExecutor) driver).
                executeScript("return (window.angular !== undefined) && " +
                        "(angular.element(document).injector() !== undefined) && " +
                        "(angular.element(document).injector().get('$http').pendingRequests.length === 0)").toString());
    }
}
