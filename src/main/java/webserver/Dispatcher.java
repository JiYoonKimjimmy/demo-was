package webserver;

import exception.InternalServerException;
import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

import java.awt.print.Pageable;
import java.util.logging.Logger;

/**
 * @author Jim, Kim
 * @since 2020-09-24
 */
public class Dispatcher {
    private static final Logger logger = Logger.getLogger(HttpResponse.class.getCanonicalName());

    private HttpRequest httpRequest;
    private HttpResponse httpResponse;

    public Dispatcher(HttpRequest httpRequest, HttpResponse httpResponse) {
        this.httpRequest = httpRequest;
        this.httpResponse = httpResponse;
    }

    public void dispatch() {
        try {
            logger.info("requestLine : " + httpRequest.getPath());
            HandlerMapping.findServlet(httpRequest.getPath()).service(httpRequest, httpResponse);
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerException(httpRequest, httpResponse);
        }
    }

}
