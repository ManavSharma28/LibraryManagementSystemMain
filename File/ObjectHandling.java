package File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import BooksPackage.Book;

public class ObjectHandling {

        public void writeObjectToFile(Object obj, String filename) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename, true))) {
                oos.writeObject(obj);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
    public void deleteContent(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*public Book[] readObjectsFromFile(String filename) {
        Book[] objects = null;
         

         try {
             objects = new Book[100];
            ObjectInputStream ois= new ObjectInputStream(new FileInputStream(filename));
            int i=0;
            while (true) {
                try {
                    objects[i++]=(Book)ois.readObject();
                    
                } catch (EOFException eofe) {
                    System.out.println("End of file reached");
                    break;
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
 
        return objects;
    }*/

    public Object[] readObjectsFromFile(String filename) {
        Object[] objects = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            int numberOfObjects = ois.readInt(); // Read the number of objects stored
            objects = new Object[numberOfObjects];

            for (int i = 0; i < numberOfObjects; i++) {
                try {
                    Object obj = ois.readObject();
                    objects[i] = obj;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace(); // Handle class not found exception
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle IOException while opening ObjectInputStream
        }

        return objects;
    }
}





