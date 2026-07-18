import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        Manager manager=new Manager(sc);
        System.out.println();
        System.out.println("""
            ███████╗███╗   ███╗███████╗
            ██╔════╝████╗ ████║██╔════╝
            ███████╗██╔████╔██║███████╗
            ╚════██║██║╚██╔╝██║╚════██║
            ███████║██║ ╚═╝ ██║███████║
            ╚══════╝╚═╝     ╚═╝╚══════╝
            
            🎓STUDENT MANAGEMENT SYSTEM🎓
            """);
        while(true){
            System.out.println();
            System.out.println("""
            ╔══════════════════════════════════════════════════════════════╗
            ║                 🎓 STUDENT MANAGEMENT SYSTEM                ║
            ╠══════════════════════════════════════════════════════════════╣
            ║  1. ➕  Add Student                                          ║
            ║  2. 📋  View All Students                                    ║
            ║  3. 🔍  Search Student                                       ║
            ║  4. ✏️  Update Student                                       ║
            ║  5. 🗑️  Delete Student                                       ║
            ║  6. 🚪  Exit                                                 ║
            ╚══════════════════════════════════════════════════════════════╝
            
            ▶ Enter your choice:
            """);
            int function= manager.checkInt();
        switch (function) {
            case 1->{
                System.out.println();
                System.out.println(""" 
        ╭──────────────────────────────╮
        │        ➕ ADD STUDENT         │
        ╰──────────────────────────────╯
        """);
                manager.details();
            }
            case 2-> {
                System.out.println();
                System.out.println("""
            ╭──────────────────────────────╮
            │      📋 ALL STUDENTS          │
            ╰──────────────────────────────╯
            """);
                manager.getDetails();
            }
            case 3->{
                System.out.println();
                System.out.println("""
        ╭──────────────────────────────╮
        │       🔍 SEARCH STUDENT       │
        ╰──────────────────────────────╯
        """);
                manager.searchStudent();
            }
            case 4->
                manager.updateDetail();
            case 5->{
                System.out.println();
                System.out.println("""
        ╭──────────────────────────────╮
        │       🗑️ DELETE STUDENT       │
        ╰──────────────────────────────╯
        """);
                manager.deleteData();
            }
            case 6->manager.fullRanking();
            case 7->{
                System.out.println();
                System.out.println("""
                ╔══════════════════════════════════════╗
                ║      🙏 THANK YOU FOR USING SMS      ║
                ║                                      ║
                ║        Have a Great Day! 😊          ║
                ╚══════════════════════════════════════╝
                """);
                return;
            }
            default -> System.out.println("❌ Invalid Input! Please try again.");
        }
        }
    }
}