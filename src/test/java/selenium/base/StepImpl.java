package selenium.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;
import java.util.Set;

public class StepImpl {

    private static final Log logger = LogFactory.getLog(StepImpl.class);
    private String winHandleBefore;
    private static WebDriver driver;
    private final WebDriverWait wait;
    private final Actions actions;
    private final JavascriptExecutor executor;
    private final int count = 10;
    long WAIT_TIME_OUT = 20;
    public StepImpl(WebDriver _driver){
        driver = _driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIME_OUT));
        actions = new Actions(driver);
        executor = (JavascriptExecutor) driver;
    }

    protected void waitListElements(final List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOf(elements.get(0)));
    }

    protected void goToUrl(String url){
        try{
            driver.get(url);
        }catch (Exception e){
            fail("Urline gidilemedi. Url: "+url);
        }
    }

    protected void checkToUrl(String url){
        String currentUrl = driver.getCurrentUrl();
        if(!currentUrl.equals(url)){
            fail("Url doğrulanamadı. Actual Url: "+driver.getCurrentUrl()+" Expected Url: "+url);
        }
    }

    protected void navigateBack(){
        driver.navigate().back();
    }

    protected void goNewTab(){
        driver.switchTo().newWindow(WindowType.TAB);
    }

    public void navigateWindow() {
        try {
            winHandleBefore = driver.getWindowHandle();
            Set<String> winHandles = driver.getWindowHandles();
            for (String handle : winHandles) {
                if (!handle.equals(winHandleBefore)) {
                    driver.switchTo().window(handle);
                    break;
                }
            }
        } catch (Exception e) {
            fail("Failed to switch window");
        }
    }

    public void navigateBackWindow() {
        try {
            driver.close();
            driver.switchTo().window(winHandleBefore);
        } catch (Exception e) {
            fail("Failed to switch window");
        }
    }

    protected void checkElement(List<WebElement> elements){
        try{
            wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        }catch (Exception e){
            fail("Elementler görünür değil.");
        }
    }

    protected void checkElementText(WebElement element, String text){
        if(!element.getText().equals(text)){
            fail(text + " değerine eşit element bulunamadı");
        }
    }

    protected void click(WebElement element){
        int i = 0;
        try{
            do{
                scroll(element);
                if(element.isEnabled()){
                    waitClickable(element);
                    element.click();
                    return;
                }
                i++;
            }while ( i < count);
        }catch (ElementClickInterceptedException ignored){
        }
    }

    protected void findElementsCheckTextEqualsAndClick(List<WebElement> elements, String text){
        for (WebElement element : elements) {
            if (element.getText().replaceAll("\\s+", "").equals(text.replaceAll("\\s+", ""))) {
                highlight(element);
                scroll(element);
                waitClickable(element);
                element.click();
                return;
            }
        }
        fail(text + " değerine eşit elemente tıklanılamadı.");
    }

    protected void findElementsCheckTextAndClickNearElement(List<WebElement> elements, By nearElementBy, String text){
        WebElement element = getCheckTextEquals(elements, text);
        if(element == null){
            fail("Element bulunamadı.");
        }
        WebElement nearElement = element.findElement(nearElementBy);
        highlight(nearElement);
        scroll(nearElement);
        clickJS(nearElement);
    }

    protected WebElement getCheckTextEquals(List<WebElement> elements, String text){
        try {
            for(int i = 0; i < count; i++) {
                Thread.sleep(500);
                for (WebElement element : elements) {
                    if (element.getText().replaceAll("\\s+", "")
                        .equals(text.replaceAll("\\s+", ""))) {
                        return element;
                    }
                }
            }
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
        return null;
    }

    protected void checkTextEquals(WebElement element, String text){
        try {
            for(int i = 0; i < count; i++) {
                Thread.sleep(500);
                if (element.getText().replaceAll("\\s+", "").equals(text.replaceAll("\\s+", ""))) {
                    return;
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void findElementsCheckTextEquals(List<WebElement> elements, String text){
        for (WebElement element : elements) {
            scroll(element);
            if (element.getText().equals(text)) {
                return;
            }
        }
        fail(text + " değerine eşit element bulunamadı.");
    }

    protected void findElementsHoverTextEquals(List<WebElement> elements, String text) {
        for (WebElement element : elements) {
            scroll(element);
            waitClickable(element);
            if (element.getText().equals(text)) {
                actions.moveToElement(element).perform();
                return;
            }
        }
        fail(text + " değerine eşit element bulunamadı.");
    }

    private void highlight(WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    protected void wait(int second){
        try {
            Thread.sleep(second* 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void waitClickable(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void waitPresentEqualsText(WebElement element, String text){
        wait.until(ExpectedConditions.textToBePresentInElementValue(element, text));
    }

    protected void waitVisibilityAllElements(List<WebElement> elements){
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    private void clickJS(WebElement element){
        executor.executeScript("arguments[0].click();", element);
    }

    protected void scroll(WebElement element) {
        try {
            executor.executeScript(
                    "arguments[0].scrollIntoView({behavior: 'auto', block: 'center', inline: 'center'});",
                    element);
        } catch (Exception e) {
            logger.warn("Scroll Failed ", e);
        }
    }

    protected void scrollDown() {
        try {
            executor.executeScript("window.scrollBy(0, 500)");
        } catch (Exception e) {
            fail("Scroll Down Error");
        }
    }

    protected void waitPageLoad() {
        try {
            wait.until(
                    driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState")
                            .toString()
                            .equals("complete"));
        } catch (Throwable error) {
            fail("Page Wait Exception " + error);
        }
    }

    protected void fail(String message){
        Assert.fail(message);
    }
}
