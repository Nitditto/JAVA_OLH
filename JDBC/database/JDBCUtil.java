package JDBC.database;

import java.sql.Connection;

public class JDBCUtil {
  public static Connection getConnection() throws ClassNotFoundException {
    Connection c=null;
    Class.forName("org.postgresql.Driver");
    String url=
    return c;

  }
}
