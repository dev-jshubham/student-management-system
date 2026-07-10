import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Manager manager=new Manager(sc);
        while(true){
            System.out.println("\n");
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("►◄  STUDENT MANAGEMENT SYSTEM  ►◄");
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println("1. ENTER DETAILS");
            System.out.println("2. GET DETAILS");
            System.out.println("3. SEARCH STUDENT DETAIL");
            System.out.println("4. UPDATE DETAIL");
            System.out.println("5. DELETE DATA");
            System.out.println("6. EXIT");
            System.out.println("------------------------------------------------------------------------------------");
            System.out.println();
            System.out.println("\n▶► ENTER:");
            int function= manager.checkInt();
        switch (function) {
            case 1:
                manager.details();
                break;
            case 2:
                manager.getDetails();
                break;
            case 3:
                manager.searchStudent();
                break;
            case 4:
                manager.updateDetail();
                break;
            case 5:
                manager.deleteData();
                break;
            case 6:
                System.out.println("\n◌◌◌◌Program ends.........◌◌◌◌"+ "\n◌◌◌◌Thank you!!!!◌◌◌◌");
                return;
            default:
                System.out.println("\n◌◌◌◌Wrong input..................◌◌◌◌");
                break;
        }
        }
    }
}