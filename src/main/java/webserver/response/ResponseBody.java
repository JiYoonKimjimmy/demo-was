package webserver.response;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;

/**
 * @author Jim, Kim
 * @since 2020-09-23
 */
class ResponseBody {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(ResponseBody.class.getCanonicalName());
    private byte[] body;

    public ResponseBody(byte[] body) {
        this.body = body;
    }

    public void responseBody(DataOutputStream dos) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getMessage());
        }
    }

    public String getBodyLength() {
        return String.valueOf(body.length);
    }

}
