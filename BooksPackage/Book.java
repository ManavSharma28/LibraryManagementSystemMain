package BooksPackage;
import DisplayPackage.Display;
import GetNamePackage.GetName;

public class Book extends GetName implements Display {
    String bookname;
    String author;
    boolean avail;
    int book_id;

    public Book(String bookname, String author, int book_id, boolean avail) {
        this.bookname = bookname;
        this.author = author;
        if(avail)
        {
            this.avail = true;
        }
        else
        {
            this.avail = false;
        }
        this.book_id = book_id;
    }

    public String getName() {
        return bookname;
    }

    public int getBookid() {
        return book_id;
    }

    public String getAuthor()
    {
        return author;
    }

    public boolean isAvailable() {
        return avail;
    }

    public void borrow() {
        avail = false;
    }

    public void returnBook() {
        avail = true;
    }

    static String availability(boolean avail) {
        String x;
        if (avail) {
            x = "Yes";
        } else {
            x = "No";
        }
        return x;
    }

    public void displayDetails() {
        System.out.println("Book ID : " + book_id);
        System.out.println("Book Title : " + bookname);
        System.out.println("Book Author : " + author);
        System.out.println("Available : " + availability(isAvailable())+"\n");
    }
}