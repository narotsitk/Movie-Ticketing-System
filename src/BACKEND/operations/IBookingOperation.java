package BACKEND.operations;

import BACKEND.models.Booking;
import BACKEND.models.Transaction;

import java.util.*;

public interface IBookingOperation {

    public boolean createBooking(Booking newBooking);
    public List<Booking> getByUserId(int id);
    public boolean bookingTransaction(Transaction tr);
    public Booking getById(int id);
}
