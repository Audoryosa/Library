package com.audriuskumpis.library.service;

import java.io.Serializable;

/**
 * Interface to mark observable insert event
 */
interface InsertionObserver extends Serializable {
    long serialVersionUID = 3L;
    void update(String title, String author);
}
