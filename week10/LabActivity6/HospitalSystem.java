import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// ==========================================
// DATA MODELS
// ==========================================

class Date {
    private final int day;
    private final int month;
    private final int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", day, month, year);
    }
}

class Patient {
    private final String name;
    private final Date admissionDate;
    private final String disease;
    private final Date dischargeDate;

    public Patient(String name, Date admissionDate, String disease, Date dischargeDate) {
        this.name = name;
        this.admissionDate = admissionDate;
        this.disease = disease;
        this.dischargeDate = dischargeDate;
    }

    public String getName() {
        return name;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public String getDisease() {
        return disease;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void displayInfo() {
        System.out.println("Name: " + name);
        System.out.println("Admission Date: " + admissionDate);
        System.out.println("Disease: " + disease);
        System.out.println("Discharge Date: " + dischargeDate);
    }
}

class PediatricPatient extends Patient {
    private static final int PEDIATRIC_AGE_LIMIT = 12;
    private final int age;

    public PediatricPatient(String name, Date admissionDate, String disease, Date dischargeDate, int age) {
        super(name, admissionDate, disease, dischargeDate);
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public boolean isPediatric() {
        return age < PEDIATRIC_AGE_LIMIT;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Age: " + age);
    }
}

// ==========================================
// BULLETPROOF INPUT READER
// ==========================================

class ConsoleInputReader {
    private final Scanner scanner;

    public ConsoleInputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readNonNumericText(String prompt, String fieldName) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Error: " + fieldName + " cannot be empty.");
            } else if (input.matches(".*\\d.*")) {
                System.out.println("Error: " + fieldName + " cannot contain numbers!");
            } else if (!input.matches("[a-zA-Z\\s.-]+")) {
                System.out.println("Error: " + fieldName + " can only contain letters and spaces.");
            } else {
                return input;
            }
        }
    }

    // Uses Integer.parseInt(nextLine()) to prevent Scanner buffer issues
    public int readInteger(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                int value = Integer.parseInt(input);
                if (value >= min && value <= max) {
                    return value;
                }
                System.out.println("Error: Enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid input! Numbers only (letters/symbols not allowed).");
            }
        }
    }

    public Date readDate(String label) {
        System.out.println(label);
        int day = readInteger("  Day (1-31): ", 1, 31);
        int month = readInteger("  Month (1-12): ", 1, 12);
        int year = readInteger("  Year (1900-2100): ", 1900, 2100);
        return new Date(day, month, year);
    }
}

// ==========================================
// MAIN APPLICATION
// ==========================================

public class HospitalSystem {
    private final List<PediatricPatient> patients = new ArrayList<>();
    private final ConsoleInputReader inputReader;

    public HospitalSystem(Scanner scanner) {
        this.inputReader = new ConsoleInputReader(scanner);
    }

    public void run() {
        int choice = 0;
        do {
            displayMenu();
            choice = inputReader.readInteger("Enter choice: ", 1, 4);

            switch (choice) {
                case 1:
                    insertPatient();
                    break;
                case 2:
                    displayAllPatients();
                    break;
                case 3:
                    displayPediatricPatients();
                    break;
                case 4:
                    System.out.println("Exiting database system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } while (choice != 4);
    }

    private void displayMenu() {
        System.out.println("\n--- HOSPITAL DATABASE MENU ---");
        System.out.println("1. Insert New Patient");
        System.out.println("2. Display all Patient");
        System.out.println("3. Display pediatric patient");
        System.out.println("4. Exit");
    }

    private void insertPatient() {
        System.out.println("\n--- INSERT NEW PATIENT ---");
        String name = inputReader.readNonNumericText("1. Name of the patient: ", "Name");
        Date admissionDate = inputReader.readDate("2. Date of admission:");
        String disease = inputReader.readNonNumericText("3. Disease: ", "Disease");
        Date dischargeDate = inputReader.readDate("4. Date of discharge:");
        int age = inputReader.readInteger("Enter Age of patient (0-120): ", 0, 120);

        patients.add(new PediatricPatient(name, admissionDate, disease, dischargeDate, age));
        System.out.println("\n>>> Patient record added successfully! <<<");
    }

    private void displayAllPatients() {
        if (patients.isEmpty()) {
            System.out.println("\nNo patients found in database. Please insert a patient first.");
            return;
        }

        System.out.println("\n=== ALL PATIENTS ===");
        for (int i = 0; i < patients.size(); i++) {
            System.out.println("\n[ Patient #" + (i + 1) + " ]");
            patients.get(i).displayInfo();
        }
    }

    private void displayPediatricPatients() {
        if (patients.isEmpty()) {
            System.out.println("\nNo patients found in database. Please insert a patient first.");
            return;
        }

        boolean foundPediatric = false;
        System.out.println("\n=== PEDIATRIC PATIENTS (AGE < 12) ===");
        int count = 1;
        for (PediatricPatient patient : patients) {
            if (patient.isPediatric()) {
                System.out.println("\n[ Pediatric Patient #" + count + " ]");
                patient.displayInfo();
                foundPediatric = true;
                count++;
            }
        }

        if (!foundPediatric) {
            System.out.println("No pediatric patients found (all recorded patients are age 12 or older).");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HospitalSystem system = new HospitalSystem(scanner);
        system.run();
        scanner.close();
    }
}