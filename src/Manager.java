import java.sql.*;
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

    public void details() {
        System.out.println("\n▶► Enter number of students:");
        int n = checkInt();
        String sql = "INSERT INTO students(id, name,english,hindi,maths,science,computer) VALUES(?,?,?,?,?,?,?)";
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ) {
            for (int i = 0; i < n; i++) {
                System.out.println("\n▶► Enter ID:");
                int id = checkInt();
                System.out.println("\n▶► Enter name:");
                String name = checkString();
                System.out.println("\n▶► Enter marks:");
                System.out.println("\n▶► English : ");
                int english = checkInt();
                System.out.println("▶► Science : ");
                int science = checkInt();
                System.out.println("▶► Maths : ");
                int maths = checkInt();
                System.out.println("▶► Hindi : ");
                int hindi = checkInt();
                System.out.println("▶► Computer : ");
                int computer = checkInt();
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.setInt(3, english);
                preparedStatement.setInt(4, hindi);
                preparedStatement.setInt(5, maths);
                preparedStatement.setInt(6, science);
                preparedStatement.setInt(7, computer);
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
                        rs.getInt("english"),
                        rs.getInt("maths"),
                        rs.getInt("science"),
                        rs.getInt("computer"),
                        rs.getInt("hindi")
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
                        rs.getInt("english"),
                        rs.getInt("maths"),
                        rs.getInt("science"),
                        rs.getInt("computer"),
                        rs.getInt("hindi")
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
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(checkSql);
        ) {
            preparedStatement.setInt(1, updateid);
            try (
                 ResultSet rs = preparedStatement.executeQuery();
                 ){
            if (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("english"),
                        rs.getInt("maths"),
                        rs.getInt("science"),
                        rs.getInt("computer"),
                        rs.getInt("hindi")
                );
                System.out.println(student);
            } else {
                System.out.println("\n◌◌◌◌No data found............◌◌◌◌");
                return;
            }
        }
            System.out.println("""
            ====================================
            UPDATE STUDENT
            ====================================

            SELECT AN OPTION:

            1. Name
            2. English Marks
            3. Maths Marks
            4. Science Marks
            5. Computer Marks
            6. Hindi Marks
            7. Update All Marks
            8. Exit

            Enter your choice:
            """);
            int updateOption = checkInt();
            switch (updateOption){
                case 1->{
                    System.out.println("\n▶► Enter the updated name:");
                    String updatedName = checkString();
                    String sql = "Update students SET name = ? WHERE id = ?";
                    try (
                            PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
                            ){
                        preparedStatement1.setString(1,updatedName);
                        preparedStatement1.setInt(2,updateid);
                        int rows = preparedStatement1.executeUpdate();
                        if (rows > 0) {
                            System.out.println("\n◌◌◌◌Name updated..........◌◌◌◌");
                        }
                    }
                }
                case 2-> {
                    System.out.println("\n▶► Enter the updated English marks:");
                    int updatedMarks = checkInt();
                    String sql = "Update students SET english = ? WHERE id = ?";
                    try (
                            PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
                    ){
                        preparedStatement1.setInt(1,updatedMarks);
                        preparedStatement1.setInt(2,updateid);
                        int rows = preparedStatement1.executeUpdate();
                        if (rows > 0) {
                            System.out.println("\n◌◌◌◌English Marks updated..........◌◌◌◌");
                        }
                    }
                }
                case 3->{
                    System.out.println("\n▶► Enter the updated Maths marks:");
                    int updatedMarks = checkInt();
                    String sql = "Update students SET maths = ? WHERE id = ?";
                    try (
                            PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
                    ){
                        preparedStatement1.setInt(1,updatedMarks);
                        preparedStatement1.setInt(2,updateid);
                        int rows = preparedStatement1.executeUpdate();
                        if (rows > 0) {
                            System.out.println("\n◌◌◌◌Maths Marks updated..........◌◌◌◌");
                        }
                    }
                }
                case 4->{
                    System.out.println("\n▶► Enter the updated Science marks:");
                    int updatedMarks = checkInt();
                    String sql = "Update students SET science = ? WHERE id = ?";
                    try (
                            PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
                    ){
                        preparedStatement1.setInt(1,updatedMarks);
                        preparedStatement1.setInt(2,updateid);
                        int rows = preparedStatement1.executeUpdate();
                        if (rows > 0) {
                            System.out.println("\n◌◌◌◌Science Marks updated..........◌◌◌◌");
                        }
                    }
                }
                case 5->{
                    System.out.println("\n▶► Enter the updated Computer marks:");
                    int updatedMarks = checkInt();
                    String sql = "Update students SET computer = ? WHERE id = ?";
                    try (
                            PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
                    ){
                        preparedStatement1.setInt(1,updatedMarks);
                        preparedStatement1.setInt(2,updateid);
                        int rows = preparedStatement1.executeUpdate();
                        if (rows > 0) {
                            System.out.println("\n◌◌◌◌Computer Marks updated..........◌◌◌◌");
                        }
                    }
                }
                case 6->{
                    System.out.println("\n▶► Enter the updated Hindi marks:");
                    int updatedMarks = checkInt();
                    String sql = "Update students SET hindi = ? WHERE id = ?";
                    try (
                            PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
                    ){
                        preparedStatement1.setInt(1,updatedMarks);
                        preparedStatement1.setInt(2,updateid);
                        int rows = preparedStatement1.executeUpdate();
                        if (rows > 0) {
                            System.out.println("\n◌◌◌◌Hindi Marks updated..........◌◌◌◌");
                        }
                    }
                }
                case 7->{
                    System.out.println("\n▶► Enter the updated English marks:");
                    int english = checkInt();
                    System.out.println("\n▶► Enter the updated Science marks:");
                    int science = checkInt();
                    System.out.println("\n▶► Enter the updated Hindi marks:");
                    int hindi = checkInt();
                    System.out.println("\n▶► Enter the updated Maths marks:");
                    int maths = checkInt();
                    System.out.println("\n▶► Enter the updated Computer marks:");
                    int computer = checkInt();
                    String sql = "Update students SET english = ?, maths = ? , science = ? , hindi = ? , computer = ? WHERE id = ?";
                    try (
                            PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
                    ){
                        preparedStatement1.setInt(1, english);
                        preparedStatement1.setInt(2, maths);
                        preparedStatement1.setInt(3, science);
                        preparedStatement1.setInt(4, hindi);
                        preparedStatement1.setInt(5, computer);
                        preparedStatement1.setInt(6,updateid);
                        int rows = preparedStatement1.executeUpdate();
                        if (rows > 0) {
                            System.out.println("\n◌◌◌◌ Marks updated..........◌◌◌◌");
                        }
                    }
                }
                case 8->{
                    System.out.println("\n◌◌◌◌Quit Update............◌◌◌◌");
                    return;
                }
                default ->
                    System.out.println("\n◌◌◌◌Wrong input..................◌◌◌◌");
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
