/**
  * Course.java
  * CSCI 4055
  * @author Vivek Daruka
  * Due: 11/25/2017
  * A Course class that has the course object in it. 
  */

public class Course
{
   private String prefix;
   private String title;
   private int number;
   private int grade;
   private int hours;

/**
  * Sets the prefix, number, title, grade and hours for the courses taken.
  * @param prefix a four letter code for the discipline for example CSCI for computer science.
  * @param number the four digit course number for example 1020.
  * @param title the title of the course for example "Database Theory".
  * @param grade a number value of 0, 1, 2, 3, or 4 that define the grade earned in class.
  * @param hours the number of credit hour earned by taking the course.
  */

   public Course (String prefix, int number, String title, int grade, int hours)
   {
      this.prefix = prefix;
      this.number = number;
      this.title = title;
      this.grade = grade;
      this.hours = hours;
   }
   
/**
  * Returns the prefix of the course.
  * @return String - the prefix of the course taken.
  */
  
   public String getPrefix()
   {
      return prefix;
   }

/**
  * Returns the four digit code number of the course.
  * @return int - the four digit code number of the course taken.
  */
   
   public int getNumber()
   {
      return number;
   }
   
/**
  * Returns the title of the course.
  * @return String - the title of the course taken.
  */

   public String getTitle()
   {
      return title;
   }

 /**
   * Returns the grade earned in the course.
   * @return int - the grade earned in the course taken.
   */
  
   public int getGrade()
   {
      return grade;
   }

/**
  * Returns the number of credit hours of the course.
  * @return int - the number of credit hours of the course taken.
  */
   
   public int getHours()
   {
      return hours;
   }



 /**
   * Returns course information containing prefix, number, title and letter grade.
   * @return String - the course information containing prefix, number, title and letter grade.
   */
     
   public String toString()
   {
      return String.format("\t%-12s %-8d %-32s %s",getPrefix(),getNumber(),getTitle(),getLgrade());
   }
}
