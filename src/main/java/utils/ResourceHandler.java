package utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ResourceHandler {

    public static Properties loadGradleResource(String fileName) throws Exception {
        File resourceFile = new File(fileName);
        if (resourceFile.exists()) {
            Properties properties = new Properties();
            FileInputStream props = new FileInputStream(resourceFile);
            properties.load(props);
            return properties;
        } else {
            System.out.println("File doesn't exist in location: " + resourceFile.getAbsolutePath());
        }
        return null;
    }
}
