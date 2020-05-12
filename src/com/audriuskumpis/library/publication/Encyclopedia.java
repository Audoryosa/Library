package com.audriuskumpis.library.publication;

/**
 * An implementation of encyclopedia
 */
public class Encyclopedia extends Book{
	private String topic;

	public Encyclopedia(String title, Author author, int pages, int isbn, int releaseYear) {
		super(title, author, pages, isbn, releaseYear);
	}

	@Override
	public String getDescription() {
		return "Thick and informative book to all the curious people out there";
	}

	@Override
	public Author getAuthor() {
		return this.getAuthorOfBook();
	}

	@Override
	public float getPrice() {
		return getPages() * 0.06F;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	


}
