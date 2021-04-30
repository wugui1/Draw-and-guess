package fzu.zrf.dng.server.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import fzu.zrf.dng.both.io.LoginInfo;
import fzu.zrf.dng.both.io.RegisterInfo;
import fzu.zrf.dng.both.io.RegisterInfo.Result;

public class Procedure {
    private Procedure() {
    }

    public static RegisterInfo.Result addPlayer(RegisterInfo ri) {
        try (Connection conn = DataSource.getConnection();
                CallableStatement stat = conn.prepareCall("CALL add_player(?, ?, ?);");) {
            stat.setString(1, ri.name);
            stat.setString(2, ri.nickname);
            stat.setString(3, ri.password);
            stat.execute();
            return Result.SUCCESS;
        } catch (SQLException e) {
            return Result.FAILED;
        }
    }

    public static LoginInfo.Result login(LoginInfo li) {
        ResultSet rs = null;
        try (Connection conn = DataSource.getConnection();
                CallableStatement stat = conn.prepareCall("CALL login(?, ?);");) {
            stat.setString(1, li.name);
            stat.setString(2, li.password);
            rs = stat.executeQuery();
            if (rs.next()) {
                return new LoginInfo.Result(rs.getBoolean("success"), rs.getInt("type"), rs.getString("auth"));
            }
            return null;
        } catch (SQLException e) {
            return new LoginInfo.Result(false, 0, "");
        } finally {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
