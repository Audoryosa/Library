package com.audriuskumpis.library.publication;

import java.io.Serializable;

/**
 * Interface <code>Publication</code>
 *
 */

public interface Publication extends Comparable<Publication>, Serializable {
	long serialVersionUID = 3L;

	/**
	 *
	 * @return a description of book
	 */
	String getDescription();

	/**
	 *
	 * @return a title of publication
	 */
	String getTitle();

	/**
	 *
	 * @return an author object of publication
	 */
	Author getAuthor();

	/**
	 *
	 * @return page count of publication
	 */
	int getPages();

	/**
	 *
	 * @return ISBN number of publication
	 */
	int getIsbn();

	/**
	 *
	 * @return release year of publication
	 */
	int getReleaseYear();

	/**
	 *
	 * @return price of the publication
	 */
	float getPrice();
}
