package util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Jim, Kim
 * @since 2020-09-23
 */
public class PropertyUtils {
    private static Properties properties = new Properties();

    private PropertyUtils() {
        try {
            InputStream in = getClass().getResourceAsStream("/properties/application.properties");
            properties.load(new BufferedInputStream(in));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertyUtils getInstance() {
        return new PropertyUtils();
    }

    public Properties getProperties() {
        return this.properties;
    }

}
