package webserver;

import exception.InternalServerException;
import util.PropertyUtils;
import webserver.response.HttpResponse;
import webserver.response.HttpStatusCode;
import webserver.servlet.ErrorServlet;
import webserver.servlet.SimpleServlet;

import java.util.Properties;
import java.util.logging.Logger;

/**
 * @author Jim, Kim
 * @since 2020-09-24
 */
public class HandlerMapping {
    private static final Logger logger = Logger.getLogger(HttpResponse.class.getCanonicalName());

    private static final String SERVLET_PREFIX = "servlet.";

    static SimpleServlet findServlet(String url) {
        Properties properties = PropertyUtils.getInstance().getProperties();

        if (url.startsWith("/")) {
            url = url.substring(1);
        }

        try {
            if (properties.containsKey(SERVLET_PREFIX + url)) {
                String cn = properties.getProperty(SERVLET_PREFIX + url);
                Class c = Class.forName(cn);
                return (SimpleServlet) c.getConstructor().newInstance();
            } else {
                throw new ClassNotFoundException();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ErrorServlet("404", HttpStatusCode.NOT_FOUND);
        }

    }

}
