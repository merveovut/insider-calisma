package selenium.pages;

import java.util.List;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.base.StepImpl;
import org.openqa.selenium.WebDriver;
import selenium.helper.MethodCall;
import selenium.report.ExtentReporter;

public class PageHome extends StepImpl {

    public PageHome(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".css-1qljeod")
    private WebElement homeLogo;

    @FindBy(css = "#navbarDropdownMenuLink,.nav-no-dropdown")
    private List<WebElement> topMenuTexts;

    @FindBy(css = ".dropdown-sub")
    private List<WebElement> topMenuSubTexts;

    @FindBy(id = "wt-cli-accept-all-btn")
    private WebElement cookie;

    public PageHome checkUrl(String url){
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("URL: "+url);
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        checkToUrl(url);
        waitPageLoad();
        return this;
    }

    public PageHome goUrl(String url){
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("URL: "+url);
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        goToUrl(url);
        return this;
    }

    public PageHome acceptCookie(){
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("WebElement: cookie");
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        click(cookie);
        return this;
    }

    public PageHome clickTopMenuText(String text){
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("WebElement: topMenuTexts");
        ExtentReporter.setNodeLogInfo("Text: "+text);
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        findElementsCheckTextEqualsAndClick(topMenuTexts, text);
        return this;
    }

    public PageHome checkTopMenuText(String text){
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("WebElement: topMenuTexts");
        ExtentReporter.setNodeLogInfo("Text: "+text);
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        findElementsCheckTextEquals(topMenuTexts, text);
        return this;
    }

    public PageHome hoverTextMenu(String text) {
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("WebElement: topMenuTexts");
        ExtentReporter.setNodeLogInfo("Text: "+text);
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        findElementsHoverTextEquals(topMenuTexts,text);
        return this;
    }

    public PageHome checkTopSubMenu(String text2) {
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("WebElement: topMenuSubTexts");
        ExtentReporter.setNodeLogInfo("Text: "+text2);
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        findElementsCheckTextEquals(topMenuSubTexts, text2);
        return this;
    }

    public PageHome clickTopSubMenu(String text2) {
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("WebElement: topMenuSubTexts");
        ExtentReporter.setNodeLogInfo("Text: "+text2);
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        findElementsCheckTextEqualsAndClick(topMenuSubTexts, text2);
        return this;
    }
}
