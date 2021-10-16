package com.company;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Backpack {
    Scanner scanner;
    List<Instructor> instructors = new LinkedList<>();
    List<Student> students = new LinkedList<>();
    List<Comment> comments = new LinkedList<>();
    List<LectureMaterial> lectureMaterials=new LinkedList<>();
    List<Assessment> assessments=new LinkedList<>();
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
            return true;
        } else {
            return false;
        }
    }

    public void logout() {
        current = null;
    }

    boolean checkExtension(String file,String extension){
        String[] split=file.split("\\.");
        return split.length == 2 && split[1].equals(extension);

    }
    public void addClassMaterial() {
        System.out.println("""
                1. Add Lecture Slide
                2. Add Lecture Video""");
        int option=scanner.nextInt();
        if(option==1){
            scanner.nextLine();
            System.out.print("Enter topic of slides: ");
            String title=scanner.nextLine();
            System.out.print("Enter number of slides: ");
            int noOfSlides=scanner.nextInt();
            scanner.nextLine();
            String[] content=new String[noOfSlides];
            System.out.println("Enter content of slides");
            for (int i = 0; i < noOfSlides; i++) {
                System.out.printf("Content of slide %d: ",i+1);
                content[i]=scanner.nextLine();
            }lectureMaterials.add(new Slides(title,noOfSlides,content,(Instructor) current));
        }else if(option==2){
            scanner.nextLine();
            System.out.print("Enter topic of video: ");
            String title=scanner.nextLine();
            System.out.print("Enter filename of video: ");
            String file=scanner.nextLine();
            if(!checkExtension(file,"mp4")){
                System.out.println("Invalid filename");
                return;
            }
            lectureMaterials.add(new Video(title,file,(Instructor) current));
        }else {
            System.out.println("Invalid input");
        }
    }

    public void addAssessments() {
        System.out.println("""
                1. Add Assignment
                2. Add Quiz""");
        int option=scanner.nextInt();
        if(option==1){
            scanner.nextLine();
            System.out.print("Enter problem statement: ");
            String problemStatement=scanner.nextLine();
            System.out.print("Enter max marks: ");
            int maxMarks=scanner.nextInt();
            assessments.add(new Assignment(problemStatement,maxMarks,(Instructor) current));
        }else if(option==2){
            scanner.nextLine();
            System.out.print("Enter quiz question: ");
            String question =scanner.nextLine();
            assessments.add(new Quiz(question,(Instructor) current));
        }else {
            System.out.println("Invalid input");
        }
    }

    public void viewLectureMaterial() {
        for (LectureMaterial lectureMaterial : lectureMaterials) {
            System.out.println(lectureMaterial+"\n");
        }
    }

    public void viewAssessments() {
        int id=0;
        for (Assessment assessment : assessments) {
            if(!assessment.isClosed()){
                System.out.println("ID: " + id++ + " " + assessment + "\n----------------");
            }
        }
    }

    public void submitAssessments() {
        System.out.println("Pending assessments");
        int c=0,id=0;
        for (Assessment assessment : assessments) {
            if(!((Student)current).getSubmitted().containsKey(assessment)){
                c++;
                System.out.println("ID: "+id+" "+assessment+"\n----------------");
            }id++;
        }if(c==0){
            System.out.println("No pending assessments");
            return;
        }
        System.out.print("Enter ID of assessment: ");
        int ID=scanner.nextInt();
        Assessment assessment=assessments.get(ID);
        if(assessment.getClass()==Assignment.class){
            scanner.nextLine();
            System.out.print("Enter filename of assignment: ");
            String file= scanner.nextLine();
            if(!checkExtension(file,"zip")){
                System.out.println("Invalid filename");
                return;
            }
            Submission submission=new AssignmentSubmission(current,file);
            assessment.getSubmissions().add(submission);
            ((Student) current).getSubmitted().put(assessment,submission);
        }else {
            scanner.nextLine();
            System.out.print(((Quiz)assessment).getQuestion()+" ");
            String ans= scanner.nextLine();
            Submission submission=new QuizSubmission(current,ans);
            assessment.getSubmissions().add(submission);
            ((Student) current).getSubmitted().put(assessment,submission);
        }
    }

    public void gradeAssessment() {
        System.out.println("List of assessments");
        viewAssessments();
        System.out.print("Enter ID of assessment to view submissions: ");
        int id=scanner.nextInt();
        System.out.println("Choose ID from these ungraded submissions");
        int i=0;
        Assessment assessment=assessments.get(id);
        for(Submission submission:assessment.getSubmissions()){
            System.out.println(i++ + ". "+submission.getStudent());
        }
        i=scanner.nextInt();
        Submission submission=assessment.getSubmissions().get(i);
        System.out.println("Submission:\n"+submission+"\n-------------------------------");
        System.out.println("Max Marks: "+((Assignment)assessment).getMaxMarks());
        System.out.print("Marks scored: ");
        int marks=scanner.nextInt();
        submission.grade(marks,(Instructor) current);
    }

    public void closeAssessment() {
        System.out.println("List of open assignments: ");
        viewAssessments();
        System.out.print("Enter ID of assessment to close: ");
        int id=scanner.nextInt();
        assessments.get(id).close();

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
        System.out.println("Welcome "+current);
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
