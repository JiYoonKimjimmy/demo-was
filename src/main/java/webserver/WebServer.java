package webserver;


import util.PropertyUtils;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jim, Kim
 * @since 2020-09-23
 */
public class WebServer {
    private static final Logger logger = Logger.getLogger(WebServer.class.getCanonicalName());

    private final int NUM_THREADS = 50;
    private final int port;

    public WebServer() {
        Properties properties = PropertyUtils.getInstance().getProperties();
        this.port = Integer.parseInt(properties.getProperty("server.port"));
    }

    public void start() throws IOException {
        ExecutorService pool = Executors.newFixedThreadPool(NUM_THREADS);
        try (ServerSocket server = new ServerSocket(port)) {
            logger.info("Accepting connections on port " + server.getLocalPort());

            Socket connection;
            while ((connection = server.accept()) != null) {
                Runnable run = new RequestHandler(connection);
                pool.submit(run);
            }
        }
    }

    public static void main(String[] args) {
        try {
            WebServer webserver = new WebServer();
            webserver.start();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Server could not start", e);
        }
    }
}
