package selenium.hook;

import selenium.driver.DriverManager;
import selenium.helper.ConfigurationHelper;
import org.openqa.selenium.WebDriver;
import selenium.report.ExtentReporter;

public class Hook {
    protected static WebDriver driver;

    protected void hookBefore(){
        driver = new DriverManager().createDriver();
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.get(ConfigurationHelper.getInstance().getConfiguration().getUrl());
    }

    protected void hookAfter(){
        if(driver != null){
            driver.close();
            driver.quit();
        }
    }

    protected static void hookBeforeClass(){
        ExtentReporter.initialize();
    }

    protected static void hookAfterClass(){
        ExtentReporter.flush();
    }
}
