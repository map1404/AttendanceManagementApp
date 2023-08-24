package com.example.AMS.service;
import com.example.AMS.entities.AttendanceEntity;
import com.example.AMS.dto.request.AttendanceRequest;
import com.example.AMS.dto.request.AttendanceUpdateRequest;

import com.example.AMS.dto.response.AttendanceResponse;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceService {



    //Request and Response
    public List<AttendanceResponse> findAllAttendance();

    public List<AttendanceResponse> saveAllAttendance(List<AttendanceRequest> attendanceRequestList);
    public List<AttendanceResponse> updateAllAttendance(List<AttendanceUpdateRequest> attendanceUpdateRequestList);

    public List<AttendanceEntity> getBystudentidindaterange(int studentId, LocalDate startdate, LocalDate enddate);

    public List<AttendanceResponse> getByclassIDindaterange(String classId, LocalDate startdate, LocalDate enddate);

    public  List<AttendanceResponse> getByclassIDanddate(String classId, LocalDate date);
}
