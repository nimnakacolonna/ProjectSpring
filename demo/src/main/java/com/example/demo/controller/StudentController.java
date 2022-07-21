package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController/*@RestController annotation in order to simplify the creation
of RESTful web services. It's a convenient annotation that combines
@Controller and @ResponseBody, which eliminates the need to annotate every request handling
 method of the controller class with the @ResponseBody annotation.*/

@RequestMapping("/student")//used to map HTTP requests to handler methods
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/hello")// Annotation for mapping HTTP GET requests onto specific handler methods. Specifically,
    // @GetMapping is a composed annotation that acts as a shortcut for @RequestMapping(method = RequestMethod. GET)
    public String hello(){

        return "Hello World!";
    }

    @PostMapping("/student-details")
    public ResponseEntity<List<Student>> studentDetails(@RequestParam String city){

        List<Student> studentList = studentService.getStudentByCity(city);

        if(studentList.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(studentList,HttpStatus.OK);
    }

//    @GetMapping("/student-details")
//    public ResponseEntity<List<Student>> studentDetails(@RequestParam String city){
//
//        List<Student> studentList = studentService.getStudentByCity(city);
//
//        if(studentList.isEmpty()){
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//
//        return new ResponseEntity<>(studentList,HttpStatus.OK);
//
//    }

    @RequestMapping(value = "/student-list",method = RequestMethod.POST)
    public ResponseEntity<List<Student>> studentList(){
        return new ResponseEntity<>(studentService.getAllStudents(),HttpStatus.OK);
    }


}
