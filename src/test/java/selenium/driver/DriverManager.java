package selenium.driver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.ie.InternetExplorerDriver;
import selenium.browser.BrowserOptions;
import selenium.browser.BrowserType;
import selenium.helper.ConfigurationHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import selenium.report.ExtentReporter;

public class DriverManager {
    private static final Log logger = LogFactory.getLog(ExtentReporter.class);
    private WebDriver webDriver;

    private WebDriver createLocalDriver() {
        BrowserType browserType = ConfigurationHelper.getInstance().getConfiguration().getBrowserType();
        BrowserOptions browserOptions = new BrowserOptions();
        switch (browserType) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver(browserOptions.chromeOptions());
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver(browserOptions.firefoxOptions());
                break;
            case EDGE:
                WebDriverManager.edgedriver().setup();
                webDriver = new EdgeDriver(browserOptions.edgeOptions());
                break;
            case SAFARI:
                WebDriverManager.safaridriver().setup();
                webDriver = new SafariDriver(browserOptions.safariOptions());
            case IE:
                WebDriverManager.iedriver().setup();
                webDriver = new InternetExplorerDriver(browserOptions.ieOptions());
                break;
        }

        long implicitlyTimeOut = ConfigurationHelper.getInstance().getConfiguration().getImplicitlyTimeOut();
        long pageTimeOut = ConfigurationHelper.getInstance().getConfiguration().getPageTimeOut();
        long scriptTimeOut = ConfigurationHelper.getInstance().getConfiguration().getScriptTimeOut();

        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyTimeOut));
        webDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(pageTimeOut));
        webDriver.manage().timeouts().scriptTimeout(Duration.ofMillis(scriptTimeOut));
        return webDriver;
    }

    public WebDriver createDriver() {
        if (webDriver == null){
            webDriver = createLocalDriver();
            logger.info("Creating new WebDriver");
        }
        return webDriver;
    }

}
