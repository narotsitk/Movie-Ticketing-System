package GUI;

import BACKEND.models.*;
import BACKEND.operations.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

public class userbookings extends JPanel{
    JLabel name;
    JLabel total_amount;
    JLabel date;
    JButton pay;
    JLabel paid;
    public userbookings(Booking booking,IMovieOperation _mdb,JTabbedPane jtb){
        setLayout(new GridLayout(0,5,12,8));
        setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(10, 10, 10, 10),  new EtchedBorder()));
        Movie movie = _mdb.getById(booking.movie);
        User user = User.getInstance();

        if (movie == null || user == null){
            JOptionPane.showMessageDialog(jtb,"Some error occurred");
            jtb.setSelectedIndex(0);
        }
        name = new JLabel(movie.name);
        total_amount = new JLabel("Total: "+Float.toString(booking.total_amount));
        date = new JLabel("Booked at: "+ booking.date);

        add(name);
        add(total_amount);
        add(date);
        if(booking.paid){
            paid = new JLabel("paid");
            add(paid);
        }else{
            pay = new JButton("pay");
            add(pay);

            pay.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new java.util.Date());
                    String payment_msg = "Do you want to pay ? Amount: " + Float.toString(booking.total_amount) + " Enter cvc:";
                    var cvc = JOptionPane.showInputDialog(jtb, payment_msg);

                    if (!cvc.isBlank() || !cvc.isEmpty()) {
                        var pin = JOptionPane.showInputDialog(jtb, "Enter pin");
                        if (pin.length() == 4) {
                            Transaction tr = new Transaction(user.id, booking.id,
                                    booking.total_amount, timeStamp);

                            if (BookingOperation.getOperation().bookingTransaction(tr)) {
                                JOptionPane.showMessageDialog(jtb, "Payment Complete");
                            } else {
                                JOptionPane.showMessageDialog(jtb, "Payment Failed");
                            }

                        } else {
                            JOptionPane.showMessageDialog(jtb, "enter valid pin");
                        }
                        jtb.remove(4);
                        jtb.add("bookings", new your_bookings(jtb));
                        jtb.setSelectedIndex(4);

                    } else {
                        JOptionPane.showMessageDialog(jtb, "Enter valid cvc number");
                    }
                }
            });
        }



        setVisible(true);
    }
}
