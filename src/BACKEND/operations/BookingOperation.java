package BACKEND.operations;

import BACKEND.models.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.*;
public class BookingOperation implements IBookingOperation{
    private final Connection _conn;

    public static BookingOperation bdb = null;
    public static BookingOperation createOperation(Connection conn){
        if(bdb == null){
            bdb = new BookingOperation(conn);
        }
        return bdb;
    }

    public static  BookingOperation getOperation(){
        return bdb;
    }

    public BookingOperation(Connection _conn){
        this._conn = _conn;
    }
    @Override
    public boolean createBooking(Booking newBooking) {
        try{
            PreparedStatement pstmt = this._conn.prepareStatement("INSERT INTO booking(user,movie,quantity,total_amount,date) VALUES(?,?,?,?,?)");
            pstmt.setInt(1,newBooking.user);
            pstmt.setInt(2,newBooking.movie);
            pstmt.setInt(3,newBooking.quantity);
            pstmt.setFloat(4,newBooking.total_amount);
            pstmt.setString(5, newBooking.date);
            int result = pstmt.executeUpdate();

            if (result > 0)
                return true;

        }catch(SQLException ex){
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    @Override
    public List<Booking> getByUserId(int id) {
        List<Booking> bookings = new ArrayList<>();
        try{


            PreparedStatement pstmt = this._conn.prepareStatement("SELECT * FROM booking WHERE user = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            pstmt.setInt(1,id);
            ResultSet rst = pstmt.executeQuery();

            while(rst.next()){
                bookings.add( new Booking(rst.getInt("id"),
                        rst.getInt("quantity"),rst.getInt("user"),
                        rst.getInt("movie"), rst.getFloat("total_amount"),
                        rst.getString("date"), rst.getBoolean("paid"))
                );
            }

        }catch(SQLException ex){
            return bookings;
        }
        return bookings;
    }

    public Booking getById(int id) {
        try{
            PreparedStatement pstmt = this._conn.prepareStatement("SELECT * FROM booking WHERE id = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            pstmt.setInt(1,id);
            ResultSet rst = pstmt.executeQuery();

            if (!rst.isBeforeFirst() ) {
                return null;
            }else{
                rst.absolute(1);
                return new Booking(rst.getInt("id"),rst.getInt("quantity"),
                        rst.getInt("user"),rst.getInt("movie"),
                        rst.getFloat("total_amount"),rst.getString("date"),
                        rst.getBoolean("paid"));
            }

        }catch(SQLException ex){
            return null;
        }
    }
    public boolean bookingTransaction(Transaction tr){
        try{
            PreparedStatement pstmt = this._conn.prepareStatement("INSERT INTO transaction(user,booking,amount,date) VALUES(?,?,?,?)");
            PreparedStatement pstmt2 = this._conn.prepareStatement("UPDATE booking SET paid = 1 WHERE id = ? AND user = ?");
            PreparedStatement pstmt3 = this._conn.prepareStatement("SELECT stock FROM movie WHERE id = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            PreparedStatement pstm4 = this._conn.prepareStatement("UPDATE movie SET quantity = ? WHERE uid = ?");

            this._conn.setAutoCommit(false);

            Booking booking = this.getById(tr.booking);

            pstmt.setInt(1,tr.user);
            pstmt.setInt(2,tr.booking);
            pstmt.setFloat(3,tr.amount);
            pstmt.setString(4,tr.date);

            pstmt2.setInt(1,tr.booking);
            pstmt2.setInt(2,tr.user);

            pstmt3.setInt(1,booking.movie);

            pstm4.setInt(2,booking.movie);

            ResultSet rst = pstmt3.executeQuery();
            rst.absolute(1);
            int stock = rst.getInt("stock");
            stock -= booking.quantity;
            pstm4.setInt(1,stock);

            int result1 = pstmt.executeUpdate();
            int result2 = pstmt2.executeUpdate();
            int result3 = pstmt.executeUpdate();

            if(result1 > 0 && result2 > 0 && result3 > 0) {
                this._conn.commit();
                return true;
            }

        }catch(Exception ex){
            ex.printStackTrace();
            try {
                this._conn.rollback();
                return false;
            }catch(SQLException exv2){
                return false;
            }
        }
        return false;
    }
}
