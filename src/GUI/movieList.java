package GUI;
import BACKEND.models.Booking;
import BACKEND.operations.BookingOperation;
import BACKEND.operations.IBookingOperation;
import BACKEND.operations.IMovieOperation;
import BACKEND.operations.MovieOperation;

import java.awt.*;
import javax.swing.*;

public class movieList extends JPanel{

    JPanel moviePanel;
    JScrollPane scrollPane;

    IMovieOperation _mdb;
    IBookingOperation _bdb;

    public movieList(JTabbedPane jtb){
        this._bdb = BookingOperation.getOperation();
        this._mdb = MovieOperation.getOperation();

        setLayout(new BorderLayout());

        moviePanel = new JPanel();
        moviePanel.setLayout(new GridLayout(0,3,2,2));
        moviePanel.setBorder(BorderFactory.createTitledBorder("Newly Released"));

        this._mdb.getAllMovies().forEach(movie -> {
            moviePanel.add(new movieCard(movie,this,jtb));
        });

        scrollPane = new JScrollPane(moviePanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(scrollPane);
        setVisible(true);

    }

}
