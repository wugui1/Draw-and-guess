package fzu.zrf.dng.server.thread;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

import fzu.zrf.dng.both.io.RegisterInfo;
import fzu.zrf.dng.both.tuple.BinaryTuple;
import fzu.zrf.dng.server.db.Procedure;
import fzu.zrf.dng.server.io.ObjectSocket;

public class RegisterQueue {
    private RegisterQueue() {
    }

    private static final LinkedBlockingQueue<BinaryTuple<ObjectSocket, RegisterInfo>> LBQ = new LinkedBlockingQueue<>();

    static {
        new Thread(() -> {
            try {
                for (;;) {
                    BinaryTuple<ObjectSocket, RegisterInfo> s = LBQ.take();
                    ThreadPool.execute(() -> {
                        try (ObjectSocket os = s.first) {
                            os.write(Procedure.addPlayer(s.second));
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

    public static void add(BinaryTuple<ObjectSocket, RegisterInfo> put) {
        LBQ.add(put);
    }

}
