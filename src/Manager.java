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
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter ID:");
            int id=checkInt();
            System.out.println("\nEnter name:");
            String name=checkString();
            System.out.println("\nGrade:  A   :   B   :   C   :   D   :   E");
            System.out.println("Enter grade:");
            char grade= checkChar();
            students.add(new Student(id,name,grade));
        }
    }


    public void getDetails(){
        if(students.isEmpty()){
            System.out.println("\nNo data.......");
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }


    public void searchStudent(){
        System.out.println("\nEnter the id of student you want to search:");
        int searchid=checkInt();
        boolean check = false;
        for (Student student : students) {
            if (searchid == student.getId()) {
                System.out.println(student);
                check=true;
            }
        }
        if(!check){
            System.out.println("\nNo result found......");
        }
    }


    public void updateDetail(){
        System.out.println("\nEnter the id of student you want to update:");
        int updateid=checkInt();
        boolean check = false;
        for (Student student : students) {
            if (updateid == student.getId()) {
                System.out.println(student);
                check=true;
            }
        }
        if(!check){
            System.out.println("\nNo result found......");
            return;
        }
    System.out.println("\nEnter the updated name");
        String updatedName=checkString();
        for (Student student:students){
            if(updateid==student.getId()){
                student.setName(updatedName);
                System.out.println("\n◌◌◌◌  Student name updated successfully...... ◌◌◌◌");
            }
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
