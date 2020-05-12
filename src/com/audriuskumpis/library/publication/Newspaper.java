package com.audriuskumpis.library.publication;

/**
 * Implementation of newspaper
 */

public class Newspaper implements Publication{
	private final String title;
	private final int releaseNumber;
	private final Author author;

	@Override
	public String getDescription() {

		return "A daily paper which covers latest local and world events.";
	}

	public Newspaper(String title, Author author, int releaseNumber) {
		this.title = title;
		this.author = author;
		if(releaseNumber < 0) {
			this.releaseNumber = 1;
		} else {
			this.releaseNumber = releaseNumber;
		}
	}

	public String getTitle() {

		return title;
	}

	public int getReleaseNumber() {

		return releaseNumber;
	}

	@Override
	public int hashCode() {
		return this.getClass().hashCode() + this.getTitle().hashCode() + this.getReleaseNumber() + 31;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}

		if((obj == null) || (this.getClass() != obj.getClass())) {
			return false;
		}

		Newspaper paper = (Newspaper) obj;

		if((this.getTitle().equals(paper.getTitle()) && (this.getReleaseNumber() == paper.getReleaseNumber()))){
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ":[title=" + this.title + ", releaseNumber=" + this.releaseNumber + "]";
	}

	@Override
	public Author getAuthor() {
		return author;
	}

	@Override
	public int getPages() {
		return 0;
	}

	@Override
	public int getIsbn() {
		return 0;
	}

	@Override
	public int getReleaseYear() {
		return 0;
	}

	@Override
	public float getPrice() {
		return 1.00F;
	}

	@Override
	public int compareTo(Publication o) {

		if(this == o) {
			return 0;
		}

		if(o != null) {
			return this.getTitle().compareTo(o.getTitle());
		}

		throw new NullPointerException();
	}

}
