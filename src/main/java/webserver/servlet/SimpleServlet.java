package webserver.servlet;

import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

/**
 * @author Jim, Kim
 * @since 2020-09-24
 */
public interface SimpleServlet {
    void service(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception;
}
