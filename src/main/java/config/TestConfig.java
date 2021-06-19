package config;

import utils.ResourceHandler;

import java.util.Properties;

public class TestConfig {
    private static Properties properties=null;

    private static String getConfigFor(String configItem) throws Exception {
        if(properties==null){
            String fileName = System.getProperty("PROPERTY_FILE");
            System.out.println("filename is " + fileName);
            properties = ResourceHandler.loadGradleResource(fileName);
        }
        return System.getProperty(configItem) != null ?
                System.getProperty(configItem) : properties.getProperty(configItem);
    }

    public static String getResource(String input) throws Exception {
        return getConfigFor(input);
    }
}
