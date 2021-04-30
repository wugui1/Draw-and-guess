package fzu.zrf.dng.server.thread;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

import fzu.zrf.dng.both.io.LoginInfo;
import fzu.zrf.dng.both.tuple.BinaryTuple;
import fzu.zrf.dng.server.db.Procedure;
import fzu.zrf.dng.server.io.ObjectSocket;

public class LoginQueue {
    private LoginQueue() {
    }

    private static final LinkedBlockingQueue<BinaryTuple<ObjectSocket, LoginInfo>> LBQ = new LinkedBlockingQueue<>();

    static {
        new Thread(() -> {
            try {
                for (;;) {
                    BinaryTuple<ObjectSocket, LoginInfo> s = LBQ.take();
                    ThreadPool.execute(() -> {
                        try (ObjectSocket os = s.first) {
                            os.write(Procedure.login(s.second));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
            } catch (InterruptedException e1) {
                throw new RuntimeException(e1);
            }
        }).start();
    }

    public static void add(BinaryTuple<ObjectSocket, LoginInfo> put) {
        LBQ.add(put);
    }

}
