package selenium.helper;

import selenium.browser.BrowserType;

import java.io.*;
import java.util.Properties;

public class Configuration {

    private final Properties properties;

    public Configuration() {
        properties = new Properties();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream in = classloader.getResourceAsStream("web-settings.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getUrl()  {
        String url = properties.getProperty("testUrl");
        if (url != null) return url;
        else
            throw new RuntimeException("testUrl not specified in the config file.");
    }

    public BrowserType getBrowserType()  {
        String browserType = properties.getProperty("browserType");

        switch (browserType) {
            case "chrome":
                return BrowserType.CHROME;
            case "firefox":
                return BrowserType.FIREFOX;
            case "edge":
                return BrowserType.EDGE;
            case "safari":
                return BrowserType.SAFARI;
            default:
                throw new RuntimeException("Browser Type key value in properties file is not matched: " + browserType);
        }
    }

    public boolean isHeadless(){
        return Boolean.parseBoolean(properties.getProperty("headless"));
    }

    public boolean isSecretMode(){
        return Boolean.parseBoolean(properties.getProperty("secretMode"));
    }

    public long getPageTimeOut(){
        return Long.parseLong(properties.getProperty("pageTimeOut"));
    }

    public long getScriptTimeOut(){
        return Long.parseLong(properties.getProperty("scriptTimeOut"));
    }

    public long getImplicitlyTimeOut(){
        return Long.parseLong(properties.getProperty("implicitlyTimeOut"));
    }

}
