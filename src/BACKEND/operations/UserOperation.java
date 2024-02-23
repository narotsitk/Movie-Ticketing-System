package BACKEND.operations;

import BACKEND.models.Booking;
import BACKEND.models.User;
import com.sun.source.tree.WhileLoopTree;

import java.sql.*;


public class UserOperation implements IUserOperation{
    private final Connection _conn;

    public static UserOperation udb = null;

    public static UserOperation createOperation(Connection conn){
        if(udb == null){
            udb = new UserOperation(conn);
        }
        return udb;
    }

    public static  UserOperation getOperation(){
        return udb;
    }
    public UserOperation(Connection conn){
        this._conn = conn;
    }

    @Override
    public boolean register(User user) {
        try{
            PreparedStatement pstmt = this._conn.prepareStatement("INSERT INTO user(email,username,password) VALUES(?,?,?)");

                pstmt.setString(1,user.email);
                pstmt.setString(2,user.username);
                pstmt.setString(3,user.password);
                int result = pstmt.executeUpdate();
                if (result > 0 )
                    return  true;

        }catch(SQLException ex){
            return false;
        }
        return false;
    }
    public User authenticate(String username,String password){

        try{
            PreparedStatement pstmt = this._conn.prepareStatement("SELECT * FROM user WHERE username = ? LIMIT 1",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            pstmt.setString(1,username);
            ResultSet rst = pstmt.executeQuery();

            if (!rst.isBeforeFirst() ) {
                return null;
            }else{
                rst.absolute(1);
                String current_password = rst.getString("password");
                String hashed_password = User.hashPassword(password);


                if(current_password.equals(hashed_password)){

                    return User.createInstance(rst.getInt("id"),
                            rst.getString("email"),
                            rst.getString("username"),
                            current_password);
                }

            }


        }catch (SQLException ex){
            return null;
        }
        return null;
    }
    public boolean logout(){
        return User.removeInstance();
    }

    @Override
    public User getById(int id) {
        try{
            PreparedStatement pstmt = this._conn.prepareStatement("SELECT * FROM user WHERE id = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            pstmt.setInt(1,id);
            ResultSet rst = pstmt.executeQuery();

            if (!rst.isBeforeFirst() ) {
                return null;
            }else{
                rst.absolute(1);
                return new User(rst.getInt("id"),
                       rst.getString("email"),rst.getString("username"),
                        rst.getString("password"));
            }

        }catch(SQLException ex){
            return null;
        }
    }

    public boolean changeInformation(User user){
        try{

            PreparedStatement pstmt = this._conn.prepareStatement("UPDATE user SET username = ?,password = ?,email = ? WHERE id = ?");

            pstmt.setString(1, user.username);
            pstmt.setString(2, user.password);
            pstmt.setString(3, user.email);
            pstmt.setInt(4,user.id);

            int result = pstmt.executeUpdate();
            if (result > 0)
                return true;


        }catch(SQLException ex){
            return false;
        }
        return false;
    }
}
