package fzu.zrf.dng.client;

public class Args {
    private Args() {
    }

    private static String auth;

    public static synchronized void setAuth(String auth) {
        Args.auth = auth;
    }

    public static synchronized String getAuth() {
        return auth;
    }
}
