package com.example.library;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem {
    private ArrayList<Book> books = new ArrayList<>();

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();
        library.run();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        System.out.println("=== Welcome to the Library Management System ===");

        do {
            System.out.println("\n1. Add a Book");
            System.out.println("2. View All Books");
            System.out.println("3. Borrow a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> addBook(scanner);
                case 2 -> viewBooks();
                case 3 -> borrowBook(scanner);
                case 4 -> returnBook(scanner);
                case 5 -> System.out.println("Exiting the system. Goodbye!");
                default -> System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);

        scanner.close();
    }

    private void addBook(Scanner scanner) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author name: ");
        String author = scanner.nextLine();

        Book book = new Book(title, author);
        books.add(book);

        System.out.println("Book added successfully!");
    }

    private void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
        } else {
            System.out.println("\nAvailable Books:");
            for (int i = 0; i < books.size(); i++) {
                System.out.println((i + 1) + ". " + books.get(i));
            }
        }
    }

    private void borrowBook(Scanner scanner) {
        if (books.isEmpty()) {
            System.out.println("No books available to borrow.");
            return;
        }

        viewBooks();
        System.out.print("Enter the book number to borrow: ");
        int bookNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (bookNumber < 1 || bookNumber > books.size()) {
            System.out.println("Invalid book number!");
        } else {
            Book book = books.get(bookNumber - 1);
            if (book.isBorrowed()) {
                System.out.println("This book is already borrowed.");
            } else {
                book.setBorrowed(true);
                System.out.println("You have borrowed: " + book.getTitle());
            }
        }
    }

    private void returnBook(Scanner scanner) {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }

        viewBooks();
        System.out.print("Enter the book number to return: ");
        int bookNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (bookNumber < 1 || bookNumber > books.size()) {
            System.out.println("Invalid book number!");
        } else {
            Book book = books.get(bookNumber - 1);
            if (!book.isBorrowed()) {
                System.out.println("This book was not borrowed.");
            } else {
                book.setBorrowed(false);
                System.out.println("You have returned: " + book.getTitle());
            }
        }
    }
}

