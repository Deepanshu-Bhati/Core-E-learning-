package Lastday;

import java.time.LocalDate;
import java.util.*;

/**
 * A simple, self-contained e-learning platform model.
 * Single-file demo with basic management operations:
 * - Manage Students, Instructors, Courses
 * - Enroll students into courses
 * - Assign instructors to courses
 * - Simple in-memory storage + demo CLI in main()
 *
 * Notes:
 * - This is meant as a starting project structure you can expand (DB, web APIs, GUI, etc.)
 * - Uses java.time.LocalDate rather than deprecated Date class
 */

// -- Models ---------------------------------------------------------------
class Instructor {
    private String id;
    private String name;
    private String dept;
    private LocalDate doj;
    private List<String> subjects = new ArrayList<>();
    private String email;
    private String phone;          // store phone as String (leading zeros, +country)
    private int experienceYears;

    public Instructor(String id, String name, String dept, LocalDate doj,
                      List<String> subjects, String email, String phone, int experienceYears) {
        this.id = Objects.requireNonNull(id);
        this.name = name;
        this.dept = dept;
        this.doj = doj;
        if (subjects != null) this.subjects = new ArrayList<>(subjects);
        this.email = email;
        this.phone = phone;
        this.experienceYears = experienceYears;
    }

    // getters / setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDept() { return dept; }
    public void setDept(String dept) { this.dept = dept; }
    public LocalDate getDoj() { return doj; }
    public void setDoj(LocalDate doj) { this.doj = doj; }
    public List<String> getSubjects() { return Collections.unmodifiableList(subjects); }
    public void setSubjects(List<String> subjects) { this.subjects = new ArrayList<>(subjects); }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public int getExperienceYears() { return experienceYears; }
    public void setExperienceYears(int experienceYears) { this.experienceYears = experienceYears; }

    @Override
    public String toString() {
        return String.format("Instructor[id=%s,name=%s,dept=%s,doj=%s,subjects=%s,email=%s,phone=%s,exp=%dyr]",
                id, name, dept, doj, subjects, email, phone, experienceYears);
    }
}

class Student {
    private String id;
    private String name;
    private String course;     // primary course
    private String dept;
    private String institution;
    private String phone;
    private String email;
    private String password;   // protected in real apps — here for demo only
    private String degree;
    private String year;
    private String address;

    public Student(String id, String name, String course, String dept, String institution,
                   String phone, String email, String password, String degree, String year, String address) {
        this.id = Objects.requireNonNull(id);
        this.name = name;
        this.course = course;
        this.dept = dept;
        this.institution = institution;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.degree = degree;
        this.year = year;
        this.address = address;
    }

    // getters / setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public String getDept() { return dept; }
    public void setDept(String dept) { this.dept = dept; }
    public String getInstitution() { return institution; }
    public void setInstitution(String institution) { this.institution = institution; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getDegree() { return degree; }
    public void setDegree(String degree) { this.degree = degree; }
    public String getYear() { return year; }
    public void setYear(String year) { this.year = year; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    @Override
    public String toString() {
        return String.format("Student[id=%s,name=%s,course=%s,dept=%s,inst=%s,email=%s,phone=%s,year=%s]",
                id, name, course, dept, institution, email, phone, year);
    }
}

class Course {
    private String id;
    private String name;
    private String description;
    private String instructorId; // assigned instructor (nullable)

    public Course(String id, String name, String description) {
        this.id = Objects.requireNonNull(id);
        this.name = name;
        this.description = description;
    }

    // getters / setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getInstructorId() { return instructorId; }
    public void setInstructorId(String instructorId) { this.instructorId = instructorId; }

    @Override
    public String toString() {
        return String.format("Course[id=%s,name=%s,instructor=%s]", id, name, instructorId);
    }
}

// Enrollment ties student and course with an enrollment date, grade optional
class Enrollment {
    private String enrollmentId;
    private String studentId;
    private String courseId;
    private LocalDate enrolledOn;
    private String grade; // optional

    public Enrollment(String enrollmentId, String studentId, String courseId, LocalDate enrolledOn) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrolledOn = enrolledOn;
    }

    public String getEnrollmentId() { return enrollmentId; }
    public String getStudentId() { return studentId; }
    public String getCourseId() { return courseId; }
    public LocalDate getEnrolledOn() { return enrolledOn; }
    public String getGrade() { return grade; }
    public void setGrade(String grade) { this.grade = grade; }

    @Override
    public String toString() {
        return String.format("Enrollment[id=%s,student=%s,course=%s,on=%s,grade=%s]",
                enrollmentId, studentId, courseId, enrolledOn, grade);
    }
}

// -- Platform manager ----------------------------------------------------
public class ElearningPlatform {
    private Map<String, Student> students = new HashMap<>();
    private Map<String, Instructor> instructors = new HashMap<>();
    private Map<String, Course> courses = new HashMap<>();
    private Map<String, Enrollment> enrollments = new HashMap<>();

    // Student operations
    public void addStudent(Student s) { students.put(s.getId(), s); }
    public Student getStudent(String id) { return students.get(id); }
    public Student removeStudent(String id) { return students.remove(id); }
    public Collection<Student> listStudents() { return students.values(); }

    // Instructor operations
    public void addInstructor(Instructor ins) { instructors.put(ins.getId(), ins); }
    public Instructor getInstructor(String id) { return instructors.get(id); }
    public Instructor removeInstructor(String id) { return instructors.remove(id); }
    public Collection<Instructor> listInstructors() { return instructors.values(); }

