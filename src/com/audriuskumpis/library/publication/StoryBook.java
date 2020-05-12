package com.audriuskumpis.library.publication;

/**
 * Implementation of story book
 */
public class StoryBook extends Book {

    public StoryBook(String title, Author author, int pages, int isbn, int releaseYear) {
        super(title, author, pages, isbn, releaseYear);
    }

    @Override
    public String getDescription() {
        return "A book telling an interesting story.";
    }

    @Override
    public Author getAuthor() {
        return this.getAuthorOfBook();
    }

    @Override
    public float getPrice() {
        return getPages() * 0.2F;
    }
}
