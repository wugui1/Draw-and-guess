package fzu.zrf.dng.client.io;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import fzu.zrf.dng.both.data.Conf;

public class ObjectSocket implements Closeable {
    private Socket soc;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    public ObjectSocket() throws IOException {
        soc = new Socket(Conf.ADDRESS, Conf.PORT);
        oos = new ObjectOutputStream(soc.getOutputStream());
        ois = new ObjectInputStream(soc.getInputStream());
    }

    @Override
    public void close() throws IOException {
        oos.close();
        ois.close();
        soc.close();
    }

    public Object read() throws IOException, ClassNotFoundException {
        return ois.readObject();
    }

    public void write(Object obj) throws IOException {
        oos.writeObject(obj);
    }
}
