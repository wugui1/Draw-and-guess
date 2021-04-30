package fzu.zrf.dng.client;

import java.util.EnumMap;

public class Args {
    private Args() {
    }

    public enum Key {
        AUTH, USER_TYPE;
    }

    private static final EnumMap<Key, Object> map = new EnumMap<>(Key.class);

    public static synchronized void setValue(Key key, Object value) {
        map.put(key, value);
    }

    public static synchronized void getValue(Key key) {
        map.get(key);
    }

}
