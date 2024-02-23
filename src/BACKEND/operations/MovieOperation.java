package BACKEND.operations;

import BACKEND.models.Booking;
import BACKEND.models.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieOperation implements IMovieOperation{
    private final Connection _conn;

    public MovieOperation(Connection _conn){
        this._conn = _conn;
    }

    public static MovieOperation mdb = null;

    public static MovieOperation createOperation(Connection conn){
        if(mdb == null){
            mdb = new MovieOperation(conn);
        }
        return mdb;
    }

    public static  MovieOperation getOperation(){
        return mdb;
    }

    public List<Movie> getAllMovies(){
        List<Movie> mov = new ArrayList<>();
        try{

            Statement stmt = this._conn.createStatement();
            ResultSet rst = stmt.executeQuery("SELECT * FROM movie");

            while(rst.next()) {

                mov.add(
                        new Movie(
                                rst.getInt("id"),
                                rst.getString("name"),
                                rst.getString("genre"),
                                rst.getString("description"),
                                rst.getString("released_date"),
                                rst.getFloat("price"),
                                rst.getInt("runtime"),
                                rst.getInt("rating"),
                                rst.getInt("stock"),
                                rst.getString("img_url")
                        )
                );
            }
            }catch(SQLException ex){
                return mov;
            }

    return mov;
    }

    @Override
    public Movie getById(int id) {
        try{
            PreparedStatement pstmt = this._conn.prepareStatement("SELECT * FROM movie WHERE id = ?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            pstmt.setInt(1,id);
            ResultSet rst = pstmt.executeQuery();

            if (!rst.isBeforeFirst() ) {
                return null;
            }else{
                rst.absolute(1);
                return new Movie(rst.getInt("id"),
                        rst.getString("name"),rst.getString("genre"),
                        rst.getString("description"), rst.getString("released_date"),
                        rst.getFloat("price"),rst.getInt("runtime"),
                        rst.getInt("rating"),rst.getInt("stock"),
                        rst.getString("img_url"));
            }

        }catch(SQLException ex){
            ex.printStackTrace();
            return null;
        }
    }
}
