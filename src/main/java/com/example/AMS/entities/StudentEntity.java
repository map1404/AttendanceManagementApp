package com.example.AMS.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentEntity
{

    private String id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String gender;
    private String teacherId;
    private String classId;

}
