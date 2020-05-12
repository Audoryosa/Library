package com.audriuskumpis.library.publication;

import java.io.Serializable;
import java.util.Objects;

/**
 * Abstract Book class. Covers all basic logic of creating <code>Book</code> type objects
 */
public abstract class Book implements Publication, Comparable<Publication>, Serializable {

	private String title;
	private Author author;
	private int pages;
	private int isbn;
	private int releaseYear;
	private String anotation;

	private static final long serialVersionUID = 3L;

	public Book(String title, Author author, int pages, int isbn, int releaseYear) {
		Objects.requireNonNull(title, "title cannot be null");
		Objects.requireNonNull(author, "author cannot be null");
		Objects.requireNonNull(pages, "pages cannot be null");
		Objects.requireNonNull(isbn, "isbn cannot be null");
		Objects.requireNonNull(releaseYear, "releaseYear cannot be null");
		this.title = title;
		this.author = author;
		
		if(pages < 0) {
			this.pages = 0;
		} else {
			this.pages = pages;
		}
		this.isbn = isbn;
		
		if(releaseYear < 0) {
			this.releaseYear = 0;
		} else {
			this.releaseYear = releaseYear;
		}
	}

//	public abstract int getPrice();
	
	public void setAnotation(String text) {
		this.anotation = text;
	}

	public String getTitle() {
		return this.title;
	}

	public String getAnotation() {
		return this.anotation;
	}

	public int getReleaseYear() {
		return this.releaseYear;
	}

	public Author getAuthorOfBook() {
		return this.author;
	}

	public int getPages() {
		return this.pages;
	}

	public int getIsbn() {
		return this.isbn;
	}

	@Override
	public String toString() {

		return this.getClass().getSimpleName() + ":[title: " + this.title + ", author: " + this.author.getName() + " " + this.author.getSurname() + ", releaseYear: "
				+ this.releaseYear + ", pages: " + this.pages + "]";

	}

	@Override
	public int compareTo(Publication o) {
//		System.out.println("Entering commpareTo() method");

		if(this == o) {
			return 0;
		}

		if(o != null) {
			return this.getTitle().compareTo(o.getTitle());
		}

		throw new NullPointerException();
	}

	@Override
	public abstract String getDescription();

	@Override
	public int hashCode() {

		return this.getClass().hashCode() + this.getTitle().hashCode() + this.getAuthor().hashCode() + 57;
	}

	@Override
	public boolean equals(Object obj) {

		if(this == obj) {
			return true;
		}

		if((obj == null) || (this.getClass() != obj.getClass())) {
			return false;
		}

		Book book = (Book) obj;

		if((this.getTitle().equals(book.getTitle()) && (this.getAuthor().equals(book.getAuthor())))){
			return true;
		}
		return false;
	}

}
