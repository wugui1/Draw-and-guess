package fzu.zrf.dng.client.i18n;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class I18N {
    private static ResourceBundle rb;

    static {
        reload();
    }

    public static synchronized String get(String key, Object... args) {
        if (!rb.containsKey(key)) {
            return key;
        }
        return MessageFormat.format(rb.getString(key), args);
    }

    public static void reload() {
        rb = ResourceBundle.getBundle(I18N.class.getName());
    }
}
