package webserver.servlet;

import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

/**
 * @author Jim, Kim
 * @since 2020-09-24
 */
public class IconServlet extends AbstractServlet {
    @Override
    public void doGet(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception {
        httpResponse.addResponseHeader(CONTENT_TYPE, ICON_CONTENT_TYPE);
        httpResponse.ok(httpRequest.getPath());
    }
}
