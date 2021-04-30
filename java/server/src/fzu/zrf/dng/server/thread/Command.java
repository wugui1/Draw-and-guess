package fzu.zrf.dng.server.thread;

import java.util.Scanner;

public class Command extends Thread {
    @Override
    public void start() {
        new Thread(() -> {
            @SuppressWarnings("resource")
            Scanner input = new Scanner(System.in);
            for (;;) {
                System.out.print("> ");
                String[] cmd = input.nextLine().trim().toLowerCase().split("\\s+");
                if (cmd.length == 0)
                    continue;
                switch (cmd[0]) {
                case "exit":
                    System.exit(0);
                    break;
                }
            }
        }).start();
    }
}
