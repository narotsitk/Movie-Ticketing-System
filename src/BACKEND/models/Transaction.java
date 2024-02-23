package BACKEND.models;

public class Transaction {
    public int id;
    public int user;
    public int booking;
    public float amount;
    public String date;

    public Transaction(int user, int booking, float amount, String date) {
        this.user = user;
        this.booking = booking;
        this.amount = amount;
        this.date = date;
    }

    public Transaction(int id, int user, int booking, float amount, String date) {
        this.id = id;
        this.user = user;
        this.booking = booking;
        this.amount = amount;
        this.date = date;
    }
}
