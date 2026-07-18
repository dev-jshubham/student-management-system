import java.sql.*;
import java.util.ArrayList;
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
                System.out.println("❌ Invalid Input! Please try again.");
            }
        }
    }

    public int checkMarks() {
        while (true) {
            int marks = checkInt();
            if (marks >= 0 && marks <= 100) {
                return marks;
            } else {
                System.out.println("⚠️ Marks should be between 0 and 100.");
            }
        }
    }

    public String checkString() {
        while (true) {
            String name = sc.nextLine();
            if (name.matches("[a-zA-Z ]+")) {
                return name;
            } else {
                System.out.println("❌ Invalid Input! Please try again.");
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
                System.out.println("\n▶► English : ");
                int english = checkMarks();
                System.out.println("▶► Science : ");
                int science = checkMarks();
                System.out.println("▶► Maths : ");
                int maths = checkMarks();
                System.out.println("▶► Hindi : ");
                int hindi = checkMarks();
                System.out.println("▶► Computer : ");
                int computer = checkMarks();
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, name);
                preparedStatement.setInt(3, english);
                preparedStatement.setInt(4, hindi);
                preparedStatement.setInt(5, maths);
                preparedStatement.setInt(6, science);
                preparedStatement.setInt(7, computer);
                int rows = preparedStatement.executeUpdate();
                if (rows > 0) {
                    System.out.println("\n✅ Student ( ID = " + id + " ) added successfully.");
                }
            }
        }
        catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("⚠️ Student already exists.");
        } catch (SQLException e) {
            System.out.println("❌ Database Error: " + e.getMessage());
        }
    }


    public void getDetails() {
        String sql = "SELECT * FROM students";
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet rs = preparedStatement.executeQuery();
        ) {
            int studentNo = 1;
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
                System.out.println("👤 Student #" + studentNo);
                System.out.println(student);
                System.out.println();
                studentNo++;            }
            if (!found) {
                System.out.println("⚠️ No data found.");
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
                System.out.println("⚠️ No data found.");
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
                System.out.println("⚠️ No data found.");
                return;
            }
        }
            System.out.println("""
        ╭──────────────────────────────────────╮
        │           ✏️ UPDATE STUDENT            │
        ├──────────────────────────────────────┤
        │  1. 👤 Update Name                    │
        │  2. 📖 Update English Marks           │
        │  3. 📐 Update Maths Marks             │
        │  4. 🔬 Update Science Marks           │
        │  5. 💻 Update Computer Marks          │
        │  6. 📝 Update Hindi Marks             │
        │  7. 📚 Update All Marks               │
        │  8. 🔙 Back                           │
        ╰──────────────────────────────────────╯

        ▶ Enter your choice:
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
                            System.out.println("✅ Name updated successfully.");
                        } else {
                            System.out.println("⚠️ Nothing was updated.");
                        }
                    }
                }
                case 2-> {
                    System.out.println("\n▶► Enter the updated English marks:");
                    int updatedMarks = checkMarks();
                    String sql = "Update students SET english = ? WHERE id = ?";
                    try (
                            PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
                    ){
                        preparedStatement1.setInt(1,updatedMarks);
                        preparedStatement1.setInt(2,updateid);
                        int rows = preparedStatement1.executeUpdate();
                        if (rows > 0) {
                            System.out.println("✅ English marks updated successfully.");
                        } else {
                            System.out.println("⚠️ Nothing was updated.");
                        }
                    }
                }
                case 3->{
                    System.out.println("\n▶► Enter the updated Maths marks:");
                    int updatedMarks = checkMarks();
                    String sql = "Update students SET maths = ? WHERE id = ?";
                    try (
                            PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
                    ){
                        preparedStatement1.setInt(1,updatedMarks);
                        preparedStatement1.setInt(2,updateid);
                        int rows = preparedStatement1.executeUpdate();
                        if (rows > 0) {
                            System.out.println("✅ Maths marks updated successfully.");
                        } else {
                            System.out.println("⚠️ Nothing was updated.");
                        }
                    }
                }
                case 4->{
                    System.out.println("\n▶► Enter the updated Science marks:");
                    int updatedMarks = checkMarks();
                    String sql = "Update students SET science = ? WHERE id = ?";
                    try (
                            PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
                    ){
                        preparedStatement1.setInt(1,updatedMarks);
                        preparedStatement1.setInt(2,updateid);
                        int rows = preparedStatement1.executeUpdate();
                        if (rows > 0) {
                            System.out.println("✅ Science marks updated successfully.");
                        } else {
                            System.out.println("⚠️ Nothing was updated.");
                        }
                    }
                }
                case 5->{
                    System.out.println("\n▶► Enter the updated Computer marks:");
                    int updatedMarks = checkMarks();
                    String sql = "Update students SET computer = ? WHERE id = ?";
                    try (
                            PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
                    ){
                        preparedStatement1.setInt(1,updatedMarks);
                        preparedStatement1.setInt(2,updateid);
                        int rows = preparedStatement1.executeUpdate();
                        if (rows > 0) {
                            System.out.println("✅ Computer marks updated successfully.");
                        } else {
                            System.out.println("⚠️ Nothing was updated.");
                        }
                    }
                }
                case 6->{
                    System.out.println("\n▶► Enter the updated Hindi marks:");
                    int updatedMarks = checkMarks();
                    String sql = "Update students SET hindi = ? WHERE id = ?";
                    try (
                            PreparedStatement preparedStatement1 = connection.prepareStatement(sql);
                    ){
                        preparedStatement1.setInt(1,updatedMarks);
                        preparedStatement1.setInt(2,updateid);
                        int rows = preparedStatement1.executeUpdate();
                        if (rows > 0) {
                            System.out.println("✅ Hindi marks updated successfully.");
                        } else {
                            System.out.println("⚠️ Nothing was updated.");
                        }
                    }
                }
                case 7->{
                    System.out.println("\n▶► Enter the updated English marks:");
                    int english = checkMarks();
                    System.out.println("\n▶► Enter the updated Science marks:");
                    int science = checkMarks();
                    System.out.println("\n▶► Enter the updated Hindi marks:");
                    int hindi = checkMarks();
                    System.out.println("\n▶► Enter the updated Maths marks:");
                    int maths = checkMarks();
                    System.out.println("\n▶► Enter the updated Computer marks:");
                    int computer = checkMarks();
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
                            System.out.println("✅ Marks updated successfully.");
                        } else {
                            System.out.println("⚠️ Nothing was updated.");
                        }
                    }
                }
                case 8 -> {
                    System.out.println("✅ Update quit.");
                    return;
                }
                default -> System.out.println("❌ Invalid Input! Please try again.");
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
                        if (rows > 0) {
                            System.out.println("✅ Student ( ID = " + deleteid + " ) deleted successfully.");
                        } else {
                            System.out.println("⚠️ No data found.");
                        }
                   } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


                public void fullRanking(){
                String sql = "SELECT * FROM students;";
                    try(
                            Connection connection = DBConnection.getConnection();
                            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    )
                    {
                        ArrayList<Student> arrayList = new ArrayList<>();
                        try(
                                ResultSet resultSet = preparedStatement.executeQuery();
                        ) {
                            while (resultSet.next()) {
                                Student students = new Student(
                                        resultSet.getInt("id"),
                                        resultSet.getString("name"),
                                        resultSet.getInt("english"),
                                        resultSet.getInt("maths"),
                                        resultSet.getInt("science"),
                                        resultSet.getInt("computer"),
                                        resultSet.getInt("hindi")
                                );
                                arrayList.add(students);
                        }
                            if(arrayList.isEmpty()){
                                System.out.println("⚠️ No data found.");
                                return;
                            }
                            arrayList.sort((s1,s2)->s2.getTotal()- s1.getTotal());
                            int rank = 1;
                            System.out.println("""
                            ══════════════════════════════════════════════════════
                                           🏆 STUDENT RANKING 🏆
                            ══════════════════════════════════════════════════════
                            """);
                            for (Student student : arrayList) {

                                String medal;
                                String title;

                                if (rank == 1) {
                                    medal = "🥇";
                                    title = "GOLD MEDAL";
                                } else if (rank == 2) {
                                    medal = "🥈";
                                    title = "SILVER MEDAL";
                                } else if (rank == 3) {
                                    medal = "🥉";
                                    title = "BRONZE MEDAL";
                                } else {
                                    medal = "🏅";
                                    title = "STUDENT RANK";
                                }

                                System.out.println();
                                System.out.println("────────────── " + medal + "  " + title + "  |  Rank : " + rank+" ──────────────");
                                System.out.printf("""
            ╭─────────────────────────────────────────────────────────────╮
            │ ID          : %-46d│
            │ Name        : %-46s│
            ├─────────────────────────────────────────────────────────────┤
            │ Total Marks : %-46s│
            │ Percentage  : %-46s│
            │ Grade       : %-46c│
            ╰─────────────────────────────────────────────────────────────╯
            """,
                                        student.getId(),
                                        student.getName(),
                                        student.getTotal() + " / 500",
                                        String.format("%.2f %%", student.getPercentage()),
                                        student.getGrade());

                                rank++;
                            }
                            System.out.println("""
                            ══════════════════════════════════════════════════════
                                       ◌◌◌◌ End of Ranking ◌◌◌◌
                            ══════════════════════════════════════════════════════
                            """);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }


                public void getStatistics(){
                    String sql = "SELECT * FROM students;";
                    try(
                            Connection connection = DBConnection.getConnection();
                            PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    )
                    {
                        ArrayList<Student> arrayList = new ArrayList<>();
                        try(
                                ResultSet resultSet = preparedStatement.executeQuery();
                        ) {
                            while (resultSet.next()) {
                                Student students = new Student(
                                        resultSet.getInt("id"),
                                        resultSet.getString("name"),
                                        resultSet.getInt("english"),
                                        resultSet.getInt("maths"),
                                        resultSet.getInt("science"),
                                        resultSet.getInt("computer"),
                                        resultSet.getInt("hindi")
                                );
                                arrayList.add(students);
                            }
                            if(arrayList.isEmpty()){
                                System.out.println("⚠️ No data found.");
                                return;
                            }

                            Student highest = arrayList.get(0);
                            for (Student student:arrayList){
                                if(student.getTotal()>highest.getTotal()){
                                    highest = student;
                                }
                            }

                            Student lowest = arrayList.get(0);
                            for (Student student:arrayList){
                                if(student.getTotal()<lowest.getTotal()){
                                    lowest = student;
                                }
                            }

                            int totalMarks = 0;
                            for (Student student:arrayList){
                            totalMarks = totalMarks+student.getTotal();
                            }
                            double average = (double) totalMarks / arrayList.size();

                            System.out.println("╭──────────────────────────────────────────────────────────────╮");
                            System.out.printf("│%-60s│%n", "📊 CLASS STATISTICS");
                            System.out.println("├──────────────────────────────────────────────────────────────┤");

                            System.out.printf("│ %-60s │%n", "Total Students : " + arrayList.size());
                            System.out.printf("│ %-60s │%n", "Topper         : " + highest.getName());
                            System.out.printf("│ %-60s │%n", "Highest Marks  : " + highest.getTotal() + " / 500");
                            System.out.printf("│ %-60s │%n", "Lowest Student : " + lowest.getName());
                            System.out.printf("│ %-60s │%n", "Lowest Marks   : " + lowest.getTotal() + " / 500");
                            System.out.printf("│ %-60s │%n", "Average Marks  : " + String.format("%.2f", average));

                            System.out.println("╰──────────────────────────────────────────────────────────────╯");
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
