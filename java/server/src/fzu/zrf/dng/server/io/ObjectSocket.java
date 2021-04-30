package fzu.zrf.dng.server.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ObjectSocket implements Closeable {
    private Socket soc;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public ObjectSocket(Socket socket) throws IOException {
        soc = socket;
        ois = new ObjectInputStream(socket.getInputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void close() throws IOException {
        ois.close();
        oos.close();
        soc.close();
    }

    public Object read() throws IOException, ClassNotFoundException {
        return ois.readObject();
    }

    public void write(Object obj) throws IOException {
        oos.writeObject(obj);
    }
}
