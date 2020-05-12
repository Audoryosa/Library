package com.audriuskumpis.library.service;

import com.audriuskumpis.library.publication.Author;
import com.audriuskumpis.library.publication.Publication;

import java.util.List;

/**
 * Provides an interface that libraries have
 */
public interface ILibrary {
    /**
     * Add a publication element to library
     * @param publication publication to add
     */
    void addPublication(Publication publication);

    /**
     * Returns a list of library content
     * @return list of Publications
     */
    List<Publication> getList();

    /**
     * To retrieve a publication from library
     * @param author author of publication
     * @param title title of publication
     * @return a publication with given parameters
     */
    Publication removePublication(Author author, String title);

    /**
     * Removes a given book
     * @param book to remove
     * @return specified book if success
     */
    Publication removePublication(Publication book);

    /**
     * Fins a publication in library
     * @param title title of publication to find
     * @return a publication with given title, null if not found
     */
    Publication findPublication(String title);

    /**
     * Returns a list view of library contents
     * @return library contents as a String
     */
    String getDisplay();

    /**
     * Returns an ordered list view of library contents
     * @return ordered list view of library contents as a String
     */
    String getSortedDisplay();

    /**
     * Saves current library work using java serialization mechanism
     */
    void saveAsSerialization();

    /**
     * Save library content as CSV file
     */
    void saveAsCsv();

    /**
     * Load library content from CSV file
     */
    void loadListFromCsv();

    /**
     *
     * @return amount of how much to pay for books to buy in the cart
     */
    float getCartPrice();

    /**
     * Adds a selected publication to a shopping cart
     */
    void addToCart(Publication book);

    /**
     * Removes item from a shopping cart
     */
    void removeFromCart(Publication book);

}
