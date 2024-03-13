// creating a package
package com.company;

// importing class File from java.io package to create , delete files
import java.io.File;

// importing class FileWriter from java.io package to write character-based data
import java.io.FileWriter;

// importing IOException from java.io package to indicate errors in input and output
import java.io.IOException;

// importing ArrayList from java.util package for resizeable array implementation
import java.util.ArrayList;

// importing Scanner class from java.util package for reading inputs
import java.util.Scanner;

// create default class Book
class Book {
    // attributes
    private String book_id;
    private String title;
    private String author;
    private String genre;
    private boolean availability_status;

    // creating a constructor
    public Book(String book_id, String title, String author, String genre, boolean availability_status) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability_status = availability_status;

    }

    // getters
    public String getBook_id() {
        return book_id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    // creating a getter method to retrieve availability of books
    public boolean isAvailability_status() {
        return availability_status;
    }

    // creating a setter method to update availability status
    public void setAvailability_status(boolean availability_status) {
        this.availability_status = availability_status;
    }
}
