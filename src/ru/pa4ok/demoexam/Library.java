package ru.pa4ok.demoexam;

import java.util.Arrays;

public class Library
{
    private String address;
    private Book[] books;

    public Library(String address, Book[] books) {
        this.address = address;
        this.books = books;
    }

    public boolean addBook(Book book)
    {
        for(int i=0; i<books.length; i++) {
            if(books[i] == null) {
                books[i] = book;
                return true;
            }
        }
        return false;
    }

    public boolean removeBook(String title, String author)
    {
        for(int i=0; i<books.length; i++) {
            if(books[i] != null && books[i].getTitle().equals(title) && books[i].getAuthor().equals(author)) {
                books[i] = null;
                return true;
            }
        }
        return false;
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
