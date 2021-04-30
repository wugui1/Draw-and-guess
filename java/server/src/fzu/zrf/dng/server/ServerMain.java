package fzu.zrf.dng.server;

import java.net.ServerSocket;
import java.net.Socket;

import fzu.zrf.dng.both.data.Conf;
import fzu.zrf.dng.server.thread.Command;
import fzu.zrf.dng.server.thread.SocketQueue;

public class ServerMain {

    public static void main(String[] args) throws Exception {
        @SuppressWarnings("resource")
        ServerSocket ss = new ServerSocket(Conf.PORT);
        new Command().start();
        for (;;) {
            Socket s = ss.accept();
            SocketQueue.add(s);
        }
    }
}
