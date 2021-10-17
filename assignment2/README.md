# AP_assignment

Assignment 2

Aayush Gakhar
2020006

CSE
2024

Ever since the arrival of Google Classroom (GC), it has been the go-to Learning Management System (LMS) for IIITD. LMS is the backbone of how teaching is delivered at IIITD. For quite some time now, it has been observed that IIITD should have its very own LMS, and the institute administration has named the project, "BACKPACK". You have been contacted by BYLD, the developer group at IIITD to give your inputs on this project. They want you to use the knowledge of OOP that you have gathered from Assignment-1 along with interfaces, and polymorphism to design a robust system that can support multiple roles. The system will be used by Instructors (a single course can have multiple instructors like GC) and Students.
For now, you are supposed to design a single course page that has users for the roles mentioned above and show BYLD a working command line simulation.
INSTRUCTOR:
1. An instructor can upload and view lecture materials. Lecture material can be of two types:
a. Lecture Videos: A filename with extension .mp4
b. Lecture Slides: An array of strings representing the content of the slides
2. An instructor can upload and grade assessments. The assessments can be of two
types:
a. Assignments: Problem Statement describing the assignment
b. Quizzes: A single question requiring a one-word answer
3. Also while uploading an assignment an instructor will specify the maximum marks possible for that assignment, which will be used when an instructor grades the submissions. For a quiz, the maximum marks are by default 1.
4. An instructor will also be required to close an assessment after which no submissions will be accepted.
STUDENT:
1. A student can view the uploaded lecture material
2. A student should be able to view assessments and upload submissions which will be
graded by the instructor. The format of submissions will be as follows:
a. Assignments: A filename with extension .zip (eg: sample_submission.zip)
b. Quiz: A one-word solution to the question
3. Students can check if their submission has been graded, and marks received for the same.
  
COMMON FUNCTIONS:
Discussions in forums are an important part of any active learning environment. To facilitate this everyone should be allowed to add comments in a course discussion forum. Every member of the course both instructors and students should be able to add comments and view other comments. While viewing, comments should be displayed along with the name of the member who added them and the timestamp of the comment. Unlike google classroom where we are allowed to add comments to assessments and lecture materials. BYLD wants you to create a simple discussion forum for the time being where members can add comments and view them.
For simulating this assignment you will be required to create a dummy course with some dummy accounts for both instructor and students. And at any point in time, you should be able to log out of a user and log in as another user. When logged in as an instructor we should be able to use the system from the perspective of an instructor. When we log in as a student we should be able to perform functions required by a student.
Please thoroughly go through the sample run below to understand the program flow. Please refrain from asking questions related to your approaches from the TAs and hope you are able to help BYLD with the following task.
Some repetitive print statements:
 
INSTRUCTOR MENU
1. Add class material
2. Add assessments
3. View lecture materials 4. View assessments
5. Grade assessments
6. Close assessment
7. View comments
8. Add comments
9. Logout

STUDENT MENU
1. View lecture materials 
2. View assessments
3. Submit assessment
4. View grades
5. View comments 
6. Add comments 
7. Logout

In the sample case below, when we say {INSTRUCTOR MENU} and {STUDENT MENU}, consider the above-mentioned statements in place.
Note:
1. .zip and .mp4 file extension must be maintained where required. The input format specified above should not be violated. This means, if a student enters “submission.rar” for an assignment, it is not a valid submission and should not be accepted.
2. Other than that no error handling is required.
