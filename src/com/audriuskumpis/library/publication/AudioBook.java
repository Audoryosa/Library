package com.audriuskumpis.library.publication;

/**
 *An implementation of audio book
 */
public class AudioBook extends Book implements Playable{

	public AudioBook(String title, Author author, int releaseYear) {
		super(title, author, 0, 0, releaseYear);
	}

	/**
	 * "Plays" the audio book
	 * @return short description of what is being played
	 */
	@Override
	public String playAudio() {
		return "Playing the track \"" + getTitle() + "\" by " + getAuthor().toString();

	}

	/**
	 *
	 * @return short description of audio book
	 */
	@Override
	public String getDescription() {
		return "Novelty book-of-no-pages, listen to it wherever you are.";
	}

	@Override
	public Author getAuthor() {
		return this.getAuthorOfBook();
	}

	@Override
	public float getPrice() {
		return getReleaseYear() * 0.02F;
	}


	@Override
	public int hashCode() {
		return (int) (this.getClass().hashCode() + this.getTitle().hashCode() + this.getAuthor().hashCode() + 31);
	}

	@Override
	public boolean equals(Object obj) {
		
		if(this == obj) {
			return true;
		}

		if((obj == null) || (this.getClass() != obj.getClass())) {
			return false;
		}

		AudioBook book = (AudioBook) obj;

		if((this.getTitle().equals(book.getTitle()) && this.getAuthor().equals(book.getAuthor()))){
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ":[title=" + this.getTitle() + ", author=" + this.getAuthor() + "]";
	}

	@Override
	public int compareTo(Publication o) {

		if(this == o) {
			return 0;
		}

//		if(o != null) {
			return this.getTitle().compareTo(o.getTitle());
//		}

//		throw new NullPointerException();
	}


}
