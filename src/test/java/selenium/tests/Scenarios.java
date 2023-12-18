package selenium.tests;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import selenium.helper.ConfigurationHelper;
import selenium.hook.Hook;
import selenium.listener.Watcher;
import selenium.pages.PageCareers;
import selenium.pages.PageHome;
import selenium.pages.PageLeverForm;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Scenarios extends Hook {

  @Rule
  public Watcher watcher = new Watcher();
  PageHome pageHome;
  PageCareers pageCareers;
  PageLeverForm pageLeverForm;

  @BeforeClass
  public static void beforeClass() {
    hookBeforeClass();
  }

  @AfterClass
  public static void afterClass() {
    hookAfterClass();
  }

  @Before
  public void before() throws IOException {
    hookBefore();
    pageHome = new PageHome(driver);
    pageCareers = new PageCareers(driver);
    pageLeverForm = new PageLeverForm(driver);
    FileUtils.deleteDirectory(new File(System.getProperty("user.dir") + "/screenshot"));
  }

  @After
  public void after() {
    hookAfter();
  }

  @Test
  public void T01_checkPage() {
    pageHome.checkUrl(ConfigurationHelper.getInstance().getConfiguration().getUrl());
  }

  @Test
  public void T02_careersPageControl() throws InterruptedException {
    pageHome.acceptCookie();
    pageHome
        .clickTopMenuText("Company")
        .clickTopSubMenu("Careers");
    pageHome.checkUrl("https://useinsider.com/careers/");
    pageCareers
        .clickSeeAllTeams()
        .waitPage()
        .waitLoadTeamNames("Finance & Business Support");
    pageCareers
        .checkTeamNames("Finance & Business Support")
        .checkTeamNames("Business Intelligence")
        .checkTeamNames("Partnership")
        .checkTeamNames("Product Design");
    pageCareers
        .checkLocationNames("New York")
        .checkLocationNames("Paris")
        .checkLocationNames("Helsinki")
        .checkLocationNames("Dubai")
        .checkLocationNames("Singapore");
    pageCareers.checkLifeAtInsider();
  }

  @Test
  public void T03_careersCheckPositions() {
    pageHome.acceptCookie();
    pageHome.goUrl("https://useinsider.com/careers/quality-assurance/");
    pageCareers
        .clickSeeAllJobs()
        .waitPage()
        .waitLoadDepartmentName("Quality Assurance")
        .selectCountry("Istanbul, Turkey")
        .selectDepartment("Quality Assurance")
        .waitLoadPosition("Quality Assurance")
        .checkPositions();
  }

  @Test
  public void T04_careersCheckPositionAndDepartmentNames() throws InterruptedException {
    pageHome.acceptCookie();
    pageHome.goUrl("https://useinsider.com/careers/quality-assurance/");
    pageCareers
        .clickSeeAllJobs()
        .waitPage()
        .waitLoadDepartmentName("Quality Assurance");
    pageCareers
        .selectCountry("Istanbul, Turkey")
        .selectDepartment("Quality Assurance")
        .waitLoadPosition("Quality Assurance")
        .checkPositions()
        .checkCareersDepartmentNames("Quality Assurance")
        .checkCareersLocationNames("Istanbul, Turkey");
  }

  @Test
  public void T05_careersCheckPositionViewRoleButon() {
    pageHome.acceptCookie();
    pageHome.goUrl("https://useinsider.com/careers/quality-assurance/");
    pageCareers
        .waitPage()
        .clickSeeAllJobs()
        .waitPage()
        .waitLoadDepartmentName("Quality Assurance")
        .selectCountry("Istanbul, Turkey")
        .selectDepartment("Quality Assurance")
        .waitLoadPosition("Quality Assurance")
        .checkPositions();
    pageCareers
        .clickViewRole("Senior Software Quality Assurance Engineer");
    pageLeverForm
        .navigateWindow();
    pageLeverForm
        .checkHeader("Senior Software Quality Assurance Engineer")
        .navigateBackWindow();
  }
}
