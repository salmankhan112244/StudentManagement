import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Properties;

public class StudentManagement {

    public static void main(String[] args) {
 try {
            Properties props = new Properties();
   FileInputStream fis = new FileInputStream("config.properties");
           props.load(fis);
String url = "jdbc:mariadb://localhost:3306/student_management?useSsl=false&restrictedAuth=mysql_native_password";
        String user = props.getProperty("db.user").trim();
        String password = props.getProperty("db.password").trim();
  
       
            Connection con = DriverManager.getConnection(url, user, password);

            System.out.println("Database Connected Successfully!");

             
           Scanner sc = new Scanner(System.in);
         
            while (true)   {
               
             System.out.println("===== Student Management System =====");
             System.out.println("1.  Add Student"); 
             System.out.println("2. View All Students");
             System.out.println("3. Search Student");
              System.out.println("4. Update Student");                                  
             System.out.println("5. Delete Student");
             System.out.println("6. Exit");

 
          System.out.print("Enter your choice: ");
           int choice = sc.nextInt();

          if (choice == 1) {

    System.out.println("Add Student selected");

    sc.nextLine();

    System.out.print("Enter student name: ");
    String name = sc.nextLine();

    System.out.print("Enter age: ");
    int age = sc.nextInt();
    sc.nextLine();

    System.out.print("Enter course: ");
    String course = sc.nextLine();

    System.out.print("Enter phone: ");
    String phone = sc.nextLine();
String sql = "INSERT INTO students (name, age, course, phone) VALUES (?, ?, ?, ?)";

PreparedStatement ps = con.prepareStatement(sql);

ps.setString(1, name);
ps.setInt(2, age);
ps.setString(3, course);
ps.setString(4, phone);

int rows = ps.executeUpdate();

if (rows > 0) {
    System.out.println("Student added successfully!");
}
}
else if (choice == 2) {
    System.out.println("View All Students selected");

 Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM students");
            while (rs.next())   {
                        
                        System.out.println("ID: " + rs.getInt("id"));                
                       System.out.println("Name: " + rs.getString("name"));
                        System.out.println("Age: " + rs.getInt("age"));
                        System.out.println("Course: " + rs.getString("course"));
                        System.out.println("Phone: " + rs.getString("phone"));

        System.out.println("-------------------------");
}
}
else if (choice == 3) {
    System.out.println("Search Student selected");
       
     System.out.print("Enter student ID: ");
      int id = sc.nextInt();
           
       String sql = "SELECT * FROM students WHERE id = ?";
  
       PreparedStatement ps = con.prepareStatement(sql);
       ps.setInt(1, id);
       
        ResultSet rs = ps.executeQuery();
  
    if (rs.next())  {
           System.out.println("ID: " + rs.getInt("id"));                
                       System.out.println("Name: " + rs.getString("name"));
                        System.out.println("Age: " + rs.getInt("age"));
                        System.out.println("Course: " + rs.getString("course"));
                        System.out.println("Phone: " + rs.getString("phone"));
}  
  else {
          System.out.println("Student Not Found!");
  }
}
  else if (choice == 4) {

    System.out.println("Update Student selected");

    System.out.print("Enter student ID: ");
    int id = sc.nextInt();
    sc.nextLine();

    System.out.print("Enter new name: ");
    String name = sc.nextLine();

    System.out.print("Enter new age: ");
    int age = sc.nextInt();
    sc.nextLine();

    System.out.print("Enter new course: ");
    String course = sc.nextLine();

    System.out.print("Enter new phone: ");
    String phone = sc.nextLine();

    String sql = "UPDATE students SET name = ?, age = ?, course = ?, phone = ? WHERE id = ?";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setString(1, name);
    ps.setInt(2, age);
    ps.setString(3, course);
    ps.setString(4, phone);
    ps.setInt(5, id);

    int rows = ps.executeUpdate();

    if (rows > 0) {
        System.out.println("Student updated successfully!");
    }
    else {
        System.out.println("Student not found!");
    }
}
else if (choice == 5) {

    System.out.println("Delete Student selected");

    System.out.print("Enter student ID: ");
    int id = sc.nextInt();

    String sql = "DELETE FROM students WHERE id = ?";

    PreparedStatement ps = con.prepareStatement(sql);

    ps.setInt(1, id);

    int rows = ps.executeUpdate();

    if (rows > 0) {
        System.out.println("Student deleted successfully!");
    }
    else {
        System.out.println("Student not found!");
    }
}
else if (choice == 6) {
    System.out.println("Thank you! Program exited.");
     break;
}
else {
    System.out.println("Invalid choice!");
}
      }     
      
            con.close();

        } catch (Exception e) {
            System.out.println("Connection Failed!");
            e.printStackTrace();
        }
    }
}