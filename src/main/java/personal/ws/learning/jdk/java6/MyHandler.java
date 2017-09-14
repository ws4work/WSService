package personal.ws.learning.jdk.java6;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MyHandler implements HttpHandler {
    public void handle(HttpExchange t) throws IOException {
        InputStream is = t.getRequestBody();
        String response = "<***>Happy Every Day 2007!--lj</***>";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}

