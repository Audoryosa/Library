package com.audriuskumpis.library.service;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Assures to update log.txt file on book insertion
 */
public class InsertionObserverWithFile implements InsertionObserver {
    @Override
    public void update(String title, String author) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("log.txt"), true))){
            bufferedWriter.write("Publication added: \"" + title + "\" by " + author + ", at " + formatter.format(now) + "\n");
        }catch (IOException e){
            System.out.println("IOException: " + e.getMessage());
        }

    }
}
