package com.audriuskumpis.library.service;

import java.io.Serializable;

/**
 * Interface to implement library observing
 */
interface LibraryObserver extends Serializable {
    long serialVersionUID = 3L;

    void update(String title, String author);
}
