package GUI;
import BACKEND.DBConnection;
import BACKEND.operations.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;

import BACKEND.models.*;

public class home extends JFrame{
    JTabbedPane _jtb;
    public home(){
        _jtb = new JTabbedPane();
        _jtb.add("movies", new movieList(_jtb));


        if(User.getInstance() != null){
            _jtb.add("logout",new logout(_jtb));
            _jtb.add(User.getInstance().username, new account(_jtb));
        }else{
            _jtb.add("login",new login(_jtb));
            _jtb.add("register", new register(_jtb));
        }

        add(_jtb);
        setVisible(true);
        setSize(1600,1600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("MovieMazza");

    }

}