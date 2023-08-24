package com.example.AMS.controller;

import com.example.AMS.entities.LeaveEntity;
import com.example.AMS.dto.types.LeaveType;
import com.example.AMS.service.LeaveService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class LeaveControllerTest {

    @Mock
    private LeaveService leaveService;

    private MockMvc mockMvc;

    @InjectMocks
    private LeaveController leaveController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        leaveController = new LeaveController(leaveService);
        mockMvc = MockMvcBuilders.standaloneSetup(leaveController).build();
    }



    @Test
    void testGetLeavesByStudentId() throws Exception {
        int studentId = 123;

        LeaveEntity leave1 = new LeaveEntity();
        leave1.setApplicationDate(LocalDate.of(2023, 8, 1));
        leave1.setAttachment("attachment1.pdf");
        leave1.setEndDate(LocalDate.of(2023, 8, 10));
        leave1.setLastUpdated(LocalDate.now());
        leave1.setStartDate(LocalDate.of(2023, 8, 5));
        leave1.setStatus("Pending");
        leave1.setStudentId(studentId);
        leave1.setClassId("A101");
        leave1.setType(LeaveType.SICK_LEAVE);
        leave1.setUpdatedBy(456);
        leave1.setDescription("Sick leave due to fever");

        LeaveEntity leave2 = new LeaveEntity();
        leave2.setApplicationDate(LocalDate.of(2023, 8, 5));
        leave2.setAttachment("attachment2.pdf");
        leave2.setEndDate(LocalDate.of(2023, 8, 15));
        leave2.setLastUpdated(LocalDate.now());
        leave2.setStartDate(LocalDate.of(2023, 8, 7));
        leave2.setStatus("Approved");
        leave2.setType(LeaveType.VACATION_LEAVE);
        leave2.setStudentId(studentId);
        leave2.setClassId("A102");
        leave2.setUpdatedBy(456);
        leave2.setDescription("Vacation leave request");

        List<LeaveEntity> expectedLeaves = new ArrayList<>();
        leave1.setLeaveId(1);
        expectedLeaves.add(leave1);
        leave2.setLeaveId(2);
        expectedLeaves.add(leave2);

        when(leaveService.getLeavesByStudentId(studentId)).thenReturn(expectedLeaves);
        List<LeaveEntity> actualLeaves = leaveController.getLeavesByStudentId(studentId);
        mockMvc.perform(get("/api/v1/leave/student")
                        .param("studentId", String.valueOf(studentId)))
                .andExpect(status().isOk());
    }


    @Test
    void testGetLeavesByStudentId_NoLeavesFound() {
        int studentId = 456;
        when(leaveService.getLeavesByStudentId(studentId)).thenReturn(new ArrayList<>());

        List<LeaveEntity> actualLeaves = leaveController.getLeavesByStudentId(studentId);

        assertTrue(actualLeaves.isEmpty());
    }
    @Test
    public void testcreateleave() throws Exception {
        // Create an empty list of AttendanceRequest objects
        LeaveEntity leaveEntity = new LeaveEntity();
        leaveEntity.setLeaveId(1);
        leaveEntity.setStudentId(1);
        leaveEntity.setUpdatedBy(5003);
        leaveEntity.setClassId("X A");


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Mockito.when(leaveService.createLeave(Mockito.any(LeaveEntity.class)))
                .thenReturn(leaveEntity);

        objectMapper.registerModule(new JavaTimeModule());


        mockMvc.perform(post("/api/v1/leave")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(leaveEntity)))
                .andExpect(status().isOk()) // Adjust this expectation based on your use case
                .andReturn();

    }
    @Test
    public void testcreateleave_invalidrequest() throws Exception {
        // Create an empty list of AttendanceRequest objects
        LeaveEntity leaveEntity = new LeaveEntity();
        leaveEntity.setLeaveId(1);
        leaveEntity.setStudentId(-1);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Mockito.when(leaveService.createLeave(Mockito.any(LeaveEntity.class)))
                .thenReturn(leaveEntity);

        objectMapper.registerModule(new JavaTimeModule());


        mockMvc.perform(post("/api/v1/leave")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(leaveEntity)))
                .andExpect(status().isOk()) // Adjust this expectation based on your use case
                .andReturn();

    }

}
