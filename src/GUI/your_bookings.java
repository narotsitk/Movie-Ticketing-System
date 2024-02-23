package GUI;
import BACKEND.models.*;
import BACKEND.operations.*;
import javax.swing.table.*;

import java.util.List;

import javax.swing.*;
import java.awt.*;
public class your_bookings extends JPanel{

    JPanel _bookingPanel;
    IBookingOperation _bdb;
    IMovieOperation _mdb;

    JTable table;
    DefaultTableModel _model;

    JScrollPane scrollPane;

    JPanel topPanel;

    public your_bookings(  JTabbedPane jtb) {
        setLayout(new BorderLayout());
        this._bdb = BookingOperation.getOperation();
        this._mdb = MovieOperation.getOperation();

        _bookingPanel = new JPanel();
        scrollPane = new JScrollPane(_bookingPanel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        List<Booking> bookings = this._bdb.getByUserId(User.getInstance().id);

        if (bookings.isEmpty()) {
            setBorder(BorderFactory.createTitledBorder("No Bookings"));
        } else {
            setBorder(BorderFactory.createTitledBorder("Your Bookings"));
            bookings.forEach(booking -> {
                _bookingPanel.add(new userbookings(booking, this._mdb, jtb));
            });
        }
        add(scrollPane, BorderLayout.CENTER);

    }
}
