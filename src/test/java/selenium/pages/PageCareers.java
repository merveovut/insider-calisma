package selenium.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import selenium.base.StepImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.List;
import selenium.helper.MethodCall;
import selenium.report.ExtentReporter;

public class PageCareers extends StepImpl {

    public PageCareers(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".loadmore")
    private WebElement seeAllTeams;
    @FindBy(css = "#career-find-our-calling h3:not([class='category-title-media'])")
    private List<WebElement> teamNames;
    @FindBy(css = ".location-info > p")
    private List<WebElement> locationNames;
    @FindBy(css = ".elementor-carousel-image")
    private List<WebElement> lifeAtInsiderImages;
    @FindBy(css = "#page-head a")
    private WebElement seeAllJobs;
    @FindBy(id = "select2-filter-by-location-container")
    private WebElement locationDropdown;
    @FindBy(id = "select2-filter-by-department-container")
    private WebElement departmentDropdown;
    @FindBy(css = ".select2-results__option")
    private List<WebElement> filterDropdownNames;
    @FindBy(css = ".position-list-item")
    private List<WebElement> positions;
    @FindBy(css = ".position-list-item .position-department")
    private List<WebElement> positionDepartmentNameList;
    @FindBy(css = ".position-list-item .position-location")
    private List<WebElement> positionLocationNameList;
    @FindBy(css = ".position-title")
    private List<WebElement> positionTitleNameList;
    By positionViewRoleByTitleNameList = By.xpath("following-sibling::a");
    @FindBy(css = ".location-slider-next")
    private WebElement locationNext;

    public PageCareers clickSeeAllTeams(){
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("WebElement: seeAllTeams");
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        waitClickable(seeAllTeams);
        click(seeAllTeams);
        waitPageLoad();
        return this;
    }

    public PageCareers checkTeamNames(String text){
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("WebElement: teamNames");
        ExtentReporter.setNodeLogInfo("Text: "+text);
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        wait(2);
        findElementsCheckTextEquals(teamNames, text);
        return this;
    }

    public PageCareers checkLocationNames(String text) throws InterruptedException {
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("WebElement: locationNames");
        ExtentReporter.setNodeLogInfo("Text: "+text);
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        List<WebElement> elements = locationNames;
        for(WebElement element : elements){
            scroll(element);
            Thread.sleep(200);
            if(element.getText().equals(text)){
                return this;
            }else{
                scroll(locationNext);
                click(locationNext);
            }
        }
        return this;
    }

    public PageCareers checkLifeAtInsider(){
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("WebElement: lifeAtInsiderImages");
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        List<WebElement> elements = lifeAtInsiderImages;
        return this;
    }

    public PageCareers clickSeeAllJobs(){
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("WebElement: seeAllJobs");
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        click(seeAllJobs);
        return this;
    }

    public PageCareers selectCountry(String text){
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("WebElement: filterDropdownNames");
        ExtentReporter.setNodeLogInfo("Text: "+text);
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        waitClickable(locationDropdown);
        click(locationDropdown);
        findElementsCheckTextEqualsAndClick(filterDropdownNames, text);
        return this;
    }

    public PageCareers selectDepartment(String text){
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("WebElement: filterDropdownNames");
        ExtentReporter.setNodeLogInfo("Text: "+text);
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        click(departmentDropdown);
        findElementsCheckTextEqualsAndClick(filterDropdownNames, text);
        return this;
    }

    public PageCareers waitLoadPosition(String text){
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("WebElement: positionDepartmentNameList");
        ExtentReporter.setNodeLogInfo("Text: "+text);
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        getCheckTextEquals(positionDepartmentNameList, text);
        return this;
    }

    public PageCareers checkPositions(){
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("WebElement: positions");
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        checkElement(positions);
        return this;
    }

    public PageCareers checkCareersDepartmentNames(String text){
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("WebElement: positionDepartmentNameList");
        ExtentReporter.setNodeLogInfo("Text: "+text);
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        waitListElements(positionDepartmentNameList);
        List<WebElement> elements = positionDepartmentNameList;
        for(WebElement element : elements){
            if(!(element.getText().equals(text))){
                fail("Department Name Not Equals");
            }
        }
        return this;
    }

    public PageCareers checkCareersLocationNames(String text){
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("WebElement: positionLocationNameList");
        ExtentReporter.setNodeLogInfo("Text: "+text);
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        waitListElements(positionLocationNameList);
        List<WebElement> elements = positionLocationNameList;
        for(WebElement element : elements){
            if(!(element.getText().equals(text))){
                fail("Location Name Not Equals");
            }
        }
        return this;
    }

    public PageCareers clickViewRole(String text) {
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("WebElement: positionTitleNameList");
        ExtentReporter.setNodeLogInfo("Text: "+text);
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        scroll(positionTitleNameList.get(0));
        waitClickable(positionTitleNameList.get(0));
        findElementsHoverTextEquals(positionTitleNameList,text);
        findElementsCheckTextAndClickNearElement(positionTitleNameList,positionViewRoleByTitleNameList,text);
        return this;
    }

    public PageCareers waitPage() {
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        waitPageLoad();
        return this;
    }

    public PageCareers waitLoadDepartmentName(String text) {
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("WebElement: departmentDropdown");
        ExtentReporter.setNodeLogInfo("Text: "+text);
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        checkTextEquals(departmentDropdown, text);
        return this;
    }

    public PageCareers waitLoadTeamNames(String text) {
        ExtentReporter.createNode(MethodCall.getCallingMethodName());
        ExtentReporter.setNodeLogInfo("WebElement: teamNames");
        ExtentReporter.setNodeLogInfo("Text: "+text);
        ExtentReporter.setNodeLogInfo("Class: "+this.getClass().getSimpleName());
        getCheckTextEquals(teamNames, text);
        return this;
    }
}
