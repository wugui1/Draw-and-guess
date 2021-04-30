package fzu.zrf.dng.server.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    private static ExecutorService e = Executors.newCachedThreadPool();

    public static void execute(Runnable r) {
        e.execute(r);
    }
}
