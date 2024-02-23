package GUI;

import BACKEND.operations.IUserOperation;
import BACKEND.operations.UserOperation;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class logout extends JPanel{
    JButton _logoutButton;
    IUserOperation _udb;
    public logout(JTabbedPane jtb){
        this._udb = UserOperation.getOperation();

        setLayout(new BorderLayout());
        _logoutButton = new JButton("logout");
        add(_logoutButton,BorderLayout.CENTER);
        _logoutButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(_udb.logout()) {
                    jtb.remove(3);
                    jtb.remove(3);

                    JOptionPane.showMessageDialog(jtb, "logged out");
                    jtb.setSelectedIndex(0);
                }else{
                    JOptionPane.showMessageDialog(jtb,"unable to logout");
                }
            }
        });

    }
}