    // Course operations
    public void addCourse(Course c) { courses.put(c.getId(), c); }
    public Course getCourse(String id) { return courses.get(id); }
    public Course removeCourse(String id) { return courses.remove(id); }
    public Collection<Course> listCourses() { return courses.values(); }

    // assign instructor
    public boolean assignInstructorToCourse(String instructorId, String courseId) {
        Instructor ins = instructors.get(instructorId);
        Course c = courses.get(courseId);
        if (ins == null || c == null) return false;
        c.setInstructorId(instructorId);
        return true;
    }

    // enrollment
    public Enrollment enrollStudent(String studentId, String courseId) {
        Student st = students.get(studentId);
        Course c = courses.get(courseId);
        if (st == null || c == null) return null;
        String eid = UUID.randomUUID().toString();
        Enrollment e = new Enrollment(eid, studentId, courseId, LocalDate.now());
        enrollments.put(eid, e);
        return e;
    }

    public boolean unenroll(String enrollmentId) {
        return enrollments.remove(enrollmentId) != null;
    }

    public Collection<Enrollment> listEnrollments() { return enrollments.values(); }

    // Simple search helpers
    public List<Course> coursesByInstructor(String instructorId) {
        List<Course> out = new ArrayList<>();
        for (Course c : courses.values()) if (instructorId.equals(c.getInstructorId())) out.add(c);
        return out;
    }

    public List<Enrollment> enrollmentsForStudent(String studentId) {
        List<Enrollment> out = new ArrayList<>();
        for (Enrollment e : enrollments.values()) if (studentId.equals(e.getStudentId())) out.add(e);
        return out;
    }

    // -- Demo CLI ---------------------------------------------------------
    public static void main(String[] args) {
        ElearningPlatform platform = new ElearningPlatform();

        // seed some data
        Instructor i1 = new Instructor("I100", "Asha Sharma", "Computer Science",
                LocalDate.of(2020, 7, 1), Arrays.asList("Data Structures","Algorithms"),
                "asha@example.com", "+91-9876543210", 6);
        platform.addInstructor(i1);

        Course c1 = new Course("C101", "Introduction to Java", "Basics of Java programming");
        Course c2 = new Course("C102", "Web Development", "HTML, CSS, JS basics");
        platform.addCourse(c1);
        platform.addCourse(c2);

        platform.assignInstructorToCourse("I100", "C101");

        Student s1 = new Student("S500", "Rohit Kumar", "B.Tech", "CSE", "SRM Ramapuram",
                "+91-9123456789", "rohit@example.com", "secret123", "B.Tech", "2nd", "Chennai");
        platform.addStudent(s1);

        Enrollment e1 = platform.enrollStudent("S500", "C101");

        // Simple console output
        System.out.println("=== E-Learning Platform Demo ===");
        System.out.println("Instructors:");
        for (Instructor ins : platform.listInstructors()) System.out.println("  " + ins);

        System.out.println("Courses:");
        for (Course c : platform.listCourses()) System.out.println("  " + c);

        System.out.println("Students:");
        for (Student st : platform.listStudents()) System.out.println("  " + st);

        System.out.println("Enrollments:");
        for (Enrollment en : platform.listEnrollments()) System.out.println("  " + en);

        // Interactive small menu (very basic)
        Scanner sc = new Scanner(System.in);
        System.out.println("\nYou can try a small interactive demo. Type 'help' to see commands, 'exit' to quit.");
        while (true) {
            System.out.print("cmd> ");
            String cmd = sc.nextLine().trim();
            if (cmd.equalsIgnoreCase("exit")) break;
            if (cmd.equalsIgnoreCase("help")) {
                System.out.println("Commands: list students | list courses | list instructors | list enrollments | enroll <studentId> <courseId> | assign <instructorId> <courseId> | exit");
                continue;
            }
            if (cmd.equalsIgnoreCase("list students")) {
                platform.listStudents().forEach(s -> System.out.println("  " + s));
                continue;
            }
            if (cmd.equalsIgnoreCase("list courses")) {
                platform.listCourses().forEach(c -> System.out.println("  " + c));
                continue;
            }
            if (cmd.equalsIgnoreCase("list instructors")) {
                platform.listInstructors().forEach(i -> System.out.println("  " + i));
                continue;
            }
            if (cmd.equalsIgnoreCase("list enrollments")) {
                platform.listEnrollments().forEach(e -> System.out.println("  " + e));
                continue;
            }
            if (cmd.startsWith("enroll ")) {
                String[] parts = cmd.split("\\s+");
                if (parts.length >= 3) {
                    Enrollment e = platform.enrollStudent(parts[1], parts[2]);
                    if (e != null) System.out.println("Enrolled: " + e);
                    else System.out.println("Failed to enroll. Check IDs.");
                } else System.out.println("Usage: enroll <studentId> <courseId>");
                continue;
            }
            if (cmd.startsWith("assign ")) {
                String[] parts = cmd.split("\\s+");
                if (parts.length >= 3) {
                    boolean ok = platform.assignInstructorToCourse(parts[1], parts[2]);
                    System.out.println(ok ? "Assigned" : "Failed to assign (check IDs)");
                } else System.out.println("Usage: assign <instructorId> <courseId>");
                continue;
            }
            System.out.println("Unknown command. Type 'help'.");
        }
        sc.close();
        System.out.println("Goodbye.");
    }
}
