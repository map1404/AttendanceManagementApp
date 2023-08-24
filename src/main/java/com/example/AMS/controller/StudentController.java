package com.example.AMS.controller;

import com.example.AMS.entities.StudentEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/students")
public class StudentController {

    private List<StudentEntity> students;

    public StudentController() {
        // Load the student data from the JSON file when the controller is initialized
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<StudentEntity>> typeReference = new TypeReference<List<StudentEntity>>() {
        };
        try {
            Resource resource = new PathMatchingResourcePatternResolver()
                    .getResource("classpath:static/students.json");
            InputStream inputStream = resource.getInputStream();
            students = mapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping
    public List<StudentEntity> getAllStudents() {
        // Return the entire list of students
        return students;
    }

    @GetMapping("/emailId/{emailId}")
    public StudentEntity getStudentByEmailId(@PathVariable String emailId) {
        // Find the student with the specified email ID
        Optional<StudentEntity> studentOptional = students.stream()
                .filter(student -> student.getEmailId().equalsIgnoreCase(emailId))
                .findFirst();

        // Return the student if found, or null if not found
        return studentOptional.orElse(null);
    }


    @GetMapping("/classId/{classId}")
    public List<StudentEntity> getStudentByClassId(@PathVariable String classId) {
        // Find the student with the specified classId
        List<StudentEntity> studentsInSection = students.stream()
                .filter(student -> student.getClassId().equalsIgnoreCase(classId))
                .collect(Collectors.toList());
        return studentsInSection;
    }

    @GetMapping("/classId/{classId}/studentCount")
    public int getStudentCountByClass(@PathVariable String classId) {
        // Calculate the number of students in the specified class
        long studentCount = students.stream()
                .filter(student -> student.getClassId().equalsIgnoreCase(classId))
                .count();
        return (int) studentCount; // Return the count as an integer
    }

    @GetMapping("/firstName/{firstName}")
    public List<StudentEntity> getStudentByFirstName(@PathVariable String firstName) {
        // Find the student with the specified email ID
        List<StudentEntity> studentOptional = students.stream()
                .filter(student -> student.getFirstName().equalsIgnoreCase(firstName))
                .collect(Collectors.toList());
        return studentOptional;
    }

    @GetMapping("/lastName/{lastName}")
    public List<StudentEntity> getStudentByLastName(@PathVariable String lastName) {
        // Find the student with the specified email ID
        List<StudentEntity> studentOptional = students.stream()
                .filter(student -> student.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
        return studentOptional;
    }
    @GetMapping("/id/{id}")
    public StudentEntity getStudentById(@PathVariable String id) {
        // Find the student with the specified email ID
        Optional<StudentEntity> studentOptional = students.stream()
                .filter(student -> student.getId().equalsIgnoreCase(id))
                .findFirst();

        // Return the student if found, or null if not found
        return studentOptional.orElse(null);
    }


}
