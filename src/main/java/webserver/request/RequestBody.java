package webserver.request;

/**
 * @author Jim, Kim
 * @since 2020-09-23
 */
public class RequestBody {
    private String body;

    public RequestBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}
