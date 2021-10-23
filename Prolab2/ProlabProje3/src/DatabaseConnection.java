
import java.sql.*;


public class DatabaseConnection {
    Connection conn=null;
    public static Connection connect() throws Exception {
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/music_app","root","fatma123");	
    return conn;
 
}
}
