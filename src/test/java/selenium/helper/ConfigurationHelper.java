package selenium.helper;

public class ConfigurationHelper {

    private static final ConfigurationHelper configurationHelper = new ConfigurationHelper();
    private static Configuration configuration;

    public static ConfigurationHelper getInstance() {
        return configurationHelper;
    }

    public Configuration getConfiguration() {
        return (configuration == null) ? new Configuration() : configuration;
    }
}
