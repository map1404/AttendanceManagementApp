package com.example.AMS.controller;

import com.example.AMS.entities.TeacherEntity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping("/teachers")
public class TeacherController {

    private List<TeacherEntity> Teachers;

    public TeacherController() {
        // Load the Teacher data from the JSON file when the controller is initialized
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<TeacherEntity>> typeReference = new TypeReference<List<TeacherEntity>>() {};
        try {
            Resource resource = new PathMatchingResourcePatternResolver()
                    .getResource("classpath:static/teachers.json");
            InputStream inputStream = resource.getInputStream();
            Teachers = mapper.readValue(inputStream, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping
    public List<TeacherEntity> getAllTeachers() {
        // Return the entire list of Teachers
        return Teachers;
    }


    @GetMapping("/emailId/{emailId}")
    public List<TeacherEntity> getTeacherByEmailId(@PathVariable String emailId) {
        // Find the teacher with the specified email ID
        List<TeacherEntity> teacherOptional = Teachers.stream()
                .filter(teacher -> teacher.getEmailId().equalsIgnoreCase(emailId))
                .collect(Collectors.toList());

        // Return the teacher if found, or null if not found
        return teacherOptional;
    }

    @GetMapping("/classId/{classId}")
    public List<TeacherEntity> getTeacherByClassId(@PathVariable String classId) {
        // Find the teacher with the specified classId
        List<TeacherEntity> teachersInSection = Teachers.stream()
                .filter(teacher -> teacher.getClassId().equalsIgnoreCase(classId))
                .collect(Collectors.toList());
        return teachersInSection;
    }



    @GetMapping("/id/{id}")
    public List<TeacherEntity> getTeacherById(@PathVariable String id) {
        // Find the teacher with the specified email ID
        List<TeacherEntity> teacherOptional = Teachers.stream()
                .filter(teacher -> teacher.getId().equalsIgnoreCase(id))
                .collect(Collectors.toList());


        // Return the teacher if found, or null if not found
        return teacherOptional;
    }




     @GetMapping("/lastName/{lastName}")
    public List<TeacherEntity> getTeacherByLastName(@PathVariable String lastName) {
        // Find the teacher with the specified email ID
        List<TeacherEntity> teacherOptional = Teachers.stream()
                .filter(teacher -> teacher.getLastName().equalsIgnoreCase(lastName))
                .collect(Collectors.toList());
        return teacherOptional;
    }

}
