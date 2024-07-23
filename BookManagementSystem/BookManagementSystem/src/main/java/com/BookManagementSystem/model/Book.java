package com.BookManagementSystem.model;

public class Book {
    private int id;
    private String title;
    private String author;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        // ID should not be updated once created
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}