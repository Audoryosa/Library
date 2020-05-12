package com.audriuskumpis.library.publication;

/**
 * Implementation of textbook
 */

public class Textbook extends Book{

	public Textbook(String title, Author author, int pages, int isbn, int releaseYear) {
		super(title, author, pages, isbn, releaseYear);
	}

	@Override
	public String getDescription() {
		return "Textbook to learn from. Usually taken by college students or by depressed lonely housewives";
	}

	@Override
	public Author getAuthor() {
		return this.getAuthorOfBook();
	}

	@Override
	public float getPrice() {
		return getPages() * 0.3F;
	}
}
