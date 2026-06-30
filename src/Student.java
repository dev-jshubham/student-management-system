public class Student {
    private final int id;
    private String name;
    private final char grade;

    public Student(int id, String name,char grade) {
        this.id = id;
        this.name = name;
        this.grade=grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return
                "-------------------------------------------------------------------------------------------------------------------\n"+
                "※ ID = " + id +
                "       :  GRADE = " + grade +
                "       :  NAME = " + name+
                "\n-------------------------------------------------------------------------------------------------------------------";
    }
}
