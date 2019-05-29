package datos;
import java.sql.*;

public class Conection {
    private static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static String JDBC_URL = "jdbc:mysql://localhost:3306/sga?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static String JDBC_USER ="root";
    private static String JDBC_PASSWORD = "admin";
    public static Driver driver = null;

    public static synchronized Connection getConnection()
        throws SQLException{
        if(driver == null){
            try {
                Class jdbcDriverClass = Class.forName(JDBC_DRIVER);
                driver = (Driver) jdbcDriverClass.newInstance();
                DriverManager.registerDriver(driver);
            }catch (Exception e){
                System.out.println("Fallo en cargar jdbc");
                e.printStackTrace();
            }
        }
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
    }

    public static void close (ResultSet rs){
        try {
            if (rs != null){
                rs.close();
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    public static void close (PreparedStatement statement){
        try{
            if (statement != null){
                statement.close();
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }

    public static void close (Connection conn){
        try {
            if (conn != null){
                conn.close();
            }
        }catch (SQLException sqle){
            sqle.printStackTrace();
        }
    }
}
