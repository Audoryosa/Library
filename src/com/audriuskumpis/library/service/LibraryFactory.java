package com.audriuskumpis.library.service;

import java.io.*;

/**
 * A factory class to get Library object. Returns a singleton instance of Library
 */
public class LibraryFactory {

    private static ILibrary library = new Library();

    /**
     * Initializes library service
     * @return a singleton library service object
     */
    public static ILibrary getInstance() {
        if(library != null){
            return library;
        } else {
            return new Library();
        }
    }

    /**
     * Loads library service from a serialized file
     * @return library if success, null otherwise
     */
    public static ILibrary loadSerializedObject() {
        ILibrary libraryToLoad;

        try(ObjectInputStream oi = new ObjectInputStream(new FileInputStream(new File("saves/data.dat")))){
            libraryToLoad = (ILibrary) oi.readObject();
            return libraryToLoad;
        } catch (FileNotFoundException e){
            System.out.println("No saves were found");
        } catch (IOException e){
            System.out.println("IOException: " + e.getMessage());
        } catch (ClassNotFoundException e){
            System.out.println("Class not found: " + e.getMessage());
        }

        return null;
    }

}
