package webserver.response;

import exception.InternalServerException;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

/**
 * @author Jim, Kim
 * @since 2020-09-23
 */
public class HttpResponse {
    private static final Logger logger = Logger.getLogger(HttpResponse.class.getCanonicalName());

    public static final String HTTP_VERSION_1_1 = "HTTP/1.1";

    private StatusLine statusLine;
    private ResponseHeader responseHeader;
    private ResponseBody responseBody;
    private DataOutputStream dos;

    public HttpResponse(DataOutputStream dos) {
        this.dos = dos;
        responseHeader = new ResponseHeader();
    }

    public static HttpResponse create(OutputStream out) {
        return new HttpResponse(new DataOutputStream(out));
    }

    public void addStatusLine(String httpVersion, HttpStatusCode statusCode) {
        statusLine = new StatusLine(httpVersion, statusCode);
    }

    public void addResponseHeader(String key, String value) {
        responseHeader.addHeader(key, value);
    }

    public void addResponseBody(String url) {
        try {
            this.responseBody = new ResponseBody(Files.readAllBytes(Paths.get("./webapp" + url)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void forward(String filePath) throws Exception {
        addResponseBody(filePath);
        addResponseHeader("Content-Length", responseBody.getBodyLength());
        writeResponseMessage();
        responseBody.responseBody(dos);
    }

    public void ok(String filePath) throws Exception {
        addStatusLine(HTTP_VERSION_1_1, HttpStatusCode.OK);
        forward(filePath);
    }

    public void error(String filePath, HttpStatusCode httpStatusCode) throws Exception {
        addStatusLine(HTTP_VERSION_1_1, httpStatusCode);
        filePath = "";
        forward(filePath);
    }

    public void sendRedirect(String url) throws Exception {
        addStatusLine(HTTP_VERSION_1_1, HttpStatusCode.FOUND);
        addResponseHeader("Location", url);
        writeResponseMessage();
    }

    private void writeResponseMessage() throws Exception {
        statusLine.addWriteStatusLine(dos);
        responseHeader.addWriteHeader(dos);
    }

}
