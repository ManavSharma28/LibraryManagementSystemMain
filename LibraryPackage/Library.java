package LibraryPackage;


import BooksPackage.Book;

public class Library {
    public Book[] books;
    int numbooks;

    public Library(int max_books) {
        books = new Book[max_books];
        numbooks = 0;
    }

    public void count()
    {
        int count=0;
        for(int i=0;i<100;i++)
        {
            if(books[i]!=null)
            {
                count++;
            }
            else
            {
                break;
            }
        }
        numbooks=count;
    }

    public void addBook(Book book) {
       
        if (numbooks < books.length) {
            books[numbooks] = book;
            numbooks++;
        } else {
            System.out.println("Library has reached the maximum limit for books.");
        }
    }


    public Book findBook(int id) {
        for (int i = 0; i < numbooks; i++) {
            if (books[i].getBookid() == id) {
                return books[i];
            }
        }
        return null;
    }


    public Book findBook(String bookname) {
        for (int i = 0; i < numbooks; i++) {
            if (books[i].getName().equalsIgnoreCase(bookname)) {
                books[i].displayDetails();
                System.out.println("----------------");
            }
        }
        return null;
    }

    public void getAvailability(String bookname)
    {
        for(int i=0;i<100;i++)
        {
            if(books[i]!=null)
            {
            if(books[i].getName().equalsIgnoreCase(bookname))
            {
                if(books[i].isAvailable())
                {
                    System.out.println("Book ID : "+books[i].getBookid()+"\n"+"Availability : Available ");
                    System.out.println("----------------------");   
                }
                else
                {
                    System.out.println("Book ID : "+books[i].getBookid()+"\n"+"Availability : Not Available ");   
                    System.out.println("----------------------");  
                }
            }
        }
    }
    }

}