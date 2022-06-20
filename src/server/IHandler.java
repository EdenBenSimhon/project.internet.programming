package server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface IHandler {
    public abstract void handleClient(InputStream fromClient,
                                      OutputStream toClient) throws IOException, ClassNotFoundException;
}
