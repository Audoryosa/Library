package com.audriuskumpis.library.publication;

import java.io.Serializable;
import java.util.Objects;

/**
 * An author of publications. Has two fields - name and surname.
 */
public class Author implements Serializable {

    private final String name;
    private final String surname;

    static final long serialVersionUID = 3L;

    public Author(String name, String surname){
        Objects.requireNonNull(name, "name cannot be null");
        Objects.requireNonNull(surname, "surname cannot be null");
        this.name = name;
        this.surname = surname;
    }

    public Author(String fullName) {
        this.name = fullName.trim();
        this.surname = "";
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }


    @Override
    public String toString() {
        if(!surname.equals("")){
            return this.name + " " + this.surname;
        } else {
            return this.name;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return name.equals(author.name) &&
                surname.equals(author.surname);
    }

    @Override
    public int hashCode() {
        return name.hashCode() + surname.hashCode() + 31;
    }
}
