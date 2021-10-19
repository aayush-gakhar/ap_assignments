import java.util.*;

interface Person {
    int getID();

    String toString();

    void query(int option);

    String getMenu();
}

interface Assessment {
    List<Submission> getSubmissions();

    String toString();

    int getMaxMarks();

    boolean isClosed();

    void close();

}

interface LectureMaterial {
    String toString();
}

interface Submission {
    boolean isGraded();

    int getMarks();

    String toString();

    Person getStudent();

    void grade(int marks, Person gradedBy);
}

public class Backpack {
    private final Scanner scanner;
    private final List<Instructor> instructors = new LinkedList<>();
    private final List<Student> students = new LinkedList<>();
    private final List<Comment> comments = new LinkedList<>();
    private final List<LectureMaterial> lectureMaterials = new LinkedList<>();
    private final List<Assessment> assessments = new LinkedList<>();
    private Person current;

    Backpack(Scanner scanner, int noOfInstructors, int noOfStudents) {
        this.scanner = scanner;
        for (int i = 0; i < noOfInstructors; i++)
            instructors.add(new Instructor(this));
        for (int i = 0; i < noOfStudents; i++)
            students.add(new Student(this));
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

    public boolean isLoggedIn() {
        return current != null;
    }

    public boolean login() {
        String loginMenu = """
                Welcome to Backpack
                1. Enter as instructor
                2. Enter as student
                3. Exit""";
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

    public boolean checkExtension(String file, String extension) {
        String[] split = file.split("\\.");
        return split.length != 2 || !split[1].equals(extension);

    }

    public void addClassMaterial() {
        System.out.println("""
                1. Add Lecture Slide
                2. Add Lecture Video""");
        int option = scanner.nextInt();
        if (option == 1) {
            scanner.nextLine();
            System.out.print("Enter topic of slides: ");
            String title = scanner.nextLine();
            System.out.print("Enter number of slides: ");
            int noOfSlides = scanner.nextInt();
            if (noOfSlides < 1) {
                System.out.println("Invalid input. required positive input");
                return;
            }
            scanner.nextLine();
            String[] content = new String[noOfSlides];
            System.out.println("Enter content of slides");
            for (int i = 0; i < noOfSlides; i++) {
                System.out.printf("Content of slide %d: ", i + 1);
                content[i] = scanner.nextLine();
            }
            lectureMaterials.add(new Slides(title, noOfSlides, content, current));
        } else if (option == 2) {
            scanner.nextLine();
            System.out.print("Enter topic of video: ");
            String title = scanner.nextLine();
            System.out.print("Enter filename of video: ");
            String file = scanner.nextLine();
            if (checkExtension(file, "mp4")) {
                System.out.println("Invalid filename");
                return;
            }
            lectureMaterials.add(new Video(title, file, current));
        } else {
            System.out.println("Invalid input");
        }
    }

    public void addAssessments() {
        System.out.println("""
                1. Add Assignment
                2. Add Quiz""");
        int option = scanner.nextInt();
        if (option == 1) {
            scanner.nextLine();
            System.out.print("Enter problem statement: ");
            String problemStatement = scanner.nextLine();
            System.out.print("Enter max marks: ");
            int maxMarks = scanner.nextInt();
            if (maxMarks < 1) {
                System.out.println("Invalid input. required positive input");
                return;
            }
            assessments.add(new Assignment(problemStatement, maxMarks));
        } else if (option == 2) {
            scanner.nextLine();
            System.out.print("Enter quiz question: ");
            String question = scanner.nextLine();
            assessments.add(new Quiz(question));
        } else {
            System.out.println("Invalid input");
        }
    }

    public void viewLectureMaterial() {
        for (LectureMaterial lectureMaterial : lectureMaterials) {
            System.out.println(lectureMaterial + "\n");
        }
    }

    public void viewAssessments() {
        int id = 0;
        for (Assessment assessment : assessments) {
            System.out.println("ID: " + id++ + " " + assessment + "\n----------------");
        }
    }

    public void submitAssessments() {
        System.out.println("Pending assessments");
        int c = 0, id = 0;
        Set<Integer> valid = new HashSet<>();
        for (Assessment assessment : assessments) {
            if (!((Student) current).getSubmitted().containsKey(assessment) && !assessment.isClosed()) {
                c++;
                valid.add(id);
                System.out.println("ID: " + id + " " + assessment + "\n----------------");
            }
            id++;
        }
        if (c == 0) {
            System.out.println("No pending assessments");
            return;
        }
        System.out.print("Enter ID of assessment: ");
        id = scanner.nextInt();
        if (!valid.contains(id)) {
            System.out.println("Invalid id");
            return;
        }
        Assessment assessment = assessments.get(id);
        if (assessment.getClass() == Assignment.class) {
            scanner.nextLine();
            System.out.print("Enter filename of assignment: ");
            String file = scanner.nextLine();
            if (checkExtension(file, "zip")) {
                System.out.println("Invalid filename");
                return;
            }
            Submission submission = new AssignmentSubmission(current, file);
            assessment.getSubmissions().add(submission);
            ((Student) current).getSubmitted().put(assessment, submission);
        } else {
            scanner.nextLine();
            System.out.print(((Quiz) assessment).getQuestion() + " ");
            String ans = scanner.nextLine();
            Submission submission = new QuizSubmission(current, ans);
            assessment.getSubmissions().add(submission);
            ((Student) current).getSubmitted().put(assessment, submission);
        }
    }

    public void gradeAssessment() {
        if(assessments.size()==0){
            System.out.println("No assessments present.");
            return;
        }
        System.out.println("List of assessments");
        viewAssessments();
        System.out.print("Enter ID of assessment to view submissions: ");
        int id = scanner.nextInt();
        Assessment assessment;
        try {
            assessment = assessments.get(id);
        } catch (Exception e) {
            System.out.println("Invalid input");
            return;
        }
        System.out.println("Choose ID from these ungraded submissions");
        int i = 0;
        for (Submission submission : assessment.getSubmissions()) {
            if (!submission.isGraded()) {
                System.out.println(i + ". " + submission.getStudent());
            }
            i++;
        }
        i = scanner.nextInt();
        Submission submission;
        try {
            submission = assessment.getSubmissions().get(i);
            if (submission.isGraded()) {
                System.out.println("Invalid input");
                return;
            }
        } catch (Exception e) {
            System.out.println("Invalid input");
            return;
        }
        System.out.println("Submission:\n" + submission + "\n-------------------------------");
        System.out.println("Max Marks: " + assessment.getMaxMarks());
        System.out.print("Marks scored: ");
        int marks = scanner.nextInt();
        if (marks < 0 || marks > assessment.getMaxMarks()) {
            System.out.println("Invalid marks.");
            return;
        }
        submission.grade(marks, current);
    }

    public void closeAssessment() {
        System.out.println("List of open assignments: ");
        int id = 0;
        for (Assessment assessment : assessments) {
            if (!assessment.isClosed()) {
                System.out.println("ID: " + id + " " + assessment + "\n----------------");
            }
            id++;
        }
        System.out.print("Enter ID of assessment to close: ");
        id = scanner.nextInt();
        Assessment assessment;
        try {
            assessment = assessments.get(id);
            if (assessment.isClosed()) {
                System.out.println("Invalid input");
                return;
            }
        } catch (Exception e) {
            System.out.println("Invalid input");
            return;
        }
        assessment.close();

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
        System.out.println("Welcome " + current);
        System.out.println(current.getMenu());
        int option = scanner.nextInt();
        current.query(option);
    }
}

class Instructor implements Person {
    private static int count = 0;
    private final int ID;
    private final String name;
    private final Backpack backpack;

    Instructor(Backpack backpack) {
        this.ID = count++;
        this.name = "I" + ID;
        this.backpack = backpack;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getMenu() {
        return """
                1. Add class material
                2. Add assessments
                3. View lecture materials
                4. View assessments
                5. Grade assessments
                6. Close assessment
                7. View comments
                8. Add comments
                9. Logout""";
    }

    @Override
    public void query(int option) {
        if (option < 1 || option > 9) {
            System.out.println("Invalid option");
        } else if (option == 1) {
            backpack.addClassMaterial();
        } else if (option == 2) {
            backpack.addAssessments();
        } else if (option == 3) {
            backpack.viewLectureMaterial();
        } else if (option == 4) {
            backpack.viewAssessments();
        } else if (option == 5) {
            backpack.gradeAssessment();
        } else if (option == 6) {
            backpack.closeAssessment();
        } else if (option == 7) {
            backpack.viewComments();
        } else if (option == 8) {
            backpack.addComment();
        } else {
            backpack.logout();
        }
    }

}

class Student implements Person {
    private static int count = 0;
    private final int ID;
    private final String name;
    private final Backpack backpack;
    private final Map<Assessment, Submission> submitted = new HashMap<>();

    Student(Backpack backpack) {
        this.ID = count++;
        this.name = "S" + ID;
        this.backpack = backpack;
    }

    public static int getCount() {
        return count;
    }

    @Override
    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public String getMenu() {
        return """
                1. View lecture materials
                2. View assessments
                3. Submit assessment
                4. View grades
                5. View comments
                6. Add comments
                7. Logout""";
    }

    public Map<Assessment, Submission> getSubmitted() {
        return submitted;
    }

    @Override
    public void query(int option) {
        if (option < 1 || option > 9) {
            System.out.println("Invalid option");
        } else if (option == 1) {
            backpack.viewLectureMaterial();
        } else if (option == 2) {
            backpack.viewAssessments();
        } else if (option == 3) {
            backpack.submitAssessments();
        } else if (option == 4) {
            viewGrades();
        } else if (option == 5) {
            backpack.viewComments();
        } else if (option == 6) {
            backpack.addComment();
        } else {
            backpack.logout();
        }
    }

    public void viewGrades() {
        System.out.println("Graded submissions");
        for (Submission submission : submitted.values()) {
            if (submission.isGraded()) {
                System.out.println(submission);
            }
        }
        System.out.println("----------------------------\n" +
                "Ungraded submissions");
        for (Submission submission : submitted.values()) {
            if (!submission.isGraded()) {
                System.out.println(submission);
            }
        }
    }
}

class Assignment implements Assessment {
    private final String problemStatement;
    private final int maxMarks;
    private final List<Submission> submissions = new LinkedList<>();
    private boolean closed;

    Assignment(String problemStatement, int maxMarks) {
        this.problemStatement = problemStatement;
        this.maxMarks = maxMarks;
        this.closed = false;
    }

    @Override
    public List<Submission> getSubmissions() {
        return submissions;
    }

    @Override
    public String toString() {
        return "Assignment: " + problemStatement + " Max Marks: " + maxMarks;
    }

    @Override
    public int getMaxMarks() {
        return maxMarks;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public void close() {
        closed = true;
    }
}

class AssignmentSubmission implements Submission {
    private final Person student;
    private final String file;
    private boolean graded;
    private int marks;
    private Person gradedBy;

    AssignmentSubmission(Person student, String file) {
        this.student = student;
        this.file = file;
        this.graded = false;
    }

    @Override
    public boolean isGraded() {
        return graded;
    }

    @Override
    public int getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "Submission: " + file + (isGraded() ? "\nMarks scored: " + marks + "\nGraded by: " + gradedBy : "");
    }

    @Override
    public Person getStudent() {
        return student;
    }

    @Override
    public void grade(int marks, Person gradedBy) {
        if (isGraded()) return;
        graded = true;
        this.marks = marks;
        this.gradedBy = gradedBy;
    }
}

class Quiz implements Assessment {
    private final String question;
    private final int maxMarks;
    private final List<Submission> submissions = new LinkedList<>();
    private boolean closed;

    Quiz(String question) {
        this.question = question;
        this.maxMarks = 1;
        this.closed = false;
    }

    @Override
    public List<Submission> getSubmissions() {
        return submissions;
    }

    @Override
    public String toString() {
        return "Question: " + question;
    }

    @Override
    public int getMaxMarks() {
        return maxMarks;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }

    @Override
    public void close() {
        closed = true;
    }

    public String getQuestion() {
        return question;
    }
}

class QuizSubmission implements Submission {
    private final Person student;
    private final String ans;
    private boolean graded;
    private int marks;
    private Person gradedBy;

    QuizSubmission(Person student, String ans) {
        this.student = student;
        this.ans = ans;
        this.graded = false;
    }

    @Override
    public boolean isGraded() {
        return graded;
    }

    @Override
    public int getMarks() {
        return marks;
    }

    @Override
    public String toString() {
        return "Submission: " + ans + (isGraded() ? "\nMarks scored: " + marks + "Graded by: " + gradedBy : "");
    }

    @Override
    public Person getStudent() {
        return student;
    }

    @Override
    public void grade(int marks, Person gradedBy) {
        if (isGraded()) return;
        graded = true;
        this.marks = marks;
        this.gradedBy = gradedBy;
    }
}

class Slides implements LectureMaterial {
    private final String title;
    private final int noOfSlides;
    private final String[] content;
    private final Date dateOfUpload;
    private final Person uploader;

    Slides(String title, int noOfSlides, String[] content, Person uploader) {
        this.title = title;
        this.noOfSlides = noOfSlides;
        this.content = content;
        this.dateOfUpload = new Date();
        this.uploader = uploader;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Title: " + title);
        int i = 1;
        for (String s : content) {
            stringBuilder.append("\nSlide ").append(i++).append(": ").append(s);
        }
        stringBuilder.append("\nNumber of slides: ").append(noOfSlides).append("\nDate of upload: ").append(dateOfUpload).append("\nUploaded by: ").append(uploader);
        return stringBuilder.toString();
    }
}

class Video implements LectureMaterial {
    private final String title;
    private final String file;
    private final Date dateOfUpload;
    private final Person uploader;

    Video(String title, String file, Person uploader) {
        this.title = title;
        this.file = file;
        this.dateOfUpload = new Date();
        this.uploader = uploader;
    }

    @Override
    public String toString() {
        return "Title of video: " + title + "\nVideo file: " + file + "\nDate of upload: " + dateOfUpload + "\nUploaded by: " + uploader;
    }
}

class Comment {
    private final String text;
    private final Person poster;
    private final Date dateOfPosting;

    Comment(String text, Person poster) {
        this.text = text;
        this.poster = poster;
        this.dateOfPosting = new Date();
    }

    public String toString() {
        return text + " - " + poster + "\n" + dateOfPosting;
    }
}
