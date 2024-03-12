package com.company;

// importing ArrayList from java.util package for resizeable array implementation
import java.util.ArrayList;

// importing class File from java.io package to create , delete files
import java.io.File;

// used to specify that file with specifies pathname does not exist
import java.io.FileNotFoundException;

// importing class FileWriter from java.io package to write character-based data
import java.io.FileWriter;

// importing IOException from java.io package to indicate errors in input and output
import java.io.IOException;

// importing Scanner class from java.util package for reading inputs
import java.util.Scanner;

public class Library {
    // attributes
    ArrayList<Book> books;
    ArrayList<User> users;

    // creating constructor
    public Library() {
        books = new ArrayList<>();
        users = new ArrayList<>();
    }

    // method to add books
    public void addBooks(Book book) {
        books.add(book);
    }

    // method to add user(User user){
    public void addUsers(User user) {
        users.add(user);
    }

    // method to display books
    public void displayBooks() {
        // using enhanced for loop
        for (Book book : books) {
            System.out.println();
            String availability;
            if (book.isAvailability_status()) {
                availability = "Yes";
            } else {
                availability = "No";
            }
            System.out.println(book.getTitle() + " by " + book.getAuthor() + " Available: " + availability);
        }
    }

    // method to search for books by title
    public Book searchBooksByTitle(String title) {
        for (Book book : books) {
            // ignoring case sensitivity
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        // returns null if book is not found
        return null;
    }

    // method to search for books by author
    public Book searchBooksByAuthor(String author) {
        for (Book book : books) {
            // ignoring case sensitivity
            if (book.getAuthor().equalsIgnoreCase(author)) {
                return book;
            }
        }
        // returns null if book is not found
        return null;
    }

    // method to search for books by id
    public User searchBooksByUserId(String userId) {
        for (User user : users) {
            // ignoring case sensitivity
            if (user.getuser_id().equalsIgnoreCase(userId)) {
                return user;
            }
        }

        // returns null if book is not found
        return null;
    }

    // method to borrow books
    public void borrowBook(String user_id, String bookTitle) {
        Book book = searchBooksByTitle(bookTitle);
        if (book == null) {
            System.out.println("Book not found");
            return;
        }
        User user = searchBooksByUserId(user_id);
        if (user == null) {
            System.out.println("User not found");
            return;
        }

        // checking if book is available for borrowing or not
        if (!book.isAvailability_status()) {
            System.out.println("Book is not available for borrowing.");
            return;
        }
        user.getborrowed_books().add(book);
        book.setAvailability_status(false);
        System.out.println("Book borrowed successfully.");
    }

    // method to return borrowed books
    public void returnBook(String user_id, String bookTitle) {
        Book book = searchBooksByTitle(bookTitle);
        if (book == null) {
            System.out.println("Book not found");
            return;
        }
        User user = searchBooksByUserId(user_id);
        if (user == null) {
            System.out.println("User not found");
            return;
        }

        // using contain method of Array List to check if Array contains book or not
        if (!user.getborrowed_books().contains(book)) {
            System.out.println("User has not borrowed this book.");
            return;
        }
        user.getborrowed_books().remove(book);
        book.setAvailability_status(true);
        System.out.println("Book returned successfully.");
    }
}

// LibraryManagementSystem class
class LibraryManagementSystem {
    private static String book_file = "books.txt";
    private static String user_file = "users.txt";
    private static Library lib = new Library();

    public static void main(String[] args) {
        loadBooks();
        displayMenu();
    }

