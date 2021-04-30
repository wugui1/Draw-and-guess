package fzu.zrf.dng.server.thread;

import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

import fzu.zrf.dng.both.io.LoginInfo;
import fzu.zrf.dng.both.io.RegisterInfo;
import fzu.zrf.dng.both.tuple.BinaryTuple;
import fzu.zrf.dng.server.io.ObjectSocket;

public class SocketQueue {
    private SocketQueue() {
    }

    private static final LinkedBlockingQueue<Socket> LBQ = new LinkedBlockingQueue<>();

    static {
        new Thread(() -> {
            try {
                for (;;) {
                    ObjectSocket s = new ObjectSocket(LBQ.take());
                    Object in = s.read();
                    if (in instanceof RegisterInfo) {
                        RegisterQueue.add(new BinaryTuple<ObjectSocket, RegisterInfo>(s, (RegisterInfo) in));
                    } else if (in instanceof LoginInfo) {
                        LoginQueue.add(new BinaryTuple<ObjectSocket, LoginInfo>(s, (LoginInfo) in));
                    } else {
                        s.close();
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public static void add(Socket s) {
        LBQ.add(s);
    }
}
