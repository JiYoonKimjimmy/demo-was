package webserver;

import webserver.request.HttpRequest;
import webserver.response.HttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jim, Kim
 * @since 2020-09-23
 */
public class RequestHandler implements Runnable {
    private static final Logger logger = Logger.getLogger(WebServer.class.getCanonicalName());

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    @Override
    public void run() {
        logger.info("New Client Connect! IP : " + connection.getInetAddress() + ", PORT : " + connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            // HttpRequest 생성
            HttpRequest httpRequest = HttpRequest.create(in);
            // HttpResponse 생성
            HttpResponse httpResponse = HttpResponse.create(out);
            // Dispatcher 생성
            Dispatcher dispatcher = new Dispatcher(httpRequest, httpResponse);
            dispatcher.dispatch();

        } catch (IOException e) {
            logger.log(Level.WARNING, "Error talking to " + connection.getRemoteSocketAddress(), e);
        }
    }
}
