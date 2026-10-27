
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class LabActivity_2 {
public static void main(String[] args) {
Scanner input = new Scanner(System.in);
System.out.println("=== Welcome John Lourde Aquino ===");

while (true) {
int totalPages = 0;
int pagesRead = 0;
int pagesPerDay;

while (true) {
System.out.print("How many pages does the book have?: ");
String totalStr = input.nextLine();
try {
totalPages = Integer.parseInt(totalStr);
break;
} catch (NumberFormatException e) {
System.out.println("Invalid input. Please enter a valid number.\n");
}
}


while (true) {
System.out.print("How many pages have you read?: ");
String readStr = input.nextLine();
try {
pagesRead = Integer.parseInt(readStr);
break;
} catch (NumberFormatException e) {
System.out.println("Invalid input. Please enter a valid number.\n");
}
}


if (totalPages <= 0 || pagesRead < 0 || pagesRead > totalPages) {
System.out.println("Invalid page input. Please try again.\n");
continue;
}


LocalDate issueDate = null;
LocalDate dueDate = null;

while (true) {
try {
System.out.print("Issue date (YYYY-MM-DD): ");
issueDate = LocalDate.parse(input.nextLine());

System.out.print("Due date (YYYY-MM-DD): ");
dueDate = LocalDate.parse(input.nextLine());

if (dueDate.isBefore(issueDate)) {
System.out.println("Due date cannot be before issue date. Please re-enter dates.\n");
continue; 
}
break; 
} catch (DateTimeParseException e) {
System.out.println("Invalid date format. Please use YYYY-MM-DD.\n");

}
}


LocalDate today = LocalDate.now();
double percentage = (double) pagesRead / totalPages * 100;
int pagesLeft = totalPages - pagesRead;
long remainingDays = ChronoUnit.DAYS.between(today, dueDate);

System.out.printf("You have read %.0f%% of the book.\n", percentage);

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

if (remainingDays < 0) {
System.out.println("Your book is overdue.");
} else if (remainingDays == 0) {
System.out.println("Your book is due today.");
}
else {
System.out.println("You have " + remainingDays + " days remaining.");

}
break; 
}
}
}

