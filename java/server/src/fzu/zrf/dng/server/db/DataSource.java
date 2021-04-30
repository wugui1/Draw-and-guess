package fzu.zrf.dng.server.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSource {
    private static final javax.sql.DataSource ds = new ComboPooledDataSource();

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
