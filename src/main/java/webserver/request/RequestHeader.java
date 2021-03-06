package webserver.request;

import java.util.Map;

/**
 * @author Jim, Kim
 * @since 2020-09-23
 */
class RequestHeader {
    private Map<String, String> headers;

    public RequestHeader(Map<String, String> headers) {
        this.headers = headers;
    }

    public int getContentLength() {
        if (headers.get("Content-Length") == null) {
            return 0;
        }
        return Integer.parseInt(headers.get("Content-Length"));
    }

    public String getHeaderValue(String key) {
        return headers.get(key);
    }
}
