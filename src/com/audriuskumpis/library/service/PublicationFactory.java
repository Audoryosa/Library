package com.audriuskumpis.library.service;

import com.audriuskumpis.library.publication.*;

/**
 * Factory class to create types of publications. This class implements two ways of creatig publications: 1. With static factory method <code>PublicationFactory.create()</code>
 * 2. Using <code>build()</code> and setter methods.
 * @see PublicationTypes
 */
public class PublicationFactory {
    private PublicationTypes type = PublicationTypes.TEXTBOOK;
    private String title = "Default Title";
    private Author author = new Author("Default Author");
    private int pages = 10;
    private int isbn = 1;
    private int releaseYear = 2020;

    public PublicationFactory(){

    }

    /**
     * The following methods are basic setters that are used to build publication object
     */
    public PublicationFactory setType(PublicationTypes type){
        this.type = type;
        return this;
    }

    public PublicationFactory setTitle(String title){
        this.title = title;
        return this;
    }

    public PublicationFactory setAuthor(Author author){
        this.author = author;
        return this;
    }

    public PublicationFactory setPages(int pages){
        this.pages = pages;
        return this;
    }

    public PublicationFactory setIsbn(int isbn){
        this.isbn = isbn;
        return this;
    }

    public PublicationFactory setReleaseYear(int releaseYear){
        this.releaseYear = releaseYear;
        return this;
    }

    /**
     * This method builds a publication that was created using setter methods.
     * @return a publication with set methods.
     */
    public Publication build(){
        return create(type, title, author, pages, isbn, releaseYear);
    }

    /**
     * Creates a publication object by given parameters
     * Example: <code>library.addPublication(PublicationFactory.create(PublicationTypes.TEXTBOOK, "A title", new Author("Name", "Surname"), 97, 123456, 2020));</code>
     * @param publicationType enum of publication types
     * @param title of the book
     * @param author of the book
     * @param pages of book
     * @param isbn of book
     * @param releaseYear of book
     * @return a new instance of publication created. Null if something went wrong
     */
    public static Publication create(PublicationTypes publicationType, String title, Author author, int pages, int isbn, int releaseYear){
        switch (publicationType){
            case AUDIOBOOK:
                return new AudioBook(title, author, releaseYear);
            case ENCYCLOPEDIA:
                return new Encyclopedia(title, author, pages, isbn, releaseYear);
            case NEWSPAPER:
                return new Newspaper(title, author, isbn);
            case STORYBOOK:
                return new StoryBook(title, author, pages, isbn, releaseYear);
            case TEXTBOOK:
                return new Textbook(title, author, pages, isbn, releaseYear);
            default: // this should never happen
                return null;
        }

    }
}
