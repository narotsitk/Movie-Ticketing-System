package BACKEND.models;

public class Movie {
    public int id;
    public String name;
    public String genre;
    public String description;
    public String released_date;
    public float price;
    public int rating;
    public int runtime;
    public int stock;
    public String image_url;
    String base_path = "D:/Spring-WorkSpace/MovieTicketSystem/static/images/";
    public Movie(int id, String name, String genre, String description, String released_date, float price, int runtime,int rating,int stock,String image_url) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.description = description;
        this.released_date = released_date;
        this.price = price;
        this.runtime = runtime;
        this.rating = rating;
        this.stock = stock;
        this.image_url = base_path+image_url;
    }
}
