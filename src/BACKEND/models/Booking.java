package BACKEND.models;

public class Booking {

    public int id;

    public int quantity;

    public int user;

    public int movie;

    public float total_amount;

    public String date;
    public boolean paid;

    public Booking(int quantity, int user, int movie, float total_amount,String date) {
        this.quantity = quantity;
        this.user = user;
        this.movie = movie;
        this.total_amount = total_amount;
        this.date = date;
        this.paid = false;
    }

    public Booking(int id, int quantity, int user, int movie, float total_amount,String date,boolean paid) {
        this.id = id;
        this.quantity = quantity;
        this.user = user;
        this.movie = movie;
        this.total_amount = total_amount;
        this.date = date;
        this.paid = paid;
    }
}
