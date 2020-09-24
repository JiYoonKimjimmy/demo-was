package webserver.request;

import util.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jim, Kim
 * @since 2020-09-23
 */
public class HttpRequest {
    private RequestLine requestLine;
    private RequestHeader requestHeader;
    private RequestBody requestBody;

    public HttpRequest(RequestLine requestLine, RequestHeader requestHeader, RequestBody requestBody) {
        this.requestLine = requestLine;
        this.requestHeader = requestHeader;
        this.requestBody = requestBody;
    }

    public String getHeader(String key) {
        return requestHeader.getHeaderValue(key);
    }

    public String getMethod() {
        return requestLine.getMethod();
    }

    public String getPath() {
        return requestLine.getUrl();
    }

    public String getBody() {
        return requestBody.getBody();
    }

    public static HttpRequest create(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

        RequestLine requestLine = setRequestLine(br);
        RequestHeader requestHeader = setRequestHeader(br);
        RequestBody requestBody = setRequestBody(br, requestHeader.getContentLength());

        return new HttpRequest(requestLine, requestHeader, requestBody);
    }

    /**
     * Request 정보 생성
     * @param br
     * @return
     * @throws IOException
     */
    private static RequestLine setRequestLine(BufferedReader br) throws IOException {
        String[] requestLine = br.readLine().split(" ");
        return new RequestLine(requestLine[0], requestLine[1], requestLine[2]);
    }

    /**
     * Request Header 생성
     * @param br
     * @return
     * @throws IOException
     */
    private static RequestHeader setRequestHeader(BufferedReader br) throws IOException {
        String line;
        Map<String, String> headers = new HashMap<>();
        while (!(line = br.readLine()).equals("")) {
            String[] header = line.split(": ");
            headers.put(header[0], header[1]);
        }
        return new RequestHeader(headers);
    }

    /**
     * Request Body 생성
     * @param br
     * @param contentLength
     * @return
     * @throws IOException
     */
    private static RequestBody setRequestBody(BufferedReader br, int contentLength) throws IOException {
        if (contentLength == 0) {
            return null;
        }

        String body = IOUtils.readData(br, contentLength);
        return new RequestBody(body);
    }

}
