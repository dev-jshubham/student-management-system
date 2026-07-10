import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager {

    Scanner sc;
    public Manager(Scanner sc) {
        this.sc = sc;
    }
    List<Student> students=new ArrayList<>();

public int checkInt(){
    while (true){
        try{
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("\nEnter a valid number:");
        }
    }
}

    public String checkString() {
        while (true) {
            String name = sc.nextLine();
            if (name.matches("[a-zA-Z ]+")) {
                return name;
            } else {
                System.out.println("\nEnter a valid name:");
            }
        }
    }

    public char checkChar(){
        while (true){
            try{
                char grade=Character.toUpperCase(sc.nextLine().charAt(0));
                if(grade == 'A' || grade == 'B' ||
                        grade == 'C' || grade == 'D' ||
                        grade == 'E') {
                    return grade;
                }
                else{
                    System.out.println("\nEnter a valid grade:");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\nEnter a valid grade:");
            }
        }
    }


    public void details(){
        System.out.println("\nEnter no. of students:");
        int n=checkInt();
            String  sql="INSERT INTO students(id, name, grade) VALUES(?,?,?)";
            try (
                    Connection connection=DBConnection.getConnection();
                    PreparedStatement preparedStatement=connection.prepareStatement(sql);
                    ) {
                for (int i = 0; i < n; i++){
                    System.out.println("\nEnter ID:");
                int id=checkInt();
                System.out.println("\nEnter name:");
                String name=checkString();
                System.out.println("\nGrade:  A   :   B   :   C   :   D   :   E");
                System.out.println("Enter grade:");
                char grade= checkChar();
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, String.valueOf(grade));
                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {
                    System.out.println("Student "+rows+"entered successfully");
                }
            }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    public void getDetails(){
        String sql = "SELECT * FROM students";
        try (
                Connection connection=DBConnection.getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
                ResultSet rs= preparedStatement.executeQuery();
        ) {
            boolean found = false;
            while (rs.next()) {
                found = true;
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("grade").charAt(0)
                );
                System.out.println(student);
            }
            if(!found){
                System.out.println("\nData not found...........");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void searchStudent() {
        System.out.println("\nEnter the id of student you want to search:");
        int searchid = checkInt();
        String sql = "SELECT * FROM students where id =?";
        try (
                Connection connection=DBConnection.getConnection();
                PreparedStatement preparedStatement=connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, searchid);
            ResultSet rs= preparedStatement.executeQuery();
            if (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("grade").charAt(0)
                );
                System.out.println(student);
            }
            else {
                System.out.println("\nNo data found.........");
            }
            rs.close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateDetail(){
        System.out.println("\nEnter the id of student you want to update:");
        int updateid=checkInt();
        String checkSql = "SELECT * FROM students WHERE id = ?";
        String sql = "UPDATE students SET name = ? WHERE id = ?;";
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                PreparedStatement preparedStatement1 = connection.prepareStatement(checkSql);
                        )
        {
            preparedStatement1.setInt(1,updateid);
            ResultSet rs = preparedStatement1.executeQuery();
            boolean found = false;
            if (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("grade").charAt(0)
                );
                System.out.println(student);
            }
            else {
                System.out.println("\nNo data found............");
                return;
            }
            rs.close();
            System.out.println("\nEnter the updated name:");
            String updatedName = checkString();
        preparedStatement.setString(1,updatedName);
        preparedStatement.setInt(2,updateid);
        int rows = preparedStatement.executeUpdate();
        if(rows>0){
            System.out.println("\nName updated..........");
        }
        else {
            System.out.println("\nNo student found with ID " + updateid);
        }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteData(){
        System.out.println("\nEnter the id of student you want to delete:");
        int deleteid=checkInt();
        for (int i=0;i<students.size();i++) {
            if (deleteid == students.get(i).getId()) {
                students.remove(i);
                System.out.println("\n◌◌◌◌  Student data deleted successfully...... ◌◌◌◌");
                return;
            }
        }
            System.out.println("\nNo result found......");
    }
}
