package com.example.AMS.controller;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.AMS.entities.AttendanceEntity;
import com.example.AMS.exceptions.InvalidDateRangeException;
import com.example.AMS.dto.request.AttendanceRequest;
import com.example.AMS.dto.request.AttendanceUpdateRequest;

import com.example.AMS.dto.response.AttendanceResponse;
import com.example.AMS.service.AttendanceService;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/v1/attendance")
public class AttendanceController  implements AttendanceService{
    private final AttendanceService attendanceService;


    public AttendanceController(AttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }


    @GetMapping("/")
    public List<AttendanceResponse> findAllAttendance()
    {
        return attendanceService.findAllAttendance();
    }





    @PostMapping("/")
    @Override
    public List<AttendanceResponse> saveAllAttendance(@RequestBody List<AttendanceRequest> attendanceRequestList) {
        List<AttendanceResponse> responseList = new ArrayList<>();
        List<String> validationErrors = new ArrayList<>();
        if(attendanceRequestList.isEmpty()) {
            validationErrors.add("Empty Request");
        }
        for (int i = 0; i < attendanceRequestList.size(); i++) {
            AttendanceRequest request = attendanceRequestList.get(i);
            if (request.getStudentId() <= 0) {
                validationErrors.add("Invalid studentId at index " + i);
            }
        }

        if (!validationErrors.isEmpty()) {
            throw new IllegalArgumentException("Validation errors: " + String.join(", ", validationErrors));
        }

        responseList = attendanceService.saveAllAttendance(attendanceRequestList);
        return responseList;
    }
    @PutMapping("/")
    @Override
    public List<AttendanceResponse> updateAllAttendance(@RequestBody List<AttendanceUpdateRequest> attendanceUpdateRequestList) {
        return attendanceService.updateAllAttendance(attendanceUpdateRequestList);
    }

//    @GetMapping("/students")
//    public List<AttendanceResponse> getBystudentidindaterange(@RequestParam int studentId, @RequestParam LocalDate startdate, @RequestParam LocalDate enddate){
//        return attendanceService.getBystudentidindaterange(studentId, startdate, enddate);
//    }



    @GetMapping("/class-records")
    public List<AttendanceResponse> getByclassIDindaterange(@RequestParam String classId, @RequestParam LocalDate startdate, @RequestParam LocalDate enddate) {
        return attendanceService.getByclassIDindaterange(classId, startdate, enddate);
    }

    @GetMapping()
    @Override
    public List<AttendanceResponse> getByclassIDanddate(@RequestParam String classId, @RequestParam LocalDate date) {

        return attendanceService.getByclassIDanddate(classId,date);
    }
    @GetMapping("/student-records")
    public List<AttendanceEntity> getBystudentidindaterange(
            @RequestParam(name = "studentId") @NotNull @Min(value = 1, message = "Student ID must be greater than or equal to 1") int studentId,
            @RequestParam(name = "startdate") @NotBlank @Past(message = "Start date must be in the past or present") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startdate,
            @RequestParam(name = "enddate") @NotBlank @FutureOrPresent(message = "End date must be in the present or future") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enddate) {


        //  validation for datechecks
        if (startdate.isAfter(enddate)) {
            throw new InvalidDateRangeException("Start date must be before or equal to end date.");
        }
        return attendanceService.getBystudentidindaterange(studentId, startdate, enddate);
    }

    @ExceptionHandler(InvalidDateRangeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleInvalidDateRangeException(InvalidDateRangeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());


    }
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<?> handlingInternalServerError(IllegalArgumentException exception) {
        // code to be executed when the exception is thrown (logs, ...)
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}