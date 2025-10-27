# E-Learning Platform (Java)

A simple, single-file Java demo of an e-learning platform that models **Students**, **Instructors**, **Courses**, and **Enrollments**. The project is designed as a clean starting point for intermediate Java developers — you can extend it later with persistence, REST APIs (Spring Boot), or a web UI.

---

## Highlights

* Pure Java (no external libraries required)
* OOP-driven design: `Student`, `Instructor`, `Course`, `Enrollment`
* In-memory storage using `Map` and `List`
* Utility operations: add/list/remove entities, assign instructors, enroll students
* Demo `main()` with seeded data and a simple interactive CLI

---

## Project structure

```
/Lastday
  └─ ElearningPlatform.java   # single-file demo (package Lastday)
README.md
.gitignore
```

> The project uses package `Lastday`. Keep the `.java` file inside a folder named `Lastday`.

---

## Prerequisites

* Java 11 or later (tested with Java 11+). Use `java -version` to check.

---

## How to compile & run

From the parent folder (the folder that contains the `Lastday` directory):

```bash
# Compile
javac Lastday/ElearningPlatform.java

# Run
java Lastday.ElearningPlatform
```

You should see seeded data printed and then an interactive prompt. Type `help` to see commands.

### Example CLI commands

```
help
list students
list courses
list instructors
list enrollments
enroll <studentId> <courseId>
assign <instructorId> <courseId>
exit
```

---

## Example output

```
=== E-Learning Platform Demo ===
Instructors:
  Instructor[id=I100,name=Asha Sharma,dept=Computer Science,doj=2020-07-01,subjects=[Data Structures, Algorithms],email=asha@example.com,phone=+91-9876543210,exp=6yr]
Courses:
  Course[id=C101,name=Introduction to Java,instructor=I100]
  Course[id=C102,name=Web Development,instructor=null]
Students:
  Student[id=S500,name=Rohit Kumar,course=B.Tech,dept=CSE,inst=SRM Ramapuram,email=rohit@example.com,phone=+91-9123456789,year=2nd]
Enrollments:
  Enrollment[id=...,student=S500,course=C101,on=2025-10-27,grade=null]
```

---

## Suggestions / Future Improvements

Here are a few realistic next steps to make this project more production-like and showcase more skills:

* Split classes into separate files (one class per file) and add a proper package structure.
* Add persistence:

  * Simple JSON save/load (Gson or Jackson)
  * Or use a relational DB via JDBC / JPA + Hibernate
* Create a Spring Boot REST API with controllers for Students / Instructors / Courses / Enrollments.
* Add unit tests (JUnit 5) to cover core operations.
* Add input validation (email, phone formats) and error handling.
* Create a lightweight React/Next.js frontend to demonstrate full-stack capabilities.
* Add authentication & authorization (role-based: admin/instructor/student).

---

## Contribution

If you want to contribute (or fork it for your own learning):

* Create a feature branch
* Add tests for new functionality
* Open a PR with a clear description of what you changed

---

## License

This repository is provided under the MIT License. Feel free to reuse and modify for learning and portfolio purposes.

---

## Contact / Attribution

Built by **Deepanshu**. If you post this on GitHub or LinkedIn, feel free to tag me in the README or include a link to your portfolio.

---

Would you like me to:

* generate a ready-to-paste `README.md` file in the repo? (I already created this doc for you), or
* create a separate `README.md` styled for LinkedIn copy (short caption + link + hashtags)?

Tell me which one and I’ll prepare it.
