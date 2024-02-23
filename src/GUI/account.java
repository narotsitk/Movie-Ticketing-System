package GUI;

import BACKEND.models.User;
import BACKEND.operations.UserOperation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class account extends JPanel {
    JPanel settingsPanel;
    JLabel _emailLabel;
    JTextField _email;
    JPasswordField _currentpass,_newpass;
    JLabel _currentpassLabel,_newpassLabel;
    User user;

    JButton _btnChange;
    public account(JTabbedPane jtb){
        settingsPanel = new JPanel();
        setLayout(new BorderLayout());
        user = User.getInstance();
        settingsPanel.setLayout(null);

        settingsPanel.setBorder(BorderFactory.createTitledBorder("Welcome, " + user.username));
        _emailLabel = new JLabel("Email Address: ");
        _currentpassLabel = new JLabel("Enter Current Password");
        _newpassLabel = new JLabel("Enter new password");
        _btnChange = new JButton("change");

        _email = new JTextField(user.email);
        _currentpass = new JPasswordField();
        _newpass = new JPasswordField();

        settingsPanel.add(_emailLabel);
        settingsPanel.add(_email);
        settingsPanel.add(_currentpassLabel);
        settingsPanel.add(_currentpass);
        settingsPanel.add(_newpassLabel);
        settingsPanel.add(_newpass);
        settingsPanel.add(_btnChange);


        _emailLabel.setBounds(500,300,100,50);
        _email.setBounds(500,340,200,20);
        _currentpassLabel.setBounds(500,360,200,50);
        _currentpass.setBounds(500,400,200,20);
        _newpassLabel.setBounds(500,420,200,50);
        _newpass.setBounds(500,460,200,20);
        _btnChange.setBounds(550,520,100,30);

        _btnChange.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!_email.getText().equals("") ){
                    user.email = _email.getText();
                    String current_password = new String(_currentpass.getPassword());
                    String new_password = new String(_newpass.getPassword());

                    if(!current_password.equals("") && !new_password.equals("")){
                        System.out.println(user.password + " " + User.hashPassword(current_password));
                       if(User.hashPassword(current_password).equals(user.password)){
                           user.password = User.hashPassword(new_password);
                        }else{
                           JOptionPane.showMessageDialog(jtb,"Password doesnot match with current one");
                       }
                    }

                    if(UserOperation.getOperation().changeInformation(user)){
                        JOptionPane.showMessageDialog(jtb,"Information Changed");
                        _email.setText(user.email);
                    }
                }
            }
        });

        add(settingsPanel);
        setVisible(true);
    }
}
