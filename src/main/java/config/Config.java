package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Config {

    private static final File CREDENTIALS_CONFIG = new File("src/main/resources/hostname.properties");

    private static Config _config = null;
    private static String adminName;
    private static String adminPass;
    private static String hostname;

    private Config() {
        initConfig();
    }

    public static Config get() {
        if (_config == null) _config = new Config();
        return _config;
    }

    private static void initConfig() {
        try {
            adminName = getProperties("admin.name");
            adminPass = getProperties("admin.pass");
            hostname = getProperties("hostname");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getProperties(String path) throws IOException {
        Properties properties = getFileProperties();
        return properties.getProperty(path);
    }

    private static Properties getFileProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(CREDENTIALS_CONFIG));
        return properties;
    }

    public String getAdminName() {
        return adminName;
    }

    public String getAdminPass() {
        return adminPass;
    }

    public String getHostName() {
        return hostname;
    }
}
