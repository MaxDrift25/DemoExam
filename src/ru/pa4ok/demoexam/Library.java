package ru.pa4ok.demoexam;

import java.util.Arrays;
import java.util.Scanner;

public class Library
{
    private String address;
    private Book[] books;

    public Library(String address, Book[] books) {
        this.address = address;
        this.books = books;
    }

    public void readBook() throws BookReadException, LibrarySpaceException {
        Scanner scanner = new Scanner(System.in);

        System.out.printf("Введите название: ");
        String title = scanner.nextLine();

        System.out.printf("Введите автора: ");
        String author = scanner.nextLine();

        if(title.length() < 3 || title.length() > 20 || author.length() < 3 || author.length() > 20) {
            throw new BookReadException();
        }

        int pages;
        try {
            System.out.printf("Введите количество страниц: ");
            pages = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            throw new BookReadException(e);
        }

        if(pages <= 0) {
            throw new BookReadException();
        }

        Book b = new Book(title, author, pages);
        for(int i=0; i<books.length; i++) {
            if(books[i] == null) {
                books[i] = b;
                return;
            }
        }

        throw new LibrarySpaceException(b);
    }

    @Override
    public String toString() {
        return "Library{" +
                "address='" + address + '\'' +
                ", books=" + Arrays.toString(books) +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }
}