    private static void loadBooks() {
        File file = new File(book_file);
        if (!file.exists()) {
            System.out.println("Book file does not exist!");
            return;
        }

        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] arr = line.split(",");
                if (arr.length < 5) {
                    System.out.println("Invalid data format in Book file");
                    continue;
                }
                String book_id = arr[0];
                String title = arr[1];
                String author = arr[2];
                String genre = arr[3];
                boolean availability_status = Boolean.parseBoolean(arr[4]);
                lib.addBooks(new Book(book_id, title, author, genre, availability_status));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Book file is not found!");
        }
    }

    private static void saveData() {
        try {
            saveBooks();
            saveUsers();
        } catch (IOException e) {
            System.out.println("Error saving data!");
        }
    }

    private static void saveBooks() throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(book_file);
            for (Book book : lib.books) {
                writer.write(String.join(",", book.getBook_id(), book.getTitle(), book.getAuthor(), book.getGenre(),
                        String.valueOf(book.isAvailability_status())));
                writer.write("\n");
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private static void saveUsers() throws IOException {
        FileWriter writer = null;
        try {
            writer = new FileWriter(user_file);
            for (User user : lib.users) {
                writer.write(String.join(",", user.getuser_id(), user.getName(), user.getContact_information()));
                writer.write("\n");
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private static void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\t\t\t\t\t\tLibrary Management System");
            System.out.println("1. Add books");
            System.out.println("2. Add users");
            System.out.println("3. Display Books");
            System.out.println("4. Borrow books");
            System.out.println("5. Return Books");
            System.out.println("6. Search for Books by Title or Author");
            System.out.println("7. Search for Books by user-id");
            System.out.println("0. Exit the program");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline character
            switch (choice) {
                case 1:
                    addBooks(scanner);
                    break;
                case 2:
                    addUsers(scanner);
                    break;
                case 3:
                    lib.displayBooks();
                    break;
                case 4:
                    borrowBook(scanner);
                    break;
                case 5:
                    returnBook(scanner);
                    break;
                case 6:
                    searchBooksByTitleOrAuthor(scanner);
                    break;
                case 7:
                    searchBooksByUserId(scanner);
                    break;
                case 0:
                    saveData();
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again!");
            }
        } while (choice != 0);

    }

    // method to add books
    private static void addBooks(Scanner scanner) {
        System.out.println("Enter book details:");
        System.out.print("Book ID: ");
        String book_id = scanner.nextLine();
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("Genre: ");
        String genre = scanner.nextLine();
        lib.addBooks(new Book(book_id, title, author, genre, true));
        System.out.println("Book added successfully!");
    }

    // method to add users
    private static void addUsers(Scanner scanner) {
        System.out.println("Enter User details:");
        System.out.print("User ID: ");
        String user_id = scanner.nextLine();
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Contact Information: ");
        String contact_information = scanner.nextLine();
        lib.addUsers(new User(user_id, name, contact_information));
        System.out.println("User added successfully!");
    }

    // method to borrow books
    private static void borrowBook(Scanner scanner) {
        System.out.print("Enter User id: ");
        String user_id = scanner.nextLine();
        System.out.print("Enter Book title: ");
        String book_title = scanner.nextLine();
        lib.borrowBook(user_id, book_title);
    }

    // method to return books
    private static void returnBook(Scanner scanner) {
        System.out.print("Enter User id: ");
        String user_id = scanner.nextLine();
        System.out.print("Enter Book title: ");
        String book_title = scanner.nextLine();
        lib.returnBook(user_id, book_title);
    }

    // method to search for book by title or author
    private static void searchBooksByTitleOrAuthor(Scanner scanner) {
        System.out.print("Enter Title or Author: ");
        String input = scanner.nextLine();
        Book book = lib.searchBooksByTitle(input);
        if (book == null) {
            book = lib.searchBooksByAuthor(input);
        }
        if (book == null) {
            System.out.println("No matching books found.");
        } else {
            System.out.println("Book found: " + book.getTitle() + " by " + book.getAuthor());
        }
    }

    // method to search for book by user id
    private static void searchBooksByUserId(Scanner scanner) {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        User user = lib.searchBooksByUserId(userId);
        if (user == null) {
            System.out.println("User not found.");
        } else {
            System.out.println("User found: " + user.getName() + " with ID: " + user.getuser_id());
        }
    }
}
