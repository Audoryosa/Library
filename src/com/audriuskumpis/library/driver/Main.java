package com.audriuskumpis.library.driver;

import com.audriuskumpis.library.publication.Author;
import com.audriuskumpis.library.publication.Publication;
import com.audriuskumpis.library.publication.PublicationTypes;
import com.audriuskumpis.library.service.ILibrary;
import com.audriuskumpis.library.service.LibraryFactory;
import com.audriuskumpis.library.service.PublicationFactory;

class Main {

    private static ILibrary library = LibraryFactory.getInstance();

    public static void main(String[] args) {
//        loadSer();
//        printAllList();

        Publication book = new PublicationFactory()
                .setTitle("Title")
                .setAuthor(new Author("Name", "Surname"))
                .setType(PublicationTypes.ENCYCLOPEDIA)
                .setPages(999)
                .setIsbn(1)
                .setReleaseYear(2020)
                .build();

        library.addPublication(book);

//       library.addPublication(PublicationFactory.create(PublicationTypes.ENCYCLOPEDIA, "Big Book of British Smiles", new Author("Dentist", "Guy"), 123, 5555, 2020));
//       library.addPublication(PublicationFactory.create(PublicationTypes.TEXTBOOK, "Some old book, called \"A BAD book\"", new Author("Some", "Guy"), 123, 5555, 2020));
//       library.removePublication(new Author("Some", "Guy"), "Some new book, called \"A BAD book\"");
    }

    private static void saveCSV(){
        library.saveAsCsv();
    }

    private static void loadCSV(){
        library.loadListFromCsv();
    }

    private static void saveSer(){
        library.saveAsSerialization();
    }

    private static void loadSer(){
        library = LibraryFactory.loadSerializedObject();
    }

    private static void printAllList(){
        for(Publication book : library.getList()){
            System.out.println("Type: " + book.getClass().getSimpleName() +
                    ", title: " + book.getTitle() +
                    ", author: " + book.getAuthor()/* +
                    ", pages: " + book.getPages() +
                    ", ISBN: " + book.getIsbn() +
                    ", release year: " + book.getReleaseYear()*/);
        }
    }
}
