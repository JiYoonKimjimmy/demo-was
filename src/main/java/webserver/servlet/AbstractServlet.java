package webserver.servlet;

import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

/**
 * @author Jim, Kim
 * @since 2020-09-24
 */
public abstract class AbstractServlet implements SimpleServlet {

    protected final static String CONTENT_TYPE = "Content-Type";
    protected final static String PLAIN_CONTENT_TYPE = "text/plain; charset=utf-8";
    protected final static String CSS_CONTENT_TYPE = "text/css; charset=utf-8";
    protected final static String HTML_CONTENT_TYPE = "text/html; charset=utf-8";
    protected final static String JS_CONTENT_TYPE = "text/javascript; charset=utf-8";
    protected final static String FONT_CONTENT_TYPE = "application/x-font-ttf";
    protected final static String ICON_CONTENT_TYPE = "image/x-icon";

    @Override
    public void service(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception {
        if (httpRequest.getMethod().equals("GET")) {
            doGet(httpRequest, httpResponse);
        }

        if (httpRequest.getMethod().equals("POST")) {
            doPost(httpRequest, httpResponse);
        }
    }

    public void doGet(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception {}
    public void doPost(HttpRequest httpRequest, HttpResponse httpResponse) throws Exception {}
}
