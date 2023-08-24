package com.example.AMS.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude
public class AttendanceDto {


@JsonProperty("class_ID")
    private String classID;
    private boolean IsPresent;
    private LocalDate date;


}
