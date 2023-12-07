package MainPackage;

import BooksPackage.Book;
import File.FileRead;
import File.History;
import LibraryPackage.Library;
import jdbc_connect.DBConnect;

import java.util.InputMismatchException;
import java.util.Scanner;

class LibraryManagementSystem {
    public static void main(String args[]) {
        Library lib = new Library(100);
        DBConnect db = new DBConnect();
        FileRead fr= new FileRead();
        History his=new History();
        Scanner sc = new Scanner(System.in);

        lib.books = db.retrieveBooks();
        lib.count();

        boolean a;
        boolean k;
        boolean z;


        k = true;
        while (k) {
            fr.MenuRead("LoginMenu.txt");

            int ch1 = 0;
            boolean InputCheck = false;
            while (!InputCheck) {
                try {
                    System.out.print("Enter choice : ");
                    ch1 = sc.nextInt();
                    InputCheck = true;
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter an integer.");
                    sc.nextLine();
                }
            }

            switch (ch1) {
                case 1:
                    int adminid = 0;
                    InputCheck = false;
                    while (!InputCheck) {
                        try {
                            System.out.print("Enter administrator id : ");
                            adminid = sc.nextInt();
                            InputCheck = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter an integer.");
                            sc.nextLine();
                        }
                    }

                    int adminpass = 0;
                    InputCheck = false;
                    while (!InputCheck) {
                        try {
                            System.out.print("Enter administrator password : ");
                            adminpass = sc.nextInt();
                            InputCheck = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter an integer.");
                            sc.nextLine();
                        }
                    }


                    if (db.adminLogin(adminid, adminpass)) {
                        System.out.println("Admin  logged in successfully.");
                        a = true;
                        while (a) {
                            fr.MenuRead("AdministratorMenu.txt");

                            int choice = 0;
                            InputCheck = false;
                            while (!InputCheck) {
                                try {
                                    System.out.print("Enter your choice : ");
                                    choice = sc.nextInt();
                                    sc.nextLine();
                                    InputCheck = true;
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input! Please enter an integer.");
                                    sc.nextLine();
                                }
                            }

                            System.out.println("\n");

                            switch (choice) {
                                case 1:
                                    int bookId = 0;
                                    InputCheck = false;
                                    while (!InputCheck) {
                                        try {
                                            System.out.print("Enter book ID : ");
                                            bookId = sc.nextInt();
                                            InputCheck = true;
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid input! Please enter an integer.");
                                            sc.nextLine();
                                        }
                                    }


                                    int t = 0;

                                    for (Book book: lib.books) {
                                        if (book != null) {
                                            if (book.getBookid() == bookId) {
                                                t = 1;
                                            }
                                        }
                                    }

                                    if (t == 1) {
                                        System.out.println("Book with the same id exist. Add the book with different id.");
                                    } else {
                                        System.out.print("Enter book title : ");
                                        sc.nextLine();
                                        String bookname = sc.nextLine();
                                        System.out.print("Enter book author : ");
                                        String bookauthor = sc.nextLine();
                                        lib.addBook(new Book(bookname, bookauthor, bookId, true));
                                        System.out.println("Book added successfully in the library." + "\n");
                                    }
                                    break;


                                case 2:
                                    int userid = 0;
                                    InputCheck = false;
                                    while (!InputCheck) {
                                        try {
                                            System.out.print("Enter user ID : ");
                                            userid = sc.nextInt();
                                            sc.nextLine();
                                            InputCheck = true;
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid input! Please enter an integer.");
                                            sc.nextLine();
                                        }
                                    }


                                    if (db.useridCheck(userid)) {
                                        System.out.print("Enter user name : ");
                                        String username = sc.nextLine();

                                        int pass = 0;
                                        InputCheck = false;
                                        while (!InputCheck) {
                                            try {
                                                System.out.print("Enter user password : ");
                                                pass = sc.nextInt();
                                                InputCheck = true;
                                            } catch (InputMismatchException e) {
                                                System.out.println("Invalid input! Please enter an integer.");
                                                sc.nextLine();
                                            }
                                        }


                                        db.addUsertoDB(userid, username, pass);
                                        System.out.println("User registered successfully in the library." + "\n");
                                    } else {
                                        System.out.println("User with the same id exist. Add the user with different id.");
                                    }
                                    break;


                                case 3:
                                    System.out.println("Details for all users of library : ");
                                    db.displayUsers();
                                    break;


                                case 4:
                                    boolean m;
                                    m = true;
                                    while (m) {
                                        fr.MenuRead("SearchMenu.txt");

                                        int ch2 = 0;
                                        InputCheck = false;
                                        while (!InputCheck) {
                                            try {
                                                System.out.print("Enter choice : ");
                                                ch2 = sc.nextInt();
                                                InputCheck = true;
                                            } catch (InputMismatchException e) {
                                                System.out.println("Invalid input! Please enter an integer.");
                                                sc.nextLine();
                                            }
                                        }

                                        switch (ch2) {
                                            case 1:
                                                int userinfoid = 0;
                                                InputCheck = false;
                                                while (!InputCheck) {
                                                    try {
                                                        System.out.print("Enter user ID: ");
                                                        userinfoid = sc.nextInt();
                                                        InputCheck = true;
                                                    } catch (InputMismatchException e) {
                                                        System.out.println("Invalid input! Please enter an integer.");
                                                        sc.nextLine();
                                                    }
                                                }


                                                if (db.useridCheck(userinfoid)) {
                                                    System.out.println("User not found in the library." + "\n");
                                                } else {
                                                    db.displayDetails(userinfoid);
                                                }
                                                break;

                                            case 2:
                                                sc.nextLine();
                                                System.out.print("Enter user name : ");
                                                String userinfoname = sc.nextLine();
                                                db.displayDetails(userinfoname);
                                                break;


                                            case 0:
                                                m = false;
                                                break;

                                            default:
                                                System.out.println("Invalid choice.");
                                                break;
                                        }
                                    }
                                    break;

                                case 5:
                                    System.out.println("Details for all books of library : ");
                                    for (Book book: lib.books) {
                                        if (book != null) {
                                            book.displayDetails();
                                            System.out.println("--------------------");
                                        }
                                    }
                                    break;


                                case 6:
                                    boolean c;
                                    c = true;
                                    while (c) {
                                        fr.MenuRead("SearchMenu.txt");

                                        int ch2 = 0;
                                        InputCheck = false;
                                        while (!InputCheck) {
                                            try {
                                                System.out.print("Enter choice : ");
                                                ch2 = sc.nextInt();
                                                InputCheck = true;
                                            } catch (InputMismatchException e) {
                                                System.out.println("Invalid input! Please enter an integer.");
                                                sc.nextLine();
                                            }
                                        }


                                        switch (ch2) {
                                            case 1:
                                                int bookinfoid = 0;
                                                InputCheck = false;
                                                while (!InputCheck) {
                                                    try {
                                                        System.out.print("Enter user ID: ");
                                                        bookinfoid = sc.nextInt();
                                                        InputCheck = true;
                                                    } catch (InputMismatchException e) {
                                                        System.out.println("Invalid input! Please enter an integer.");
                                                        sc.nextLine();
                                                    }
                                                }
                                                Book bookInfo = lib.findBook(bookinfoid);
                                                if (bookInfo == null) {
                                                    System.out.println("Book not found in the library." + "\n");
                                                } else {
                                                    bookInfo.displayDetails();
                                                }
                                                break;

                                            case 2:
                                                sc.nextLine();
                                                System.out.print("Enter book name : ");
                                                String bookinfoname = sc.nextLine();

                                                lib.findBook(bookinfoname);
                                                break;


                                            case 0:
                                                c = false;
                                                break;

                                            default:
                                                System.out.println("Invalid choice.");
                                                break;
                                        }
                                    }
                                    break;


                                case 0:
                                    a = false;
                                    System.out.println("Administrator logged out.");
                                    break;



                                default:

                            }
                        }
                    } else {
                        System.out.println("Login failed.");
                    }
                    break;


                case 2:
                    int user_id = 0;
                    InputCheck = false;
                    while (!InputCheck) {
                        try {
                            System.out.print("Enter user ID : ");
                            user_id = sc.nextInt();
                            InputCheck = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter an integer.");
                            sc.nextLine();
                        }
                    }

                    int password = 0;
                    InputCheck = false;
                    while (!InputCheck) {
                        try {
                            System.out.print("Enter login password : ");
                            password = sc.nextInt();
                            InputCheck = true;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input! Please enter an integer.");
                            sc.nextLine();
                        }
                    }



                    if (db.userLogin(user_id, password)) {
                        System.out.print("User  logged in successfully." + "\n");
                        z = true;

                        while (z) {
                            fr.MenuRead("UserMenu.txt");

                            int ch = 0;
                            InputCheck = false;
                            while (!InputCheck) {
                                try {
                                    System.out.print("Enter choice : ");
                                    ch = sc.nextInt();
                                    InputCheck = true;
                                } catch (InputMismatchException e) {
                                    System.out.println("Invalid input! Please enter an integer.");
                                    sc.nextLine();
                                }
                            }

                            switch (ch) {
                                case 1:

                                    int bookidtoborrow = 0;
                                    InputCheck = false;
                                    while (!InputCheck) {
                                        try {
                                            System.out.print("Enter book Id to be borrowed : ");
                                            bookidtoborrow = sc.nextInt();
                                            InputCheck = true;
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid input! Please enter an integer.");
                                            sc.nextLine();
                                        }
                                    }

                                    Book bookToBorrow = lib.findBook(bookidtoborrow);
                                    if (bookToBorrow == null) {
                                        System.out.println("Book not found in the library." + "\n");
                                    } else if (!bookToBorrow.isAvailable()) {
                                        System.out.println("Book is not available for borrowing." + "\n");
                                    } else {
                                        db.borrowBook(user_id, bookToBorrow);
                                        his.addBorrowHistory(user_id, bookToBorrow.getName(), bookidtoborrow);
                                        System.out.println("Book borrowed successfully." + "\n");
                                    }
                                    break;


                                case 2:
                                    int bookidtoreturn = 0;
                                    InputCheck = false;
                                    while (!InputCheck) {
                                        try {
                                            System.out.print("Enter book Id to be returned : ");
                                            bookidtoreturn = sc.nextInt();
                                            InputCheck = true;
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid input! Please enter an integer.");
                                            sc.nextLine();
                                        }
                                    }
                                    Book bookToReturn = lib.findBook(bookidtoreturn);

                                    if (bookToReturn == null) {
                                        System.out.println("This book is not from this library." + "\n");
                                    } else if (db.ifBorrowed(user_id, bookidtoreturn)) {
                                        System.out.println("User didnt borrowed this book." + "\n");
                                    } else {
                                        int fine=db.calcFine(user_id, bookidtoreturn);
                                        db.returnBook(user_id, bookidtoreturn,bookToReturn);
                                        db.updateFine(fine,user_id);
                                        his.addReturnHistory(user_id, bookToReturn.getName(), bookidtoreturn);
                                        System.out.println("Book returned successfully with Rs."+fine+" fine." + "\n");
                                    }
                                    break;



                                case 3:
                                    db.returnBorrowedbooks(user_id);
                                    break;



                                case 4:
                                    System.out.println("User details are : ");
                                    db.displayDetails(user_id);
                                    break;

                                
                                case 5:
                                    int fine=db.getFine(user_id);
                                    System.out.println("Pending Fine : "+fine); 
                                    int amt=0;
                                    InputCheck = false;
                                    while (!InputCheck) {
                                        try {
                                            System.out.print("Enter amount to be paid : ");
                                            amt = sc.nextInt();
                                            InputCheck = true;
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid input! Please enter an integer.");
                                            sc.nextLine();
                                        }
                                    }
                                    if(amt > fine)
                                    {
                                        System.out.println("The amount entered is more than pending fine. Please try again."); 
                                        break;
                                    }
                                    else
                                    {
                                        db.payFine(amt,user_id);
                                    }       
                                break;



                                case 6:
                                    int oldpass = 0;
                                    InputCheck = false;
                                    while (!InputCheck) {
                                        try {
                                            System.out.print("Enter old password : ");
                                            oldpass = sc.nextInt();
                                            InputCheck = true;
                                        } catch (InputMismatchException e) {
                                            System.out.println("Invalid input! Please enter an integer.");
                                            sc.nextLine();
                                        }
                                    }


                                    if (db.passCheck(user_id, oldpass)) {
                                        int newpass = 0;
                                        InputCheck = false;
                                        while (!InputCheck) {
                                            try {
                                                System.out.print("Enter new password : ");
                                                newpass = sc.nextInt();
                                                InputCheck = true;
                                            } catch (InputMismatchException e) {
                                                System.out.println("Invalid input! Please enter an integer.");
                                                sc.nextLine();
                                            }
                                        }


                                        db.changePassword(user_id, newpass);
                                        System.out.println("Your password changed successfully.");
                                    } else {
                                        System.out.println("Wrong password entered.");
                                    }
                                    break;


                                case 7:
                                    System.out.print("Enter bookname : ");
                                    sc.nextLine();
                                    String bookavailcheck=sc.nextLine();
                                    lib.getAvailability(bookavailcheck);
                                    break;

                                case 0:
                                    z = false;
                                    System.out.println("User logged out.");
                                    break;


                                default:
                                    System.out.println("Invalid choice.");
                            }
                        }
                    } else {
                        System.out.println("User login failed." + "\n");
                    }
                    break;


                case 0:
                    k = false;
                    db.truncateDB();
                    for (int d = 0; d < 100; d++) {
                        if (lib.books[d] != null) {
                            db.sendBookData(lib.books[d]);
                        } else {
                            break;
                        }
                    }
                    System.out.println("Thank you for your presence in library.");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}