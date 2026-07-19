public class Student {
    private final int id;
    private String name;
    private final int english;
    private final int maths;
    private final int science;
    private final int computer;
    private final int hindi;

    public int getEnglish() {
        return english;
    }

    public int getMaths() {
        return maths;
    }

    public int getScience() {
        return science;
    }

    public int getComputer() {
        return computer;
    }

    public int getHindi() {
        return hindi;
    }

    public Student(int id, String name, int english, int maths, int science, int computer, int hindi) {
        this.id = id;
        this.name = name;
        this.english = english;
        this.maths = maths;
        this.science = science;
        this.computer = computer;
        this.hindi = hindi;
    }

    public int getTotal(){
        return english+science+maths+computer+hindi;
    }

    public double getPercentage(){
        return (getTotal()/500.0)*100;
    }

    public String getStatus(){
        if(getGrade()=='F'){
            return "Fail";
        }
        return "Pass";
    }

    public char getGrade(){
        double percentage = getPercentage();
        if(percentage>=90.0){
            return 'A';
        }
        else if(percentage>=80.0){
            return 'B';
        }
        else if(percentage >=70.0){
            return 'C';
        }
        else if(percentage >=60.0){
            return 'D';
        }
        else{
            return 'F';
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("""
╭─────────────────────────────────────────────────────────────╮
│                 👤 STUDENT PROFILE                          │
├─────────────────────────────────────────────────────────────┤
│ ID          : %-46d│
│ Name        : %-46s│
├─────────────────────────────────────────────────────────────┤
│ English     : %-46d│
│ Maths       : %-46d│
│ Science     : %-46d│
│ Computer    : %-46d│
│ Hindi       : %-46d│
├─────────────────────────────────────────────────────────────┤
│ Total       : %-46s│
│ Percentage  : %-46s│
│ Grade       : %-46c│
│ Status      : %-46s│
╰─────────────────────────────────────────────────────────────╯
""",
                id,
                name,
                english,
                maths,
                science,
                computer,
                hindi,
                getTotal() + " / 500",
                String.format("%.2f %%", getPercentage()),
                getGrade(),
                getStatus()
        );
    }
}
