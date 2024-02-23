package GUI;

import BACKEND.DBConnection;
import BACKEND.operations.*;

import java.sql.Connection;
import java.sql.SQLException;

public class app {
//    main application runner point
    public static void main(String[] args) throws SQLException {
        Connection _conn = DBConnection.getConnection();
        MovieOperation mdb = MovieOperation.createOperation(_conn);
        BookingOperation bdb = BookingOperation.createOperation(_conn);
        UserOperation udb = UserOperation.createOperation(_conn);

        new home();
    }
}
