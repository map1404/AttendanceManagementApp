package com.example.AMS.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class TeacherEntity
{

    private String id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String classId;
}
