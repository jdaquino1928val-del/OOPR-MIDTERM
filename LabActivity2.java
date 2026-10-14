import java.util.Scanner;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LabActivity2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int totalPages, pagesRead;
        double percentage;
        long remainingDays;
        int pagesLeft, pagesPerDay;

        System.out.println("=== Welcome John Lourde Aquino ===");

        System.out.print("How many pages does the book have?: ");
        totalPages = input.nextInt();

        System.out.print("How many pages have you read?: ");
        pagesRead = input.nextInt();

        input.nextLine();

        System.out.print("Issue date (YYYY-MM-DD): ");
        LocalDate issueDate = LocalDate.parse(input.nextLine());

        System.out.print("Due date (YYYY-MM-DD): ");
        LocalDate dueDate = LocalDate.parse(input.nextLine());

        LocalDate today = LocalDate.now();

        if (totalPages <= 0 || pagesRead < 0 || pagesRead > totalPages) {
            System.out.println("Invalid page input.");
        } else if (dueDate.isBefore(issueDate)) {
            System.out.println("Due date cannot be before issue date.");
        } else {
            percentage = (double) pagesRead / totalPages * 100;
            pagesLeft = totalPages - pagesRead;
            remainingDays = ChronoUnit.DAYS.between(today, dueDate);

            System.out.printf("You have read %.0f%% of the book.%n",
                    percentage);

            if (remainingDays < 0) {
                System.out.println("Your book is overdue.");
            } else if (remainingDays == 0) {
                System.out.println("Your book is due today.");
            } else {
                System.out.println("You have " + remainingDays
                        + " days remaining to return the book.");
            }

            System.out.println("You have " + pagesLeft
                    + " pages left to read.");

            if (pagesLeft == 0) {
                System.out.println("You have finished the book!");
            } else if (remainingDays > 0) {
                pagesPerDay = (int) Math.ceil(
                        (double) pagesLeft / remainingDays);

                System.out.println("You should read " + pagesPerDay
                        + " pages per day.");
            } else {
                System.out.println("The book must be returned now.");
            }
        }

        input.close();
    }
}