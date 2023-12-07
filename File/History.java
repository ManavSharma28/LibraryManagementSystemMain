package File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
    
public class History {
    
    public void addBorrowHistory(int userid,String bookname,int bookid) 
    {
        String fileName = "History.txt";
    
        String Data = "User_id : "+userid+" borrowed bookname : "+bookname+" with book_id : "+bookid;
    
            try {
                
                FileWriter fw = new FileWriter(fileName, true);
    
                BufferedWriter bw = new BufferedWriter(fw);
    
                bw.write(Data);
                bw.newLine();     
                bw.close();
    
            } catch (IOException e) {
                System.out.println("Error in writing to the file: " + e.getMessage());
            }
        }

        public void addReturnHistory(int userid,String bookname,int bookid) 
    {
        String fileName = "History.txt";
    
        String Data = "User_id : "+userid+" returned bookname : "+bookname+" with book_id : "+bookid;
    
            try {
                
                FileWriter fw = new FileWriter(fileName, true);
    
                BufferedWriter bw = new BufferedWriter(fw);
    
                bw.write(Data);
                bw.newLine();     
                bw.close();
    
            } catch (IOException e) {
                System.out.println("Error in writing to the file: " + e.getMessage());
            }
        }
    }
        
