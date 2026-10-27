class Professor {
    protected String name;
    protected double salary;

    public Professor(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
}

class Dean extends Professor {
    protected String department;

    public Dean(String name, double salary, String department) {
        super(name, salary);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Status\n" +
               "Rank: Dean\n" +
               "Name: " + name + "\n" +
               "Department: " + department + "\n" +
               "Salary: " + (int)salary;
    }
}

class Executive extends Dean {
    public Executive(String name, double salary, String department) {
        super(name, salary, department);
    }

    @Override
    public String toString() {
        return "Status\n" +
               "Rank: Executive\n" +
               "Name: " + name + "\n" +
               "Department: " + department + "\n" +
               "Salary: " + (int)salary;
    }
}

public class TestAcademicHierarchy {
    public static void main(String[] args) {
        Dean dean = new Dean("John Cena", 32000, "English");
        Executive executive = new Executive("Ronald McDonald", 42000, "Computer Science");

        System.out.println(dean.toString());
        System.out.println();
        System.out.println(executive.toString());
    }
}