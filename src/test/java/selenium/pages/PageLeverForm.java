package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.base.StepImpl;
import org.openqa.selenium.WebDriver;
import selenium.helper.MethodCall;
import selenium.report.ExtentReporter;

public class PageLeverForm extends StepImpl {

    public PageLeverForm(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".posting-headline > h2")
    private WebElement header;

    public PageLeverForm checkHeader(String text){
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("WebElement: header");
        ExtentReporter.setNodeLogInfo("Text: "+text);
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        checkElementText(header, text);
        return this;
    }
}
