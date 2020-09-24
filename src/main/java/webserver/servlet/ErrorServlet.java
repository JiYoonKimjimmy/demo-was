package webserver.servlet;

import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.response.HttpStatusCode;

import java.awt.print.Pageable;

/**
 * @author Jim, Kim
 * @since 2020-09-24
 */
public class ErrorServlet extends AbstractServlet {
    private final String filePath;
    private final HttpStatusCode httpStatusCode;

    public ErrorServlet(String filePath, HttpStatusCode httpStatusCode) {
        this.filePath = filePath;
        this.httpStatusCode = httpStatusCode;
    }

    @Override
    public void doGet(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception {
        httpResponse.addResponseHeader(CONTENT_TYPE, HTML_CONTENT_TYPE);
        httpResponse.error("/error/" + filePath + ".html", httpStatusCode);
    }
}
