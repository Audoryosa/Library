package com.audriuskumpis.library.service;

import com.audriuskumpis.library.exceptions.NoSuchPublicationException;
import com.audriuskumpis.library.publication.*;

import java.io.*;
import java.util.*;

class Library implements ILibrary, Serializable {

    private List<Publication> library;
    private List<Publication> shoppingCart;
    private List<LibraryObserver> observers = new ArrayList<>();
    private static final long serialVersionUID = 3L;

    Library(){
        library = new ArrayList<>();
        shoppingCart = new ArrayList<>();
        observers.add(new InsertionObserverWithFile());
        observers.add(new RemovalObserverWithFile());
    }

    /**
     * Return a list of shopping cart elements
     * @return an unmodifiable list of cart elements
     */
    public List<Publication> getShoppingCart() {
        return Collections.unmodifiableList(shoppingCart);
    }

    /**
     * Adds publication to library
     * @param publication publication to add
     */
    public void addPublication(Publication publication){
        library.add(publication);
        notifyInsertionObservers(publication.getTitle(), publication.getAuthor().toString());
    }

    /**
     * @return an unmodifiable list of library contents
     */
    public List<Publication> getList() {
        return Collections.unmodifiableList(library);
    }

    /**
     * Removes publication from library
     * @param author author of publication
     * @param title title of publication
     * @return book if it was removed
     * @throws NoSuchPublicationException if book with given parameters does not exist
     */
    public Publication removePublication(Author author, String title){
        Publication book = findPublication(author, title);
        if(book != null){
            library.remove(book);
            notifyRemovalObservers(title, author.toString());
            return book;
        } else {
            throw new NoSuchPublicationException();
        }
    }

    /**
     * Removes a given book from library
     * @param book to remove
     * @return book if success
     * @throws NoSuchPublicationException if specified book not found or is null
     */
    public Publication removePublication(Publication book){
        if(book != null){
            library.remove(book);
            notifyRemovalObservers(book.getTitle(), book.getAuthor().toString());
            return book;
        } else {
            throw new NoSuchPublicationException();
        }

    }

    /**
     * Finds publication in library
     * @param title title of publication to find
     * @return book with this title, null if not found
     */
    public Publication findPublication(String title) {
        for(Publication book : library){
            if(book.getTitle().equals(title)){
                return book;
            }
        }
        throw new NoSuchPublicationException();
    }

    /**
     * @return a string of library contents
     */
    public String getDisplay() {
        StringBuilder stringBuilder = new StringBuilder();

        for(Publication book : library){
            stringBuilder.append(book.getAuthor().toString() + ": \"" + book.getTitle() + "\"\n" );
        }
        return stringBuilder.toString();
    }

    /**
     *
     * @return a string of sorted library content
     */
    public String getSortedDisplay() {
        StringBuilder stringBuilder = new StringBuilder();
        Collections.sort(library, new Comparator<Publication>() {
            @Override
            public int compare(Publication o1, Publication o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });

        for(Publication book : library){
            stringBuilder.append(book.getAuthor().toString() + ": \"" + book.getTitle() + "\"\n" );
        }
        return stringBuilder.toString();
    }

    /**
     * Removes a book from cart
     * @param book to remove
     * @throws NoSuchPublicationException if book specified is not in the cart
     */
    @Override
    public void removeFromCart(Publication book) {
        if(shoppingCart.contains(book)){
            shoppingCart.remove(book);
        } else throw new NoSuchPublicationException();
    }

    /**
     * Saves whole this object using serialization (saves/data.dat)
     */
    public void saveAsSerialization() {
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(new File("saves/data.dat")))){
            os.writeObject(this);
        } catch (FileNotFoundException e){
            System.out.println("Could not create file: " + e.getMessage());
        } catch (IOException e){
            System.out.println("IO exception occurred: " + e.getMessage());
        }

    }

    /**
     * Saves library contents to CSV file (saves/data.csv)
     */
    public void saveAsCsv() {

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("saves/data.csv"))){
            for(Publication book : library){
                StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append("\"").append(book.getClass().getSimpleName()).append("\""); // type
                stringBuilder.append(","); // delimiter
                stringBuilder.append("\"").append(book.getTitle()).append("\""); // title
                stringBuilder.append(",");
                stringBuilder.append("\"").append(book.getAuthor().toString()).append("\""); // author
                stringBuilder.append(",");
                stringBuilder.append("\"").append(book.getPages()).append("\""); // pages
                stringBuilder.append(",");
                stringBuilder.append("\"").append(book.getIsbn()).append("\""); // isbn
                stringBuilder.append(",");
                stringBuilder.append("\"").append(book.getReleaseYear()).append("\""); // year
                stringBuilder.append("\n");

                bufferedWriter.write(stringBuilder.toString()); // creates a line of one publication information and puts it to file
            }
        } catch (IOException e){
            System.out.println("IOException occurred: " + e.getMessage());
        }
    }

    /**
     * Loads library's content from a CSV file (saves/data.csv)
     */
    public void loadListFromCsv() {
        List<Publication> list = new ArrayList<>();
        String line;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("saves/data.csv"))){
            while ((line = bufferedReader.readLine()) != null){
                String[] attributes = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                String[] formattedAttributes = formatAttributes(attributes);
                Publication book = createPublicationFromAttributes(formattedAttributes);
                list.add(book);
            }
        } catch (IOException e){
            System.out.println("IOException occurred: " + e.getMessage());
        }

        this.library = list;
    }

    /**
     * Returns amount of cash required to buy books
     * @return total amount of book prices in cart
     */
    public float getCartPrice() {
        float totalSum = 0.0F;
        for(Publication book : shoppingCart){
            totalSum += book.getPrice();
        }

        return totalSum;
    }

    /**
     * Adds a book to a shopping cart
     * @param book to add to cart
     * @throws NoSuchPublicationException if is trying to add a book that is not found in specified library
     */
    public void addToCart(Publication book) {
        if(book != null){
            shoppingCart.add(book);
        } else {
            throw new NoSuchPublicationException();
        }

    }

    private Publication createPublicationFromAttributes(String[] formattedAttributes) {
        PublicationTypes type = PublicationTypes.valueOf(formattedAttributes[0].toUpperCase());
        String title = formattedAttributes[1];
        Author author = new Author(formattedAttributes[2]);
        int pages = Integer.parseInt(formattedAttributes[3]);
        int isbn = Integer.parseInt(formattedAttributes[4]);
        int releaseYear = Integer.parseInt(formattedAttributes[5]);

        return PublicationFactory.create(type, title, author, pages, isbn, releaseYear);
    }

    private String[] formatAttributes(String[] attributes) {
         String[] newAttributes = new String[attributes.length];

         for(int i = 0; i < attributes.length; i++){
             newAttributes[i] = attributes[i].substring(1, attributes[i].length()-1);
         }

         return newAttributes;
    }

    private Publication findPublication(Author author, String title){
        for(Publication book : library){
            if(book.getTitle().equals(title) && book.getAuthor().equals(author)){
                return book;
            }
        }
        return null;
    }

    private void notifyInsertionObservers(String title, String author) {
        for(LibraryObserver observer : observers){
            if(observer instanceof InsertionObserver){
                observer.update(title, author);
            }
        }
    }

    private void notifyRemovalObservers(String title, String author) {
        for(LibraryObserver observer : observers){
            if(observer instanceof RemovalObserver){
                observer.update(title, author);
            }
        }
    }

}
