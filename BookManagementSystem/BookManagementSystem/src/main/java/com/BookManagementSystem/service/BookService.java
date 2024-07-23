package com.BookManagementSystem.service;

import java.util.List;

import com.BookManagementSystem.model.Book;

public interface BookService2 {
    List<Book> getBooks();
    Book getBookById(int id);
    Book saveBook(Book book);
    String deleteBook(int id);
    Book updateMyBook(int id, Book book);
    List<Book> findBooksByTitle(String title);
    List<Book> findBooksByAuthor(String author);
}
