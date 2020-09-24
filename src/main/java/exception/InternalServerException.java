package exception;

import webserver.request.HttpRequest;
import webserver.response.HttpResponse;
import webserver.response.HttpStatusCode;
import webserver.servlet.ErrorServlet;

/**
 * @author Jim, Kim
 * @since 2020-09-24
 */
public class InternalServerException extends RuntimeException {

    public InternalServerException(String msg, Throwable t) { super(msg, t); }

    public InternalServerException(String msg) { super(msg); }

    public InternalServerException() { super(); }

    public InternalServerException(HttpRequest httpRequest, HttpResponse httpResponse) {
        try {
            new ErrorServlet("500", HttpStatusCode.INTERNAL_SERVER_ERROR).doGet(httpRequest, httpResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
