package webserver.request;

/**
 * @author Jim, Kim
 * @since 2020-09-23
 */
class RequestLine {

    private String method;
    private String url;
    private String httpVersion;

    public RequestLine(String method, String url, String httpVersion) {
        this.method = method;
        this.url = url;
        this.httpVersion = httpVersion;
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getHttpVersion() {
        return httpVersion;
    }
}
