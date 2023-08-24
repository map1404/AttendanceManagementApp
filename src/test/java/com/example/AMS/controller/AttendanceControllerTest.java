package com.example.AMS.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.AMS.dto.request.AttendanceRequest;
import com.example.AMS.dto.response.AttendanceResponse;
import com.example.AMS.service.AttendanceService;
import com.example.AMS.entities.AttendanceEntity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AttendanceControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AttendanceService attendanceService;

    private AttendanceController attendanceController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        attendanceController = new AttendanceController(attendanceService);
        mockMvc = MockMvcBuilders.standaloneSetup(attendanceController).build();
    }
    @Test
    public void testGetByStudentIdInDateRange() throws Exception {
        // Mock data
        int studentId = 1;
        LocalDate startdate = LocalDate.parse("2023-07-30");
        LocalDate enddate = LocalDate.parse("2023-08-01");

        AttendanceEntity entity1 = new AttendanceEntity(100, studentId, 5002, startdate, true, "X A", 5002, 8,null,null,null);
        AttendanceEntity entity2 = new AttendanceEntity(101, studentId, 5002, enddate, true, "X A", 5002, 8,null,null,null);
        List<AttendanceEntity> mockAttendanceList = Arrays.asList(entity1, entity2);

        // Mock the service method
        when(attendanceService.getBystudentidindaterange(studentId, startdate, enddate))
                .thenReturn(mockAttendanceList);

        // Perform the GET request
        mockMvc.perform(get("/api/v1/attendance/students/id-date-range")
                        .param("studentId", String.valueOf(studentId))
                        .param("startdate", startdate.toString())
                        .param("enddate", enddate.toString()))
                .andExpect(status().isOk());
        verify(attendanceService).getBystudentidindaterange(studentId, startdate, enddate);
    }

    @Test
    public void testEmptyListInput() throws Exception {
        // Perform the GET request with empty list parameters
        mockMvc.perform(get("/api/v1/attendance/students/id-date-range")
                        .param("studentId", "")
                        .param("startdate", "")
                        .param("enddate", ""))
                .andExpect(status().isInternalServerError());
    }





    @Test
    public void testStartDateAfterEndDate() throws Exception {
        // Perform the GET request with invalid parameters (start date after end date)
        try {
            mockMvc.perform(get("/api/v1/attendance/students/id-date-range")
                            .param("studentId", "1")
                            .param("startdate", "2023-08-01")
                            .param("enddate", "2023-07-30"))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testValidationConstraints() throws Exception {
        // Perform the GET request with invalid parameters
        mockMvc.perform(get("/api/v1/attendance/students/id-date-range")
                        .param("studentId", "-1")  // Invalid studentId
                        .param("startdate", "2023-07-30")
                        .param("enddate", "2023/08/01"))  // Incorrect date format
                .andExpect(status().isInternalServerError());
    }


    @Test
    public void testSaveAllAttendance_EmptyRequestList() throws Exception {
        // Create an empty list of AttendanceRequest objects
        List<AttendanceRequest> attendanceRequestList = new ArrayList<>();
        List<AttendanceResponse> attendanceResponseList = new ArrayList<>();
        when(attendanceService.saveAllAttendance(anyList())).thenReturn(attendanceResponseList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc.perform(post("/api/v1/attendance/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(attendanceRequestList)))
                .andExpect(status().isInternalServerError())
                .andReturn();

    }
    @Test
    public void testSaveAllAttendance_InvalidRequestList() throws Exception {


        LocalDate now = LocalDate.now();
        List<AttendanceRequest> attendanceRequestList = new ArrayList<>();
        attendanceRequestList.add(new AttendanceRequest(0, 5001, now, false, "X A", 0, 8,null,null));
        List<AttendanceResponse> attendanceResponseList = new ArrayList<>();
        attendanceResponseList.add(new AttendanceResponse(1,1, 5001, now, false, "X A", 0,8,null,null));

        when(attendanceService.saveAllAttendance(anyList())).thenReturn(attendanceResponseList);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc.perform(post("/api/v1/attendance/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(attendanceRequestList)))
                .andExpect(status().isInternalServerError())
                .andReturn();

    }


    @Test
    public void testSaveAllAttendance() throws Exception {
        LocalDate now = LocalDate.now();

        List<AttendanceRequest> requestList = new ArrayList<>();
        requestList.add(new AttendanceRequest(1, 5001, now, false, "X A", 8, 0,null,null));
        requestList.add(new AttendanceRequest(2, 5001, now, false, "X A", 8, 0,null,null));

        List<AttendanceResponse> mockResponseList = new ArrayList<>();
        mockResponseList.add(new AttendanceResponse(1,1, 5001, now, false, "X A", 8, 0,null,null));
        mockResponseList.add(new AttendanceResponse(1,2, 5001, now, false, "X A", 8, 0,null,null));

        when(attendanceService.saveAllAttendance(anyList())).thenReturn(mockResponseList);

        mockMvc.perform(post("/api/v1/attendance/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(requestList)))

                .andExpect(jsonPath("$[0].studentId").value(1))
                .andExpect(jsonPath("$[1].studentId").value(2))
                .andReturn();
    }

    private String asJsonString(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        return mapper.writeValueAsString(obj);
    }
}

