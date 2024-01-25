package records;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Student {
    private String name;
    private int rollNumber;
    private Map<String, Integer> subjectMarks;

    public Student(String name, int rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.subjectMarks = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public Map<String, Integer> getSubjectMarks() {
        return subjectMarks;
    }

    public void addSubjectMark(String subject, int mark) {
        subjectMarks.put(subject, mark);
    }

    public void updateSubjectMark(String subject, int mark) {
        if (subjectMarks.containsKey(subject)) {
            subjectMarks.put(subject, mark);
        } else {
            System.out.println("Subject not found for updating.");
        }
    }

    public void deleteSubjectMark(String subject) {
        if (subjectMarks.containsKey(subject)) {
            subjectMarks.remove(subject);
        } else {
            System.out.println("Subject not found for deletion.");
        }
    }

    public double calculateOverallPercentage() {
        if (subjectMarks.isEmpty()) {
            return 0.0;
        }

        int totalMarks = 0;
        for (int mark : subjectMarks.values()) {
            totalMarks += mark;
        }

        return (double) totalMarks / subjectMarks.size();
    }

    public String calculateGrade() {
        double percentage = calculateOverallPercentage();

        if (percentage >= 90) {
            return "A";
        } else if (percentage >= 80) {
            return "B";
        } else if (percentage >= 70) {
            return "C";
        } else if (percentage >= 60) {
            return "D";
        } else {
            return "F";
        }
    }
}

public class StudentManagementSystem {
    private static ArrayList<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Update Student");
            System.out.println("3. Delete Student");
            System.out.println("4. Display Student Information");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    updateStudent(scanner);
                    break;
                case 3:
                    deleteStudent(scanner);
                    break;
                case 4:
                    displayStudentInformation(scanner);
                    break;
                case 5:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

   private static void addStudent(Scanner scanner) {
    System.out.print("Enter student name: ");
    String name = scanner.nextLine();

    int rollNumber = 0;
    boolean validRollNumber = false;
    while (!validRollNumber) {
        try {
            System.out.print("Enter roll number: ");
            rollNumber = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character
            validRollNumber = true;
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input for roll number. Please enter a valid integer.");
            scanner.nextLine();  // Consume the invalid input
        }
    }

    Student newStudent = new Student(name, rollNumber);

    int numSubjects = 0;
    boolean validNumSubjects = false;
    while (!validNumSubjects) {
        try {
            System.out.print("Enter the number of subjects: ");
            numSubjects = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character
            validNumSubjects = true;
        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input for the number of subjects. Please enter a valid integer.");
            scanner.nextLine();  // Consume the invalid input
        }
    }

    for (int i = 0; i < numSubjects; i++) {
        System.out.print("Enter subject name: ");
        String subject = scanner.nextLine();

        int mark = 0;
        boolean validMark = false;
        while (!validMark) {
            try {
                System.out.print("Enter marks for " + subject + ": ");
                mark = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character
                validMark = true;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input for marks. Please enter a valid integer.");
                scanner.nextLine();  // Consume the invalid input
            }
        }

        newStudent.addSubjectMark(subject, mark);
    }

    studentList.add(newStudent);
    System.out.println("Student added successfully.");
}



    private static void updateStudent(Scanner scanner) {
        System.out.print("Enter roll number of the student to update: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        for (Student student : studentList) {
            if (student.getRollNumber() == rollNumber) {
                System.out.println("1. Add Subject Mark");
                System.out.println("2. Update Subject Mark");
                System.out.println("3. Delete Subject Mark");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();  // Consume the newline character

                switch (choice) {
                    case 1:
                        addSubjectMark(scanner, student);
                        break;
                    case 2:
                        updateSubjectMark(scanner, student);
                        break;
                    case 3:
                        deleteSubjectMark(scanner, student);
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }

                return;
            }
        }

        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

    private static void deleteSubjectMark(Scanner scanner, Student student) {
        System.out.print("Enter subject name to delete: ");
        String subject = scanner.nextLine();

        student.deleteSubjectMark(subject);

        System.out.println("Subject mark deleted successfully.");
    }

    private static void deleteStudent(Scanner scanner) {
        System.out.print("Enter the roll number of the student to delete: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        ArrayList<Student> tempStudentList = new ArrayList<>(studentList);

        for (Student student : tempStudentList) {
            if (student.getRollNumber() == rollNumber) {
                studentList.remove(student);
                System.out.println("Student deleted successfully.");
                return;
            }
        }

        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

    private static void displayStudentInformation(Scanner scanner) {
        System.out.print("Enter the roll number of the student to display information: ");
        int rollNumber = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        for (Student student : studentList) {
            if (student.getRollNumber() == rollNumber) {
                System.out.println("\nStudent Information:");
                System.out.println("Name: " + student.getName());
                System.out.println("Roll Number: " + student.getRollNumber());
                System.out.println("Subject Marks:");

                for (Map.Entry<String, Integer> entry : student.getSubjectMarks().entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }

                System.out.println("Overall Percentage: " + student.calculateOverallPercentage() + "%");
                System.out.println("Grade: " + student.calculateGrade());
                return;
            }
        }

        System.out.println("Student with roll number " + rollNumber + " not found.");
    }

    private static void addSubjectMark(Scanner scanner, Student student) {
        System.out.print("Enter subject name: ");
        String subject = scanner.nextLine();

        System.out.print("Enter marks for " + subject + ": ");
        int mark = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        student.addSubjectMark(subject, mark);
        System.out.println("Subject mark added successfully.");
    }

    private static void updateSubjectMark(Scanner scanner, Student student) {
        System.out.print("Enter subject name to update: ");
        String subject = scanner.nextLine();

        System.out.print("Enter new marks for " + subject + ": ");
        int newMark = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        student.updateSubjectMark(subject, newMark);
        System.out.println("Subject mark updated successfully.");
    }
}
