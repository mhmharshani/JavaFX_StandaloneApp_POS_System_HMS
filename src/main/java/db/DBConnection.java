package db;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static DBConnection instance;

    @Getter
    private Connection connection;

    private DBConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/thogakage";
        String userName ="root";
        String password="199161500318";
        connection=DriverManager.getConnection(url,userName,password);
    }

    public static DBConnection getInstance() throws SQLException {
        return instance==null?instance=new DBConnection():instance;
    }
}
