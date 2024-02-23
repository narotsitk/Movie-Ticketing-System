package GUI;
import BACKEND.models.User;
import BACKEND.operations.IUserOperation;
import BACKEND.operations.UserOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class register extends JPanel{

    JLabel _userLabel;
    JLabel _passwordLabel;
    JTextField _username;
    JPasswordField _password;
    JLabel _emailLabel;
    JTextField _email;
    JButton _registerButton;
    JPanel _registerPanel;
    IUserOperation _udb;
    public register(JTabbedPane jtb){
        this._udb = UserOperation.getOperation();

        setLayout(new BorderLayout());
        _registerPanel = new JPanel();
        _registerPanel.setBorder(BorderFactory.createTitledBorder("Welcome Back,"));
        _registerPanel.setLayout(null);
        _userLabel = new JLabel("Username: ");
        _passwordLabel = new JLabel("Password: ");
        _username = new JTextField();
        _password = new JPasswordField();
        _registerButton = new JButton("Login");
        _emailLabel = new JLabel("Email Address: ");
        _email = new JTextField();
        _registerPanel.add(_userLabel);
        _registerPanel.add(_passwordLabel);
        _registerPanel.add(_emailLabel);
        _registerPanel.add(_email);
        _registerPanel.add(_username);
        _registerPanel.add(_password);
        _registerPanel.add(_registerButton);

        add(_registerPanel,BorderLayout.CENTER);

        _userLabel.setBounds(500,200,100,50);
        _username.setBounds(500,240,200,20);
        _emailLabel.setBounds(500,260,100,50);
        _email.setBounds(500,300,200,20);
        _passwordLabel.setBounds(500,320,100,50);
        _password.setBounds(500,360,200,20);
        _registerButton.setBounds(550,420,100,30);
        add(_registerPanel);

        _registerButton.addMouseListener(new MouseAdapter() {
            @Override

            public void mouseClicked(MouseEvent e) {

                if (_email.getText().equals("") || _username.getText().equals("") || new String(_password.getPassword()).equals("")) {
                    JOptionPane.showMessageDialog(e.getComponent(),"Field cannot be empty");

                } else {

                    boolean ok = _udb.register(new User(_email.getText(), _username.getText(), new String(_password.getPassword())));
                    if (ok) {
                        User.removeInstance();
                        JOptionPane.showMessageDialog(jtb, "Now a registered user");
                        jtb.setSelectedIndex(0);

                    } else {
                        JOptionPane.showMessageDialog(jtb, "Could not register check everything");
                    }
                }
            }
        });

        setVisible(true);
    }
}
