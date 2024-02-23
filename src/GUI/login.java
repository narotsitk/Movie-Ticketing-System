package GUI;

import BACKEND.operations.IMovieOperation;
import BACKEND.operations.IUserOperation;
import BACKEND.models.*;
import BACKEND.operations.UserOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class login extends JPanel{

    JLabel _userLabel;
    JLabel _passwordLabel;

    JTextField _username;
    JPasswordField _password;
     JButton _loginButton;
     JPanel _loginPanel;

     IUserOperation _udb;

    public login(JTabbedPane jtb){
        this._udb = UserOperation.getOperation();

        setLayout(new BorderLayout());
        _loginPanel = new JPanel();
        _loginPanel.setBorder(BorderFactory.createTitledBorder("Welcome Back,"));
        _loginPanel.setLayout(null);


        _userLabel = new JLabel("Username: ");
        _passwordLabel = new JLabel("Password: ");
        _username = new JTextField();
        _password = new JPasswordField();
        _loginButton = new JButton("Login");



        _loginPanel.add(_userLabel);
        _loginPanel.add(_passwordLabel);
        _loginPanel.add(_username);
        _loginPanel.add(_password);
        _loginPanel.add(_loginButton);

         add(_loginPanel,BorderLayout.CENTER);

        _userLabel.setBounds(500,200,100,50);
        _username.setBounds(500,240,200,20);
        _passwordLabel.setBounds(500,260,100,50);
        _password.setBounds(500,300,200,20);
        _loginButton.setBounds(550,340,100,30);
        add(_loginPanel);

        _loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                User user = _udb.authenticate(_username.getText(),_password.getText());

                if(user != null){
                    jtb.remove(1);
                    JOptionPane.showMessageDialog(_loginPanel,"Logged in");
                    jtb.add("logout",new logout(jtb));
                    jtb.add(user.username, new account(jtb));
                    jtb.add("bookings", new your_bookings(jtb));
                    jtb.setSelectedIndex(0);
                }else{
                    JOptionPane.showMessageDialog(_loginPanel,"Username or Password not valid");
                }
            }
        });

        setVisible(true);

    }


}
