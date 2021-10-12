package ru.pa4ok.demoexam;

import java.util.Set;

public class Library
{
    private String address;
    private Set<Book> books;

    public Library(String address, Set<Book> books) {
        this.address = address;
        this.books = books;
    }

    public boolean hasBook(Book book) {
        return books.contains(book);
    }

    public Book addBook(String title, String author, int pages) {
        Book book = new Book(title, author, pages);
        return books.add(book) ? book : null;
    }

    public Book removeBook(String title, String author, int pages) {
        Book book = new Book(title, author, pages);
        return books.remove(book) ? book : null;
    }

    @Override
    public String toString() {
        return "Library{" +
                "address='" + address + '\'' +
                ", books=" + books +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
