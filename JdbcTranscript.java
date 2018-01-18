/**
  * JdbcTranscript.java
  * CSCI 4055
  * @author Vivek Daruka
  * Due: 11/25/2017
  * A Java program that generate the transcript of a student formatted in a specific manner.
  * Transcript contains name, semester, subject, courses, grade along with semester GPA, major GPA and total GPA. 
  */

import java.sql.*;

public class JdbcTranscript 
{
   public static void main(String args[]) 
   {
      Connection con = null;
      Statement stmt;
      ResultSet result;
      String query;
      String major;
      String name;
      String sem;
      String crs;
   
      try
      {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
         con = DriverManager.getConnection("jdbc:mysql://cs.ulm.edu/UNIVERSITY", 
            	   "darukav", "CSCI4055");
      
         if(!con.isClosed())
         {  
            System.out.println("Successfully connected to " +
               "MySQL server using TCP/IP...");
            stmt = con.createStatement();
            
            query = "SELECT MjrCode ";
            query += "FROM MAJOR WHERE CWID = 30054245"; 
            result = stmt.executeQuery(query);
            result.next();
            major = result.getString("MjrCode");
            
            query = "SELECT p.Fname, p.Lname, t.Semester, t.Year, c.Prefix, c.Number, c.Title, t.Grade, c.Hours ";
            query += "FROM PERSON p JOIN TAKEN t ON t.CWID = p.CWID " + 
                "JOIN SECTION s ON t.CRN = s.CRN and t.Semester = s.Semester and t.Year = s.Year " + 
                "JOIN COURSE c ON c.CourseID = s.CourseId " + "WHERE t.CWID = 30054245 " + "ORDER BY t.Year, t.Semester";
            result = stmt.executeQuery(query);
            
            Course myCrs;
            Semester mySem;
            String s = " ";
            int y = 0;
            int g = 0;
            int h = 0;
            int sop = 0;
            int totalHrs = 0;
            int mjrSop = 0;
            int mjrHrs = 0;
            double overallGPA = 0;
            double majorGPA = 0;
         
            result.next(); 
            name = result.getString("Fname") + " " + 
                      result.getString("Lname");
            s =  result.getString("Semester");
            y =  result.getInt("Year");
            mySem = new Semester(s,y);
            System.out.println("Name: " + name + "\n");            
            System.out.println(mySem);
         
            result.previous();                                           
            while(result.next())
            {
               mySem = new Semester(s,y);
               myCrs = new Course(result.getString("Prefix"),result.getInt("Number"),
                       result.getString("Title"),result.getInt("Grade"),result.getInt("Hours"));
               mySem.CourseList(myCrs); 
               
               // information for calculating Total GPA.       
               g = result.getInt("Grade");
               h = result.getInt("Hours");
               sop += g * h;
               totalHrs += h;
               
               // information for calculating Major GPA.   
               if (result.getString("Prefix").equals(major))
               {
                  g = result.getInt("Grade");
                  h = result.getInt("Hours");
                  mjrSop += g * h;
                  mjrHrs += h;
               }
                           
               if (result.getString("Semester").equals(s) && result.getInt("Year") == y)
               {
                  System.out.println(myCrs);
               }
               else
               {
                  System.out.printf("\tSemester GPA = %4.2f \n\n", mySem.semGPA());
                  s =  result.getString("Semester");
                  y =  result.getInt("Year");
                  mySem = new Semester(s,y);
                  mySem.CourseList(myCrs);                 
                  System.out.println(mySem);
                  System.out.println(myCrs);
               }
            }    
            System.out.printf("\tSemester GPA = %4.2f \n\n", mySem.semGPA());
            overallGPA = sop / totalHrs;
            majorGPA = mjrSop / mjrHrs;
            System.out.printf("Total GPA = %4.2f" + ", Major GPA = %4.2f \n\n", overallGPA, majorGPA);          
         }
      } 
      catch(Exception e) 
      {
         System.err.println("Exception: " + e.getMessage());
      } 
      finally
      {
         try
         {
            if(con != null)
               con.close();
         } 
         catch(SQLException e) {}
      }
   }   
}
