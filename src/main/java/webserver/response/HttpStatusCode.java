package webserver.response;

/**
 * @author Jim, Kim
 * @since 2020-09-23
 */
public enum HttpStatusCode {
    OK("200 OK"),
    FOUND("302 Found"),
    UNAUTHORIZED("401 Unauthorized"),
    FORBIDDEN("403 Forbidden"),
    NOT_FOUND("404 Not Found"),
    INTERNAL_SERVER_ERROR("500 Internal Server Error");

    private String httpStatusNumber;

    HttpStatusCode(String httpStatusNumber) {
        this.httpStatusNumber = httpStatusNumber;
    }

    public String getHttpStatusNumber() {
        return httpStatusNumber;
    }
}
