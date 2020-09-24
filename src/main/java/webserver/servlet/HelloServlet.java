package webserver.servlet;

import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

/**
 * @author Jim, Kim
 * @since 2020-09-24
 */
public class HelloServlet extends AbstractServlet {
    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception {
        httpResponse.addResponseHeader(CONTENT_TYPE, HTML_CONTENT_TYPE);
        httpResponse.ok(httpRequest.getPath() + ".html");
    }
}
