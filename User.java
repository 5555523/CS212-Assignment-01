package com.company;

// importing class ArrayList
import java.util.ArrayList;

public class User {
    // attributes
    private String user_id;
    private String name;
    private String contact_information;
    private ArrayList<Book> borrowed_books;

    // creating a constructor
    public User(String user_id, String name, String contact_information) {
        this.user_id = user_id;
        this.name = name;
        this.contact_information = contact_information;
        this.borrowed_books = new ArrayList<>();
    }

    // getters
    public String getuser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getContact_information() {
        return contact_information;
    }

    public ArrayList<Book> getborrowed_books() {
        return borrowed_books;
    }

    // creating method to set borrowed books
    public void setborrowed_books(ArrayList<Book> books) {
        this.borrowed_books = borrowed_books;
    }
}
