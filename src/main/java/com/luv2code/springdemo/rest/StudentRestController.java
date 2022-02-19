package com.luv2code.springdemo.rest;

import com.luv2code.springdemo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> list;

    @PostConstruct
    public void loadData() {
        list = Arrays.asList(new Student(1, "Vyom", "Yadav"),
                             new Student(2, "Vrinda", "Yadav"));

    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return list;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        try {
            return list.get(studentId - 1);
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new StudentNotFoundException("Student not found with id - " + studentId);
        }
    }


    /* Exception handling moved to global aspect
    // Add exception handler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {
        StudentErrorResponse studentErrorResponse = new StudentErrorResponse();
        studentErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        studentErrorResponse.setMessage(exc.getMessage());
        studentErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(studentErrorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle all the general exceptions
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleExceptionTwo(Exception exc) {

        StudentErrorResponse studentErrorResponse = new StudentErrorResponse();
        studentErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        studentErrorResponse.setMessage(exc.getMessage());
        studentErrorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(studentErrorResponse, HttpStatus.BAD_REQUEST);
    }*/
}
