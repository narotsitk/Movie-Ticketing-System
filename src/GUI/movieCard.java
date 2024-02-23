package GUI;
import BACKEND.models.Movie;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.border.BevelBorder;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import BACKEND.models.*;
import BACKEND.operations.BookingOperation;
import BACKEND.operations.IBookingOperation;

public class movieCard extends JPanel {
    JLabel _movietitle;
    JLabel _movieGenre;
    JLabel _movieRuntime;
    JLabel _moviePoster;
    JLabel _movieRating;

    JButton _buyTicket;

    String  default_poster = "";

    IBookingOperation _bdb;

    public movieCard(Movie mov, JPanel frame,JTabbedPane jtb){
        this._bdb = BookingOperation.getOperation();

        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        _movietitle = new JLabel("Name: " + mov.name);
        _movieGenre = new JLabel("Genre: "+ mov.genre);
        _movieRuntime = new JLabel("Runtime: " + Integer.toString(mov.runtime));
        _movieRating = new JLabel("Rating: " + Integer.toString(mov.rating));
        _buyTicket = new JButton("Buy Tickets");

        try {
            BufferedImage poster = ImageIO.read(new File(mov.image_url));
            ImageIcon icon = new ImageIcon(poster);
            Image scaleImage = icon.getImage().getScaledInstance(150, 200,Image.SCALE_DEFAULT);
            _moviePoster = new JLabel(new ImageIcon(scaleImage));
        }catch(IOException ex){
            ex.printStackTrace();
        }

        _buyTicket.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                User user = User.getInstance();
                if(user == null){
                    JOptionPane.showMessageDialog(frame,"you must be logged in first");
                }else{
                    String quantity = JOptionPane.showInputDialog(frame,"Number of tickets to buy");
                    try{
                        int quantity_num = Integer.parseInt(quantity);
                        if(quantity_num == 0)
                           throw  new Exception("zero value quantity");

                        int confirm = JOptionPane.showConfirmDialog(frame,"Do you want to book ?");

                        if(confirm == JOptionPane.YES_OPTION) {
                            String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new java.util.Date());

                            Booking newBooking = new Booking(quantity_num, user.id, mov.id, quantity_num * mov.price,
                                    timeStamp);

                            if (_bdb.createBooking(newBooking)) {
                                JOptionPane.showMessageDialog(frame, "booked tickets");
//                                referesh booking
                                jtb.remove(4);
                                jtb.add("bookings",new your_bookings(jtb));

                            }else{
                                JOptionPane.showMessageDialog(frame, "some error occured during booking please try again");
                            }
                        }
                    }catch (Exception ex){
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(frame,"provide appropriate quantity");
                    }

                }
            }
        });
        GridBagConstraints gbc = new GridBagConstraints();
        add(_moviePoster);
        add(_movietitle);
        add(_movieGenre);
        add(_movieRuntime);
        add(_movieRating);
        add(_buyTicket);
        setVisible(true);
        setBorder(BorderFactory.createTitledBorder(mov.name));


    }
}
