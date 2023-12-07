package jdbc_connect;
import BooksPackage.Book;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

public class DBConnect
{
	PreparedStatement ps;
	PreparedStatement ps1;
	Statement s;
	Connection c;
	ResultSet rs = null;
	ResultSet rs1 = null;
	public DBConnect()
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			c=DriverManager.getConnection("jdbc:mysql://localhost/user_java","root","Manav@2004");
			s=c.createStatement();
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Exception : "+e);
		}
		catch(SQLException e)
		{
			System.out.println("Exception : "+e);
		}
	}

	

	public Book[] retrieveBooks()
	{
		try 
		{
            String sql = "select * from books";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
			boolean x;
			int i=0;
			Book[] arr=new Book[100];

            while (rs.next()) 
			{
                int id = rs.getInt("book_id");
                String name = rs.getString("bookname");
				String author = rs.getString("author");
				String avail = rs.getString("book_avail");
				
				String temp="Yes";
			if(avail.equals(temp))
			{
				x=true;
			}
			else
			{
				x=false;
			}

			Book book=new Book(name, author, id, x);
			
			arr[i++]=book;	
            }
			return arr;
        }

        catch (SQLException e) 
		{
            System.out.println(e);
        }
		return null;
	}

	public void addUsertoDB(int id, String name, int pass)
	{
		try
		{
			ps=c.prepareStatement("insert into users(user_id,username,user_password) values(?,?,?)");
			ps.setInt(1,id);
			ps.setString(2,name);
			ps.setInt(3,pass);	
			ps.executeUpdate();
	    }
		catch(SQLException e)
		{
			System.out.println("Exception : "+e);
		}
	}


	public void displayUsers()
	{
		try 
		{
            String sql = "select * from users";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
 
            while (rs.next()) 
			{
                int id = rs.getInt("user_id");
                String name = rs.getString("username");
				int fine=rs.getInt("Fine");

				System.out.println("User ID : "+id);
				System.out.println("User Name : "+name);
                System.out.println("Pending Fine : "+fine); 

				String sql1 = "select * from borrowedbooks";
				ps1 = c.prepareStatement(sql1);
				rs1 = ps1.executeQuery();
				System.out.println("Borrowed books are :");
            	while (rs1.next()) 
				{
                	int id1 = rs1.getInt("user_id");
                	String name1 = rs1.getString("bookname");
					if(id==id1 )
					{
						System.out.println(name1);
					}
            	}
				System.out.println("-------------------------------");
            }
        }

        catch (SQLException e) 
		{
            System.out.println(e);
        }
	}

	public boolean adminLogin(int admin_id, int adminpass)
	{
		try 
		{
            String sql = "select * from admin";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
 
            while (rs.next()) 
			{
                int id = rs.getInt(2);
                int pass = rs.getInt(3);
				if(admin_id==id )
				{
					if(adminpass == pass)
					{
						return true;
					}
					else
					{
						System.out.println("Password wrong.");
						return false;
					}
				}
            }
			System.out.println("Admin not registered.");
			return false;
        }

        catch (SQLException e) 
		{
            System.out.println(e);
        }
		return false;
	}

	public boolean useridCheck(int userid)
	{
		try 
		{
            String sql = "select user_id from users";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
 
            while (rs.next()) 
			{
                int id = rs.getInt("user_id");
				if( userid ==id )
				{
					return false;
				}
            }
			return true;
        }

        catch (SQLException e) 
		{
            System.out.println(e);
        }
		return false;
	}

	public void borrowBook(int userid, Book book)
	{
		try
		{
			ps=c.prepareStatement("insert into borrowedbooks(user_id,bookname,book_id) values(?,?,?)");
			ps.setInt(1,userid);
			ps.setString(2,book.getName());
			ps.setInt(3,book.getBookid());	
			ps.executeUpdate();
			book.borrow();
	    }
		catch(Exception e)
		{
			System.out.println("Exception : "+e);
		}
	}

	public boolean userLogin(int user_id, int userpass)
	{
		try 
		{
            String sql = "select * from users";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
 
            while (rs.next()) 
			{
                int id = rs.getInt(1);
                int pass = rs.getInt(3);
				if(user_id==id )
				{
					if(userpass == pass)
					{
						return true;
					}
					else
					{
						System.out.println("Password wrong.");
						return false;
					}
				}
            }
			System.out.println("User not registered.");
			return false;
        }

        catch (SQLException e) 
		{
            System.out.println(e);
        }
		return false;
	}
	
	public boolean ifBorrowed(int user_id, int bookid)
	{
		try 
		{
            String sql = "select * from borrowedbooks";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
 
            while (rs.next()) 
			{
                int id = rs.getInt("user_id");
                int book_id = rs.getInt("book_id");
				if(user_id==id )
				{
					if(book_id==bookid)
					{
						return false;
					}
				}
            }
			return true;
        }

        catch (SQLException e) 
		{
            System.out.println(e);
        }
		return true;
	}

	public void returnBook(int userid, int bookid,Book book)
	{
		try{
			String sql ="DELETE FROM borrowedbooks WHERE user_id = ? AND book_id = ?";
			ps = c.prepareStatement(sql);
				ps.setInt(1, userid);
				ps.setInt(2, bookid);
				ps.executeUpdate();											// change
				book.returnBook();
	
			} 
			catch (SQLException e) 
			{
				System.out.println(e);
			}
	}

	public void truncateDB()
	{
		String ss="truncate books";
		try {
			s.execute(ss);
		} catch (SQLException e) {
			System.out.println("Exception : "+e);
		}
	}
	

	public void sendBookData(Book book)
	{
		try
		{
			String x;
			if(book.isAvailable())
			{
				x="Yes";
			}
			else
			{
				x="No";
			}

			ps=c.prepareStatement("insert into books values(?,?,?,?)");
			ps.setInt(1,book.getBookid());
			ps.setString(2,book.getName());
			ps.setString(3,book.getAuthor());
			ps.setString(4,x);	
			ps.executeUpdate();
	    }
		catch(Exception e)
		{
			System.out.println("Exception : "+e);
		}
	}

		

	public void returnBorrowedbooks(int userid)
	{
		try 
		{
            String sql = "select * from borrowedbooks";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
			
			System.out.println("Borrowed books are :");
            while (rs.next()) 
			{
                int id = rs.getInt("user_id");
                String name = rs.getString("bookname");
				if(userid==id )
				{
					System.out.println(name);
				}
            }
        }

        catch (SQLException e) 
		{
            System.out.println(e);
        }
	}
	
	public void displayDetails(int user_id)
	{
		try 
		{
            String sql = "select * from users";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
 
            while (rs.next()) 
			{
                int id = rs.getInt("user_id");
                String name = rs.getString("username");
				int fine=rs.getInt("Fine");				
				if(id == user_id)
				{
                System.out.println("User ID : "+id);
				System.out.println("User Name : "+name);
				System.out.println("Pending Fine : "+fine); 
				String sql1 = "select * from borrowedbooks";
				ps1 = c.prepareStatement(sql1);
				rs1 = ps1.executeQuery();
				System.out.println("Borrowed books are :");
            	while (rs1.next()) 
				{
                	int id1 = rs1.getInt("user_id");
                	String name1 = rs1.getString("bookname");
					if(id==id1 )
					{
						System.out.println(name1);
					}
            	}
				}
            }
        }

        catch (SQLException e) 
		{

            System.out.println(e);
        }
	}

	public void displayDetails(String username)
	{
		try 
		{
            String sql = "select * from users";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
 
            while (rs.next()) 
			{
                int id = rs.getInt("user_id");
                String name = rs.getString("username");
				int fine=rs.getInt("Fine");	
				if(username.equalsIgnoreCase(name))
				{
					System.out.println("User ID : "+id);
					System.out.println("User Name : "+name);
					System.out.println("Pending Fine : "+fine); 
					
					String sql1 = "select * from borrowedbooks";
					ps1 = c.prepareStatement(sql1);
					rs1 = ps1.executeQuery();
					System.out.println("Borrowed books are :");
            		while (rs1.next()) 
					{
                		int id1 = rs1.getInt("user_id");
               		 	String name1 = rs1.getString("bookname");
						if(id==id1 )
						{
						System.out.println(name1);
						}
            		}
                	
				}
            }
        }

        catch (SQLException e) 
		{

            System.out.println(e);
        }
	}

	public boolean passCheck(int userid,int pass)
	{
		try 
		{
            String sql = "select * from users";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
 
            while (rs.next()) 
			{
                int id = rs.getInt("user_id");
                int password = rs.getInt("user_password");
				if(userid==id)
				{
					if(pass==password)
					{
						return true;
					}
					return false;
				}
			}
			
        }

        catch (SQLException e) 
		{

            System.out.println(e);
        }
		return false;
	}

	public void changePassword(int userid,int pass)
	{
		try 
		{
			String sql ="UPDATE users SET user_password=? WHERE user_id = ? ";
			ps = c.prepareStatement(sql);
			ps.setInt(1, pass);
			ps.setInt(2, userid);
			ps.executeUpdate();	
		}			

        catch (SQLException e) 
		{
            System.out.println(e);
        }
	}

	public int calcFine(int userid, int bookid)
	{
		int fine=0;
		try 
		{
			String sql ="select * FROM borrowedbooks WHERE user_id = ? AND book_id = ?";
				ps = c.prepareStatement(sql);
				ps.setInt(1, userid);
				ps.setInt(2, bookid);
				rs = ps.executeQuery();		
				
				 while (rs.next()) 
			{
			
                Date issuedate = rs.getDate("issueDate");
				LocalDate ReturnDate = LocalDate.now();
				LocalDate issueDate = issuedate.toLocalDate();
       			Period period = Period.between(issueDate, ReturnDate);
    		    int TotalDays=period.getDays();
				if(TotalDays > 7)
				{
					TotalDays=TotalDays-7;
					fine = 10*TotalDays;
					return fine;
				}
			}
				return fine;

        }

        catch (SQLException e) 
		{
            System.out.println(e);
        }
		return fine;
	}

	public void updateFine(int fine,int userid)
	{
		try 
		{
			String sql ="UPDATE users SET Fine=? WHERE user_id = ? ";
			ps = c.prepareStatement(sql);
			ps.setInt(1, fine);
			ps.setInt(2, userid);
			ps.executeUpdate();	
		}			

        catch (SQLException e) 
		{
            System.out.println(e);
        }
	}

	public int getFine(int user_id)
	{
		try 
		{
            String sql = "select * from users";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
 
            while (rs.next()) 
			{
                int id = rs.getInt("user_id");
				int fine=rs.getInt("Fine");				
				if(id == user_id)
				{
				return fine;
				}
            }
			return 0;
        }

        catch (SQLException e) 
		{

            System.out.println(e);
        }
		return 0;
	}

	public void payFine(int amt,int userid)
	{
		try 
		{
			String sql = "select * from users";
            ps = c.prepareStatement(sql);
            rs = ps.executeQuery();
			int fine=0;
 
            while (rs.next()) 
			{
				int id=rs.getInt("user_id");				
				if(id == userid)
				{
					fine=rs.getInt("Fine");
				}
            }

			int temp = fine - amt;

			sql ="UPDATE users SET Fine=? WHERE user_id = ? ";
			ps = c.prepareStatement(sql);
			ps.setInt(1, temp);
			ps.setInt(2, userid);
			ps.executeUpdate();	
			System.out.println("Amount paid successfully.");
		}			

        catch (SQLException e) 
		{
            System.out.println(e);
        }
	}
}