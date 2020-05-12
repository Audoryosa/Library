package com.audriuskumpis.library.exceptions;

/**
 * Is thrown if publication type is not supported by library.
 */
public class NoSuchPublicationException extends RuntimeException {

    public NoSuchPublicationException(){
        super("No such publication available");
    }
}
