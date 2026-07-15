import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager {

    Scanner sc;

    public Manager(Scanner sc) {
        this.sc = sc;
    }

    public int checkInt() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("\n▶► Enter a valid number:");
            }
        }
    }

    public String checkString() {
        while (true) {
            String name = sc.nextLine();
            if (name.matches("[a-zA-Z ]+")) {
                return name;
            } else {
                System.out.println("\n▶► Enter a valid name:");
            }
        }
    }

    public char checkChar() {
        while (true) {
            try {
                char grade = Character.toUpperCase(sc.nextLine().charAt(0));
                if (grade == 'A' || grade == 'B' ||
                        grade == 'C' || grade == 'D' ||
                        grade == 'E') {
                    return grade;
                } else {
                    System.out.println("\n▶► Enter a valid grade:");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("\n▶► Enter a valid grade:");
            }
        }
    }


    public void details() {
        System.out.println("\n▶► Enter number of students:");
        int n = checkInt();
        String sql = "INSERT INTO students(id, name, grade) VALUES(?,?,?)";
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            for (int i = 0; i < n; i++) {
                System.out.println("\n▶► Enter ID:");
                int id = checkInt();
                System.out.println("\n▶► Enter name:");
                String name = checkString();
                System.out.println("\n◌◌◌◌     Grade:  A   :   B   :   C   :   D   :   E      ◌◌◌◌");
                System.out.println("▶► Enter grade:");
                char grade = checkChar();
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, String.valueOf(grade));
                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {
                    System.out.println("\n◌◌◌◌Student ( ID =  " + id+ " ) added successfully.............◌◌◌◌");
                }
            }
        }
        catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("◌◌◌◌Student already exists............◌◌◌◌");
        } catch (SQLException e) {
            System.out.println("◌◌◌◌◌◌Database Error :   ◌◌◌◌◌◌ " + e.getMessage());
        }
    }


    public void getDetails() {
        String sql = "SELECT * FROM students";
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery();
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
            if (!found) {
                System.out.println("\n◌◌◌◌No data found...........◌◌◌◌");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void searchStudent() {
        System.out.println("\n▶► Enter the id of student you want to search:");
        int searchid = checkInt();
        String sql = "SELECT * FROM students where id =?";
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            preparedStatement.setInt(1, searchid);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("grade").charAt(0)
                );
                System.out.println(student);
            } else {
                System.out.println("\n◌◌◌◌No data found.........◌◌◌◌");
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateDetail() {
        System.out.println("\n▶► Enter the id of student you want to update:");
        int updateid = checkInt();
        String checkSql = "SELECT * FROM students WHERE id = ?";
        String sql = "UPDATE students SET name = ? WHERE id = ?;";
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                PreparedStatement preparedStatement1 = connection.prepareStatement(checkSql);
        ) {
            preparedStatement1.setInt(1, updateid);
            ResultSet rs = preparedStatement1.executeQuery();
            boolean found = false;
            if (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("grade").charAt(0)
                );
                System.out.println(student);
            } else {
                System.out.println("\n◌◌◌◌No data found............◌◌◌◌");
                return;
            }
            rs.close();
            System.out.println("\n▶► Enter the updated name:");
            String updatedName = checkString();
            preparedStatement.setString(1, updatedName);
            preparedStatement.setInt(2, updateid);
            int rows = preparedStatement.executeUpdate();
            if (rows > 0) {
                System.out.println("\n◌◌◌◌Name updated..........◌◌◌◌");
            } else {
                System.out.println("\n◌◌◌◌No student found with ID " + updateid+"..........◌◌◌◌");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteData() {
        System.out.println("\n▶► Enter the id of student you want to delete:");
        int deleteid = checkInt();
        String sql = "DELETE FROM students WHERE id = ?;";
        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                )
        {
            preparedStatement.setInt(1,deleteid);
            int rows = preparedStatement.executeUpdate();
            if(rows>0){
                System.out.println("\n◌◌◌◌  Student ( ID = " +deleteid+ " ) deleted successfully...... ◌◌◌◌");
            }
            else{
                System.out.println("\n◌◌◌◌No data found............◌◌◌◌");
            }
       } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
