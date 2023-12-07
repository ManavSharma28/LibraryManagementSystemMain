package File;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreation {


    public static void main(String[] args) {


        try {
            FileWriter fileWriter = new FileWriter("LoginMenu.txt");
            BufferedWriter bw = new BufferedWriter(fileWriter);

            bw.write("***************************"+"\n");
            bw.write("* Welcome to the library. *"+"\n");
            bw.write("*                         *"+"\n");
            bw.write("* 1. Administrator login  *"+"\n");
            bw.write("* 2. User login           *"+"\n");
            bw.write("* 0. Exit code            *"+"\n");
            bw.write("*                         *"+"\n");            
            bw.write("***************************"+"\n");
            bw.close();
            
            FileWriter fileWriter1 = new FileWriter("AdministratorMenu.txt");
            BufferedWriter bw1 = new BufferedWriter(fileWriter1);

            bw1.write("*******************************************"+"\n");
            bw1.write("*  Library Management System Menu:        *"+"\n");
            bw1.write("*                                         *"+"\n");
            bw1.write("*  Administrator Functions :              *"+"\n");
            bw1.write("*                                         *"+"\n");
            bw1.write("*  1. Add Book                            *"+"\n");
            bw1.write("*  2. Add User                            *"+"\n");
            bw1.write("*  3. Display all users                   *"+"\n");
            bw1.write("*  4. Display particular user information *"+"\n");
            bw1.write("*  5. Display all books                   *"+"\n");
            bw1.write("*  6. Display particular book information *"+"\n");
            bw1.write("*  0. Log out                             *"+"\n");
            bw1.write("*                                         *"+"\n");
            bw1.write("*******************************************"+"\n");    
            bw1.close();

            FileWriter fileWriter2 = new FileWriter("SearchMenu.txt");
            BufferedWriter bw2 = new BufferedWriter(fileWriter2);

            bw2.write("************************"+"\n");
            bw2.write("*                      *"+"\n");
            bw2.write("* 1. Search by Id      *"+"\n");
            bw2.write("* 2. Search by name    *"+"\n");
            bw2.write("* 0. Exit search menu  *"+"\n");
            bw2.write("*                      *"+"\n");
            bw2.write("************************"+"\n");
            bw2.close();

            FileWriter fileWriter3 = new FileWriter("UserMenu.txt");
            BufferedWriter bw3 = new BufferedWriter(fileWriter3);

            bw3.write("********************************"+"\n");
            bw3.write("*  User Functions :            *"+"\n");
            bw3.write("*                              *"+"\n");
            bw3.write("*  1. Borrow Book              *"+"\n");
            bw3.write("*  2. Return Book              *"+"\n");
            bw3.write("*  3. My borrowed books        *"+"\n");            
            bw3.write("*  4. My details               *"+"\n");
            bw3.write("*  5. Pay Fine                 *"+"\n");
            bw3.write("*  6. Change password          *"+"\n");
            bw3.write("*  7. Check book Availability  *"+"\n");
            bw3.write("*  0. Log out                  *"+"\n");
            bw3.write("*                              *"+"\n");
            bw3.write("********************************"+"\n");       
            bw3.close();     

        } catch (IOException e) {
           System.out.println("Error writing to the file: " + e.getMessage());
        }
    }
}

