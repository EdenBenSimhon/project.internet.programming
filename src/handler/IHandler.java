package handler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public interface IHandler {
    /**
     * The abstract method handle request and answer from/to client
     * @param fromClient
     * @param toClient
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public abstract void handleClient(InputStream fromClient,
                                      OutputStream toClient) throws IOException, ClassNotFoundException;

}
