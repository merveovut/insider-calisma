package selenium.browser;

import org.openqa.selenium.ie.InternetExplorerOptions;
import selenium.helper.ConfigurationHelper;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariOptions;

public class BrowserOptions {

    public SafariOptions safariOptions() {
        SafariOptions options = new SafariOptions();
        return options;
    }

    public InternetExplorerOptions ieOptions() {
        InternetExplorerOptions options = new InternetExplorerOptions();
        return options;
    }

    public ChromeOptions chromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--test-type");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-translate");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        if(ConfigurationHelper.getInstance().getConfiguration().isHeadless()){
            options.addArguments("--headless", "--window-size=1920,1080");
        }
        if(ConfigurationHelper.getInstance().getConfiguration().isSecretMode()){
            options.addArguments("--incognito");
        }
        return options;
    }

    public FirefoxOptions firefoxOptions(){
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--test-type");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-translate");
        options.addArguments("--disable-notifications");
        if (ConfigurationHelper.getInstance().getConfiguration().isHeadless()) {
            options.addArguments("-headless", "--window-size=1920,1080");
        }
        if(ConfigurationHelper.getInstance().getConfiguration().isSecretMode()){
            options.addArguments("-private");
        }
        return options;
    }

    public EdgeOptions edgeOptions(){
        EdgeOptions options = new EdgeOptions();
        if (ConfigurationHelper.getInstance().getConfiguration().isHeadless()) {
            options.addArguments("--headless", "--window-size=1920,1080");
        }
        if(ConfigurationHelper.getInstance().getConfiguration().isSecretMode()){
            options.addArguments("-inprivate");
        }
        return options;
    }
}
