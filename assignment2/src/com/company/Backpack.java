package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Backpack {
    Scanner scanner;
    List<Instructor> instructors = new LinkedList<>();
    List<Student> students = new LinkedList<>();
    List<Comment> comments = new LinkedList<>();
    Person current;
    String loginMenu = """
            Welcome to Backpack
            1. Enter as instructor
            2. Enter as student
            3. Exit""";

    Backpack(Scanner scanner, int noOfInstructors, int noOfStudents) {
        this.scanner = scanner;
        for (int i = 0; i < noOfInstructors; i++)
            instructors.add(new Instructor(this));
        for (int i = 0; i < noOfStudents; i++)
            students.add(new Student(this));
    }

    boolean isLoggedIn() {
        return current != null;
    }

    boolean login() {
        System.out.println(loginMenu);
        int option = scanner.nextInt();
        while (option > 3 || option < 1) {
            System.out.println("Invalid input");
            System.out.println(loginMenu);
            option = scanner.nextInt();
        }
        if (option == 1) {
            System.out.println("Instructors:");
            int id = 0;
            for (Instructor instructor : instructors) {
                System.out.println(id++ + " - " + instructor);
            }
            System.out.print("Choose id: ");
            id = scanner.nextInt();
            if (id < 0 || id >= Instructor.getCount()) {
                System.out.println("Invalid input");
                return true;
            }
            current = instructors.get(id);
            System.out.println("Welcome " + current);
            return true;
        } else if (option == 2) {
            System.out.println("Students:");
            int id = 0;
            for (Student student : students) {
                System.out.println(id++ + " - " + student);
            }
            System.out.print("Choose id: ");
            id = scanner.nextInt();
            if (id < 0 || id >= Student.getCount()) {
                System.out.println("Invalid input");
                return true;
            }
            current = students.get(id);
            System.out.println("Welcome " + current);
            return true;
        } else {
            return false;
        }
    }

    public void logout() {
        current = null;
    }

    public void addClassMaterial() {
        System.out.println("""
                1. Add Lecture Slide
                2. Add Lecture Video""");
        int option=scanner.nextInt();
    }

    public void viewComments() {
        for (Comment comment : comments) {
            System.out.println(comment + "\n");
        }
    }

    public void addComment() {
        scanner.nextLine();
        System.out.print("Enter comment: ");
        String text = scanner.nextLine();
        comments.add(new Comment(text, current));
    }

    public void query() {
        System.out.println(current.getMenu());
        int option = scanner.nextInt();
        current.query(option);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Backpack backpack = new Backpack(scanner, 2, 3);
        while (true) {
            if (!backpack.isLoggedIn()) {
                if (!backpack.login()) break;
            } else {
                backpack.query();
            }
        }
    }
}
