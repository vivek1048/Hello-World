/**
  * Semester.java
  * CSCI 4055
  * @author Vivek Daruka
  * Due: 11/25/2017
  * A Semester class that contains the list of courses taken during a specific semester and calclulates semester GPA. 
  */

import java.util.*;

public class Semester
{
   private String sem;
   private int year;
   private List<Course> myList = new ArrayList<Course>();    
   private int g = 0;
   private int h = 0;
   private int totalHrs = 0;
   private int sop = 0;
   private double semGPA = 0;

/**
  * Sets the name and year for any given semester.
  * @param sem the semester a section was taught, possible values are Spring, Fall, Summer.
  * @param year the year a course was taught in a four digit format like 2016.
  */

   public Semester (String sem, int year)
   {
      this.sem = sem;
      this.year = year;
   }

/**
  * Returns the semester, Fall or Spring.
  * @return String - the semester course was taken.
  */
     
   public String getSem()
   {
      return sem;
   }

/**
  * Returns the year for the corresponding semester.
  * @return int - the year of the semester.
  */
     
   public int getYear()
   {
      return year;
   }

/**
  * Adds course taken during the given semester.
  * @param c the information of the course.
  */
   
   public void CourseList(Course c)
   {
      myList.add(c);
   } 

/**
  * Returns the semester GPA.
  * @return double - the GPA of given semester.
  */
        
   public double semGPA()
   {
      for (int i=0; i<myList.size(); i++)
      {
         g = myList.get(i).getGrade();
         h = myList.get(i).getHours();
         sop += g * h;
         totalHrs += h;
      }   
      return sop / totalHrs;
   }

/**
  * Returns the semester with year in which the courses are taken.
  * @return String - the information containing the semester with year.
  */
    
   public String toString()
   {
      return "Term: " + getSem() + " " + getYear() + "\n" + String.format("\t%-12s %-8s %-32s %s","Subject","Course","Title","Grade");
   }
}