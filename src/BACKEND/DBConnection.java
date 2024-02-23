package BACKEND;
import java.sql.*;
import java.lang.*;
public class DBConnection {

    private static String _connection_class = "com.mysql.cj.jdbc.Driver";
    private static String _connection_url = "jdbc:mysql://localhost:3306/moviedb";
    private static String _user = "root";
    private static String _password = "root";

    private static Connection _conn = null;

    public static Connection getConnection(){
        if (_conn != null)
            return _conn;
        try {
            Class.forName(_connection_class);
            _conn = DriverManager.getConnection(_connection_url,_user,_password);
        }catch(ClassNotFoundException ex){
            ex.printStackTrace();
        }catch (SQLException ex){
            ex.printStackTrace();
        }

        return _conn;
    }
}
