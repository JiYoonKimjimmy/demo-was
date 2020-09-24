package util;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author Jim, Kim
 * @since 2020-09-23
 */
public class IOUtils {
    /**
     * body 시작하는 지점부터 header 에 정의한 Content-Length 지점까지
     * 읽어서 return.
     * @param br
     * @param contentLength
     * @return
     * @throws IOException
     */
    public static String readData(BufferedReader br, int contentLength) throws IOException {
        char[] body = new char[contentLength];
        br.read(body, 0, contentLength);
        return String.valueOf(body);
    }
}
